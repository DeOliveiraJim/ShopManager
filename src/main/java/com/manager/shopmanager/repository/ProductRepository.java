package com.manager.shopmanager.repository;

import com.manager.shopmanager.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
