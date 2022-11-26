package com.manager.shopmanager.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.manager.shopmanager.model.Category;
import com.manager.shopmanager.repository.CategoryRepository;

@Service
public class CategoryServiceImpl {

    @Autowired
    private CategoryRepository categoryRepository;

    public boolean deleteCategory(int shopId) {
        categoryRepository.deleteById(shopId);
        return true;
    }

    public Iterable<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategory(int id) {
        return categoryRepository.findById(id);
    }

    public Optional<Category> getCategoryByName(String name) {
        Category c = new Category();
        c.setName(name);
        return categoryRepository.findOne(Example.of(c));
    }

    public Category saveCategory(Category c) {
        return categoryRepository.save(c);
    }

}
