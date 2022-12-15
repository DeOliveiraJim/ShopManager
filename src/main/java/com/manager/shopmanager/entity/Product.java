package com.manager.shopmanager.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.manager.shopmanager.entity.interfaces.ValidationGroups.OnCreateValidation;
import com.manager.shopmanager.entity.interfaces.ValidationGroups.OnPatchValidation;
import com.manager.shopmanager.validation.NotBlankOrEmptyOrNull;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = Access.READ_ONLY)
    private Integer id;

    @NotBlank(groups = OnCreateValidation.class)
    @NotBlankOrEmptyOrNull(groups = OnPatchValidation.class)
    private String name;

    @NotNull(groups = OnCreateValidation.class)
    @PositiveOrZero
    private Integer price;

    @Column(columnDefinition = "TEXT")
    private String description = "";

    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH })
    @JoinTable(name = "product_categories", joinColumns = @JoinColumn(name = "products_id"))
    @Valid
    private Set<Category> categories = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Category> getCategories() {
        return new HashSet<>(categories);
    }

    public void setCategories(Set<Category> l) {
        categories = l;
    }

    public void addCategory(Category c) {
        categories.add(c);
    }

    public void modifyProduct(Product input) {
        if (input.getName() != null)
            setName(input.getName());
        if (input.getCategories() != null)
            setCategories(input.getCategories());
        if (input.getDescription() != null)
            setDescription(input.description);
        if (input.getPrice() != null)
            setPrice(input.getPrice());
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
