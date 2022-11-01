package com.manager.shopmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manager.shopmanager.model.Boutique;
import com.manager.shopmanager.repository.BoutiqueRepository;

@Service
public class BoutiqueServiceImpl {

    @Autowired
    private BoutiqueRepository boutiqueRepository;

    public boolean deleteboutique(int boutiqueId) {
        boutiqueRepository.deleteById(boutiqueId);
        return true;
    }

    public Iterable<Boutique> getAllboutiques() {
        return boutiqueRepository.findAll();
    }

    public void createBoutique(Boutique b) {
        boutiqueRepository.save(b);
    }

    private Boutique populateboutiqueEntity(Boutique boutiqueData) {
        Boutique boutique = new Boutique();
        boutique.setNbProduits(boutiqueData.getNbProduits());
        boutique.setHoraires(boutiqueData.getHoraires());
        boutique.setNom(boutiqueData.getNom());
        boutique.setDateCreation(boutiqueData.getDateCreation());
        boutique.setNbCategories(boutiqueData.getNbCategories());
        boutique.setConge(boutiqueData.isConge());
        return boutique;
    }
}
