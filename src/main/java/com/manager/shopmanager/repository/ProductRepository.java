package com.manager.shopmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manager.shopmanager.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
