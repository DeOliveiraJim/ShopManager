package com.manager.shopmanager.controllers;

import java.sql.Timestamp;
import java.util.Date;

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

import com.manager.shopmanager.exceptions.ElementNotFoundException;
import com.manager.shopmanager.model.Boutique;
import com.manager.shopmanager.model.interfaces.ValidationGroups.OnCreateValidation;
import com.manager.shopmanager.model.interfaces.ValidationGroups.OnPatchValidation;
import com.manager.shopmanager.service.BoutiqueServiceImpl;

@RestController
@RequestMapping("/boutique")
public class BoutiqueRestController {

    @Resource
    private BoutiqueServiceImpl boutiqueService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Iterable<Boutique> getAllBoutiques() {
        return boutiqueService.getAllboutiques();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Boutique getOneBoutique(@PathVariable(value = "id") int boutiqueId) {
        return boutiqueService.getBoutique(boutiqueId);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boutique> createBoutique(@Validated(OnCreateValidation.class) @RequestBody Boutique input) {
        Date date = new Date();
        input.setDateCreation(new Timestamp(date.getTime()));
        return new ResponseEntity<>(boutiqueService.saveBoutique(input), HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boutique> updateBoutique(@PathVariable(value = "id") int id,
            @Validated(OnPatchValidation.class) @RequestBody Boutique input) throws ElementNotFoundException {
        Boutique boutique = boutiqueService.getBoutique(id);
        boutique.modifyBoutique(input);
        return new ResponseEntity<>(boutiqueService.saveBoutique(boutique), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Object> deleteBoutique(@PathVariable(value = "id") int boutiqueId) {
        boutiqueService.getBoutique(boutiqueId);
        boutiqueService.deleteBoutique(boutiqueId);
        return ResponseEntity.ok().build();
    }

}
