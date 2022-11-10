package com.manager.shopmanager.repository;

import com.manager.shopmanager.model.Categorie;
import com.manager.shopmanager.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitRepository extends JpaRepository<Produit, Integer> {
}
