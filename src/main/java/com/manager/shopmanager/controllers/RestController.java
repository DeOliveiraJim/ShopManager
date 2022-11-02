package com.manager.shopmanager.controllers;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @GetMapping(value = "/boutique", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Iterable<Boutique> getAllBoutiques() {
        return boutiqueService.getAllboutiques();
    }

    @PostMapping(value = "/boutique", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boutique> createBoutique(@Valid @RequestBody Boutique input) {
        return new ResponseEntity<>(boutiqueService.saveBoutique(input), HttpStatus.OK);
    }

    @DeleteMapping(value = "/boutique/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<Produit>> deleteBoutique(@PathVariable(value = "id") int boutiqueId) {
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
        boutiqueService.deleteBoutique(boutiqueId);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/boutique/{id}/produits", produces = MediaType.APPLICATION_JSON_VALUE)
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

    @PostMapping(value = "/boutique/{id}/produits", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Produit> addProduct(@PathVariable(value = "id") int boutiqueId,
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
        List<Produit> prods = boutiqueService.saveBoutique(b).getProduits();
        return new ResponseEntity<>(prods.get(prods.size() - 1), HttpStatus.OK);
    }

    @DeleteMapping(value = "/boutique/{id}/produits/{produitId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") int boutiqueId,
            @PathVariable(value = "produitId") int produitId) {
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
        Produit produit = null;
        for (Produit p : b.getProduits()) {
            if (p.getId() == produitId) {
                produit = p;
                break;
            }
        }
        if (produit == null) {
            return ResponseEntity.notFound().build();
        }
        b.removeProduit(produit);
        boutiqueService.saveBoutique(b);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/categorie", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<Categorie>> getCategories() {
        return new ResponseEntity<>(categorieRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/categorie", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Categorie> addCategorie(@Valid @RequestBody Categorie input) {
        return new ResponseEntity<>(categorieRepository.save(input), HttpStatus.OK);
    }

    @PutMapping(value = "/categorie/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Categorie> renameCategorie(@PathVariable(value = "id") int categorieId,
            @Valid @RequestBody Categorie input) {
        Optional<Categorie> opCat = categorieRepository.findById(categorieId);
        if (opCat.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Categorie categorie = opCat.get();
        categorie.setNom(input.getNom());
        return new ResponseEntity<>(categorieRepository.save(categorie), HttpStatus.OK);
    }

    @DeleteMapping(value = "/categorie/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Object> removeCategorie(@PathVariable(value = "id") int categorieId) {
        Optional<Categorie> opCat = categorieRepository.findById(categorieId);
        if (opCat.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        categorieRepository.delete(opCat.get());
        return ResponseEntity.ok().build();
    }

}
