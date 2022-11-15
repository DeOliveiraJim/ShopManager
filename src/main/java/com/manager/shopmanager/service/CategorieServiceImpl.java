package com.manager.shopmanager.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.manager.shopmanager.model.Categorie;
import com.manager.shopmanager.repository.CategorieRepository;

@Service
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

    public Optional<Categorie> getCategorieByName(String name) {
        Categorie c = new Categorie();
        c.setNom(name);
        return categorieRepository.findOne(Example.of(c));
    }

    public Categorie saveCategorie(Categorie c) {
        return categorieRepository.save(c);
    }

}
