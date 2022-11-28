package com.manager.shopmanager.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.manager.shopmanager.model.Category;
import com.manager.shopmanager.model.interfaces.ValidationGroups.OnCreateValidation;
import com.manager.shopmanager.model.interfaces.ValidationGroups.OnPatchValidation;
import com.manager.shopmanager.repository.CategoryRepository;

@RestController
@RequestMapping("/categories")
public class CategoryRestController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<Category>> getCategories() {
        return new ResponseEntity<>(categoryRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Category> getCategory(@PathVariable(value = "id") int categoryId) {
        Optional<Category> opCat = categoryRepository.findById(categoryId);
        if (opCat.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(opCat.get(), HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Category> addCategory(
            @Validated(OnCreateValidation.class) @RequestBody Category input) {
        return new ResponseEntity<>(categoryRepository.save(input), HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Category> renameCategory(@PathVariable(value = "id") int categoryId,
            @Validated(OnPatchValidation.class) @RequestBody Category input) {
        Optional<Category> opCat = categoryRepository.findById(categoryId);
        if (opCat.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Category category = opCat.get();
        category.setName(input.getName());
        return new ResponseEntity<>(categoryRepository.save(category), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Object> removeCategory(@PathVariable(value = "id") int categoryId) {
        Optional<Category> opCat = categoryRepository.findById(categoryId);
        if (opCat.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        categoryRepository.delete(opCat.get());
        return ResponseEntity.ok().build();
    }
}
