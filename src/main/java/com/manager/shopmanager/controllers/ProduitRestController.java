package com.manager.shopmanager.controllers;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.manager.shopmanager.model.Boutique;
import com.manager.shopmanager.model.Produit;
import com.manager.shopmanager.model.interfaces.ValidationGroups.OnCreateValidation;
import com.manager.shopmanager.model.interfaces.ValidationGroups.OnPatchValidation;
import com.manager.shopmanager.service.BoutiqueServiceImpl;

@RestController
@RequestMapping("/boutique/{boutiqueId}")
public class ProduitRestController {

    @Resource
    private BoutiqueServiceImpl boutiqueService;

    @GetMapping(value = "/produits", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<Produit>> getProducts(@PathVariable(value = "boutiqueId") int boutiqueId) {
        Optional<Boutique> optBoutique = boutiqueService.getBoutique(boutiqueId);
        if (optBoutique.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Boutique b = optBoutique.get();
        return new ResponseEntity<>(b.getProduits(), HttpStatus.OK);
    }

    @PostMapping(value = "/produits", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Produit> addProduct(@PathVariable(value = "boutiqueId") int boutiqueId,
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

    @PatchMapping(value = "/produits/{produitId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Produit> modifyProduit(@PathVariable(value = "boutiqueId") int boutiqueId,
            @PathVariable(value = "produitId") int produitId,
            @Validated(OnPatchValidation.class) @RequestBody Produit input) {
        Optional<Boutique> optBoutique = boutiqueService.getBoutique(boutiqueId);
        if (optBoutique.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Boutique boutique = optBoutique.get();
        Produit produit = boutique.getProduit(produitId);
        if (produit == null) {
            return ResponseEntity.notFound().build();
        }
        produit.modifyProduit(input);
        boutiqueService.saveBoutique(boutique);
        return new ResponseEntity<>(produit, HttpStatus.OK);

    }

    @DeleteMapping(value = "/produits/{produitId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Object> deleteProduct(@PathVariable(value = "boutiqueId") int boutiqueId,
            @PathVariable(value = "produitId") int produitId) {
        Optional<Boutique> optBoutique = boutiqueService.getBoutique(boutiqueId);
        if (optBoutique.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Boutique boutique = optBoutique.get();
        Produit produit = boutique.getProduit(produitId);
        if (produit == null) {
            return ResponseEntity.notFound().build();
        }
        boutique.removeProduit(produit);
        boutiqueService.saveBoutique(boutique);
        return ResponseEntity.ok().build();
    }

}
