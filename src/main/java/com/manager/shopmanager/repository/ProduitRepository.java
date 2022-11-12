package com.manager.shopmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manager.shopmanager.model.Produit;

public interface ProduitRepository extends JpaRepository<Produit, Integer> {
}
