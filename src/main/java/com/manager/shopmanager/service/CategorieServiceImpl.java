package com.manager.shopmanager.service;

import com.manager.shopmanager.model.Categorie;
import com.manager.shopmanager.repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class CategorieServiceImpl {


    @Autowired
    private CategorieRepository categorieRepository;



    public boolean deleteCategorie(int boutiqueId) {
        categorieRepository.deleteById(boutiqueId);
        return true;
    }

    public Iterable<Categorie> getAllCategorie() {
        return categorieRepository.findAll();
    }

    public Optional<Categorie> getCategorie(int id) {
        return categorieRepository.findById(id);
    }

    public Categorie saveCategorie(Categorie c) {
        return categorieRepository.save(c);
    }


}
