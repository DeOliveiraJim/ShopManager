package com.manager.shopmanager;

import java.io.File;
import java.sql.Timestamp;
import java.util.*;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manager.shopmanager.entity.Category;
import com.manager.shopmanager.entity.Product;
import com.manager.shopmanager.entity.Shop;
import com.manager.shopmanager.service.CategoryServiceImpl;
import com.manager.shopmanager.service.ShopServiceImpl;

@Component
public class FillDatabase implements ApplicationRunner {
    @Autowired
    private ShopServiceImpl shopService;

    @Autowired
    private CategoryServiceImpl categoryService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (shopService.count() != 0 || categoryService.count() != 0)
            return;

        File shopFile = ResourceUtils.getFile("classpath:shop.json");
        File productFile = ResourceUtils.getFile("classpath:product.json");
        ObjectMapper mapper = new ObjectMapper();
        Shop[] shops = mapper.readValue(shopFile, Shop[].class);
        Product[] products = mapper.readValue(productFile, Product[].class);

        Timestamp ts = new Timestamp(new Date().getTime());
        for (int i = 0; i < shops.length; i++) {
            shops[i].setCreationDate(ts);
            shops[i] = shopService.saveShop(shops[i]);
        }
        Random rand = new Random();
        for (Product p : products) {
            p.setCategories(resolveCategories(p.getCategories()));
            int r = rand.nextInt(shops.length);
            Shop s = shopService.getShop(shops[r].getId());
            Hibernate.initialize(s.getProducts());
            s.addProduct(p);
            shopService.saveShop(s);
        }
    }

    private Set<Category> resolveCategories(Set<Category> set) {
        Set<Category> result = new HashSet<>();
        for (Category c : set) {
            Optional<Category> optC = categoryService.getCategoryByName(c.getName());
            if (optC.isPresent()) {
                result.add(optC.get());
            } else {
                result.add(c);
            }
        }
        return result;
    }
}
