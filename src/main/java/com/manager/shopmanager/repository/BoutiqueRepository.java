package com.manager.shopmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manager.shopmanager.model.Boutique;

public interface BoutiqueRepository extends JpaRepository<Boutique, Integer> {
}
