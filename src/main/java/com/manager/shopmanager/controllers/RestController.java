package com.manager.shopmanager.controllers;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.manager.shopmanager.model.Boutique;
import com.manager.shopmanager.service.BoutiqueServiceImpl;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Resource
    private BoutiqueServiceImpl boutiqueService;

    @GetMapping(value = "/boutique", produces = "application/json")
    public @ResponseBody Iterable<Boutique> getAllBoutiques() {
        return boutiqueService.getAllboutiques();
    }

    @PostMapping(value = "/boutique", consumes = "application/json")
    public ResponseEntity<Object> createBoutique(@Valid @RequestBody Boutique input) {
        System.out.println(input);
        boutiqueService.createBoutique(input);
        return ResponseEntity.ok().build();
    }

}
