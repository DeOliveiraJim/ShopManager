package com.manager.shopmanager.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.manager.shopmanager.model.Categorie;
import com.manager.shopmanager.repository.CategorieRepository;

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
