package com.manager.shopmanager.controllers;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.manager.shopmanager.model.Boutique;
import com.manager.shopmanager.model.Categorie;
import com.manager.shopmanager.model.Produit;
import com.manager.shopmanager.repository.CategorieRepository;
import com.manager.shopmanager.service.BoutiqueServiceImpl;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Resource
    private BoutiqueServiceImpl boutiqueService;
    @Autowired
    private CategorieRepository categorieRepository;

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

    @GetMapping(value = "/boutique/{id}", produces = "application/json")
    public @ResponseBody ResponseEntity<List<Produit>> getProducts(@PathVariable(value = "id") int boutiqueId) {
        Boutique b = null;
        for (Boutique x : boutiqueService.getAllboutiques()) {
            if (x.getId() == boutiqueId) {
                b = x;
                break;
            }
        }
        if (b == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(b.getProduits(), HttpStatus.OK);
    }

    @PostMapping(value = "/boutique/{id}", produces = "application/json")
    public @ResponseBody ResponseEntity<Object> addProduct(@PathVariable(value = "id") int boutiqueId,
            @Valid @RequestBody Produit input) {
        Boutique b = null;
        for (Boutique x : boutiqueService.getAllboutiques()) {
            if (x.getId() == boutiqueId) {
                b = x;
                break;
            }
        }
        if (b == null) {
            return ResponseEntity.notFound().build();
        }
        b.addProduit(input);
        boutiqueService.createBoutique(b);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/categorie", produces = "application/json")
    public @ResponseBody ResponseEntity<List<Categorie>> getCategories() {
        return new ResponseEntity<>(categorieRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/categorie", produces = "application/json")
    public @ResponseBody ResponseEntity<Object> addCategorie(@Valid @RequestBody Categorie input) {
        categorieRepository.save(input);
        return ResponseEntity.ok().build();
    }
}
