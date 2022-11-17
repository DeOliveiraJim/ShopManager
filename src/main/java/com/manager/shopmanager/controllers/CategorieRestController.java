package com.manager.shopmanager.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.manager.shopmanager.model.Categorie;
import com.manager.shopmanager.model.interfaces.ValidationGroups.OnCreateValidation;
import com.manager.shopmanager.model.interfaces.ValidationGroups.OnPatchValidation;
import com.manager.shopmanager.repository.CategorieRepository;

@RestController
@RequestMapping("/categorie")
public class CategorieRestController {

    @Autowired
    private CategorieRepository categorieRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<Categorie>> getCategories() {
        return new ResponseEntity<>(categorieRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Categorie> addCategorie(
            @Validated(OnCreateValidation.class) @RequestBody Categorie input) {
        return new ResponseEntity<>(categorieRepository.save(input), HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Categorie> renameCategorie(@PathVariable(value = "id") int categorieId,
            @Validated(OnPatchValidation.class) @RequestBody Categorie input) {
        Optional<Categorie> opCat = categorieRepository.findById(categorieId);
        if (opCat.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Categorie categorie = opCat.get();
        categorie.setNom(input.getNom());
        return new ResponseEntity<>(categorieRepository.save(categorie), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Object> removeCategorie(@PathVariable(value = "id") int categorieId) {
        Optional<Categorie> opCat = categorieRepository.findById(categorieId);
        if (opCat.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        categorieRepository.delete(opCat.get());
        return ResponseEntity.ok().build();
    }
}
