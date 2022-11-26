package com.manager.shopmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manager.shopmanager.model.Shop;

public interface ShopRepository extends JpaRepository<Shop, Integer> {
}
