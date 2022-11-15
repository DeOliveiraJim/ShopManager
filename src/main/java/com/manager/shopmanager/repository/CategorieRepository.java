package com.manager.shopmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manager.shopmanager.model.Categorie;

public interface CategorieRepository extends JpaRepository<Categorie, Integer> {
}
