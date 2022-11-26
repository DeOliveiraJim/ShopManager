package com.manager.shopmanager.controllers;

import com.manager.shopmanager.exceptions.ElementNotFoundException;
import com.manager.shopmanager.model.Shop;
import com.manager.shopmanager.model.interfaces.ValidationGroups.OnCreateValidation;
import com.manager.shopmanager.model.interfaces.ValidationGroups.OnPatchValidation;
import com.manager.shopmanager.service.ShopServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;

@RestController
@RequestMapping("/shops")
public class ShopRestController {

    @Resource
    private ShopServiceImpl shopService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Iterable<Shop> getAllShops() {
        return shopService.getAllshops();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Shop getOneShop(@PathVariable(value = "id") int shopId) {
        return shopService.getShop(shopId);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Shop> createShop(@Validated(OnCreateValidation.class) @RequestBody Shop input) {
        Date date = new Date();
        input.setCreationDate(new Timestamp(date.getTime()));
        return new ResponseEntity<>(shopService.saveShop(input), HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Shop> updateShop(@PathVariable(value = "id") int id,
                                           @Validated(OnPatchValidation.class) @RequestBody Shop input) throws ElementNotFoundException {
        Shop shop = shopService.getShop(id);
        shop.modifyShop(input);
        return new ResponseEntity<>(shopService.saveShop(shop), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Object> deleteShop(@PathVariable(value = "id") int shopId) {
        shopService.getShop(shopId);
        shopService.deleteShop(shopId);
        return ResponseEntity.ok().build();
    }

}
