package com.manager.shopmanager.repository;

import com.manager.shopmanager.model.Categorie;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepository extends JpaRepository<Categorie, Integer> {
}
