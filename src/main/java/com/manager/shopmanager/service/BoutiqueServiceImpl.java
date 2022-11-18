package com.manager.shopmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manager.shopmanager.exceptions.ElementNotFoundException;
import com.manager.shopmanager.model.Boutique;
import com.manager.shopmanager.repository.BoutiqueRepository;

@Service
public class BoutiqueServiceImpl {

    @Autowired
    private BoutiqueRepository boutiqueRepository;

    public boolean deleteBoutique(int boutiqueId) {
        boutiqueRepository.deleteById(boutiqueId);
        return true;
    }

    public Iterable<Boutique> getAllboutiques() {
        return boutiqueRepository.findAll();
    }

    public Boutique getBoutique(int id) throws ElementNotFoundException {
        return boutiqueRepository.findById(id).orElseThrow(() -> new ElementNotFoundException());
    }

    public Boutique saveBoutique(Boutique b) {
        return boutiqueRepository.save(b);
    }

}
