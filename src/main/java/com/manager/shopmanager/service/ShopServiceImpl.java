package com.manager.shopmanager.service;

import com.manager.shopmanager.exceptions.ElementNotFoundException;
import com.manager.shopmanager.model.Shop;
import com.manager.shopmanager.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopServiceImpl {

    @Autowired
    private ShopRepository shopRepository;

    public boolean deleteShop(int shopId) {
        shopRepository.deleteById(shopId);
        return true;
    }

    public Iterable<Shop> getAllshops() {
        return shopRepository.findAll();
    }

    public Shop getShop(int id) throws ElementNotFoundException {
        return shopRepository.findById(id).orElseThrow(
                () -> new ElementNotFoundException("The shop with id " + id + " cannot be found"));
    }

    public Shop saveShop(Shop b) {
        return shopRepository.save(b);
    }

}
