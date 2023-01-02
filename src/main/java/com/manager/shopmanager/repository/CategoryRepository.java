package com.manager.shopmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manager.shopmanager.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
