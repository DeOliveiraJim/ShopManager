package com.manager.shopmanager.repository;

import com.manager.shopmanager.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, Integer> {
}
