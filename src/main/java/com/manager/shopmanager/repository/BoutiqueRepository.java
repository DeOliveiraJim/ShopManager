package com.manager.shopmanager.repository;

import com.manager.shopmanager.model.Boutique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface BoutiqueRepository extends JpaRepository<Boutique,Integer> {
}
