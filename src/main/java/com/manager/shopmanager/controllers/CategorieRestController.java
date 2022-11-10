package com.manager.shopmanager.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.manager.shopmanager.model.Categorie;
import com.manager.shopmanager.repository.CategorieRepository;

@org.springframework.web.bind.annotation.RestController
public class CategorieRestController {

    @Autowired
    private CategorieRepository categorieRepository;

    @GetMapping(value = "/categorie", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<Categorie>> getCategories() {
        return new ResponseEntity<>(categorieRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/categorie", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Categorie> addCategorie(@Valid @RequestBody Categorie input) {
        return new ResponseEntity<>(categorieRepository.save(input), HttpStatus.OK);
    }

    @PatchMapping(value = "/categorie/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
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
