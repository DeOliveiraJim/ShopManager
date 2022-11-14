package com.manager.shopmanager.controllers;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.manager.shopmanager.exceptions.ElementNotFoundException;
import com.manager.shopmanager.model.Boutique;
import com.manager.shopmanager.model.Produit;
import com.manager.shopmanager.model.interfaces.ValidationGroups.OnCreateValidation;
import com.manager.shopmanager.model.interfaces.ValidationGroups.OnPatchValidation;
import com.manager.shopmanager.service.BoutiqueServiceImpl;

@org.springframework.web.bind.annotation.RestController
public class BoutiqueRestController {

    @Resource
    private BoutiqueServiceImpl boutiqueService;

    @GetMapping(value = "/boutique", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Iterable<Boutique> getAllBoutiques() {
        return boutiqueService.getAllboutiques();
    }

    @PostMapping(value = "/boutique", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boutique> createBoutique(@Validated(OnCreateValidation.class) @RequestBody Boutique input) {
        Date date = new Date();
        input.setDateCreation(new Timestamp(date.getTime()));
        return new ResponseEntity<>(boutiqueService.saveBoutique(input), HttpStatus.OK);
    }

    @PatchMapping(value = "/boutique/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boutique> updateBoutique(@PathVariable(value = "id") int id,
            @Validated(OnPatchValidation.class) @RequestBody Boutique input) throws ElementNotFoundException {
        Optional<Boutique> optBoutique = boutiqueService.getBoutique(id);
        if (optBoutique.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Boutique boutique = optBoutique.get();
        boutique.modifyBoutique(input);
        return new ResponseEntity<>(boutiqueService.saveBoutique(boutique), HttpStatus.OK);
    }

    @DeleteMapping(value = "/boutique/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Object> deleteBoutique(@PathVariable(value = "id") int boutiqueId) {
        Optional<Boutique> optBoutique = boutiqueService.getBoutique(boutiqueId);
        if (optBoutique.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        boutiqueService.deleteBoutique(boutiqueId);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/boutique/{id}/produits", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<Produit>> getProducts(@PathVariable(value = "id") int boutiqueId) {
        Optional<Boutique> optBoutique = boutiqueService.getBoutique(boutiqueId);
        if (optBoutique.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Boutique b = optBoutique.get();
        return new ResponseEntity<>(b.getProduits(), HttpStatus.OK);
    }

    @PostMapping(value = "/boutique/{id}/produits", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Produit> addProduct(@PathVariable(value = "id") int boutiqueId,
            @Validated(OnCreateValidation.class) @RequestBody Produit input) {
        Optional<Boutique> optBoutique = boutiqueService.getBoutique(boutiqueId);
        if (optBoutique.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Boutique b = optBoutique.get();
        b.addProduit(input);
        List<Produit> prods = boutiqueService.saveBoutique(b).getProduits();
        return new ResponseEntity<>(prods.get(prods.size() - 1), HttpStatus.OK);
    }

    @PatchMapping(value = "/boutique/{id}/produits/{produitId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Produit> modifyProduit(@PathVariable(value = "id") int boutiqueId,
            @PathVariable(value = "produitId") int produitId,
            @Validated(OnPatchValidation.class) @RequestBody Produit input) {
        Boutique b = getBoutique(boutiqueId);
        if (b == null) {
            return ResponseEntity.notFound().build();
        }
        Produit produit = b.getProduit(produitId);
        if (produit == null) {
            return ResponseEntity.notFound().build();
        }
        produit.modifyProduit(input);
        boutiqueService.saveBoutique(b);
        return new ResponseEntity<>(produit, HttpStatus.OK);

    }

    @DeleteMapping(value = "/boutique/{id}/produits/{produitId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") int boutiqueId,
            @PathVariable(value = "produitId") int produitId) {

        Boutique b = getBoutique(boutiqueId);
        if (b == null) {
            return ResponseEntity.notFound().build();
        }
        Produit produit = b.getProduit(produitId);
        if (produit == null) {
            return ResponseEntity.notFound().build();
        }
        b.removeProduit(produit);
        boutiqueService.saveBoutique(b);
        return ResponseEntity.ok().build();
    }

    private Boutique getBoutique(int boutiqueId) {
        Optional<Boutique> optBoutique = boutiqueService.getBoutique(boutiqueId);
        if (optBoutique.isEmpty()) {
            return null;
        }
        Boutique b = optBoutique.get();
        return b;
    }

}
