package com.manager.shopmanager.controllers;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.manager.shopmanager.model.Category;
import com.manager.shopmanager.model.Product;
import com.manager.shopmanager.model.Shop;
import com.manager.shopmanager.model.interfaces.ValidationGroups.OnCreateValidation;
import com.manager.shopmanager.model.interfaces.ValidationGroups.OnPatchValidation;
import com.manager.shopmanager.service.CategoryServiceImpl;
import com.manager.shopmanager.service.ShopServiceImpl;

@RestController
@RequestMapping("/shops/{shopId}")
public class ProductRestController {

    @Resource
    private ShopServiceImpl shopService;

    @Resource
    private CategoryServiceImpl categoryService;

    @GetMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<Product>> getProducts(@PathVariable(value = "shopId") int shopId) {
        Shop b = shopService.getShop(shopId);
        return new ResponseEntity<>(b.getProducts(), HttpStatus.OK);
    }

    @GetMapping(value = "/products/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Product> getOneProduct(@PathVariable(value = "shopId") int shopId,
            @PathVariable(value = "productId") int productId) {

        Shop shop = shopService.getShop(shopId);
        Product product = shop.getProduct(productId);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Product> addProduct(@PathVariable(value = "shopId") int shopId,
            @Validated(OnCreateValidation.class) @RequestBody Product input) {
        if (input.getCategories() != null) {
            input.setCategories(resolveCategories(input.getCategories()));
        }
        Shop b = shopService.getShop(shopId);
        b.addProduct(input);
        List<Product> prods = shopService.saveShop(b).getProducts();
        return new ResponseEntity<>(prods.get(prods.size() - 1), HttpStatus.OK);
    }

    @PatchMapping(value = "/products/{productId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Product> modifyProduct(@PathVariable(value = "shopId") int shopId,
            @PathVariable(value = "productId") int productId,
            @Validated(OnPatchValidation.class) @RequestBody Product input) {
        if (input.getCategories() != null) {
            input.setCategories(resolveCategories(input.getCategories()));
        }
        Shop shop = shopService.getShop(shopId);
        Product product = shop.getProduct(productId);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        product.modifyProduct(input);
        shopService.saveShop(shop);
        return new ResponseEntity<>(product, HttpStatus.OK);

    }

    @DeleteMapping(value = "/products/{productId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Object> deleteProduct(@PathVariable(value = "shopId") int shopId,
            @PathVariable(value = "productId") int productId) {
        Shop shop = shopService.getShop(shopId);
        Product product = shop.getProduct(productId);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        shop.removeProduct(product);
        shopService.saveShop(shop);
        return ResponseEntity.ok().build();
    }

    private List<Category> resolveCategories(List<Category> cats) {
        List<Category> result = new LinkedList<>();
        for (Category c : cats) {
            c.setName(c.getName().toUpperCase());
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
