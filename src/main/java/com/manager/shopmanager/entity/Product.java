package com.manager.shopmanager.entity;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.manager.shopmanager.entity.interfaces.ValidationGroups.OnCreateValidation;
import com.manager.shopmanager.entity.interfaces.ValidationGroups.OnPatchValidation;
import com.manager.shopmanager.validation.NotBlankOrEmptyOrNull;
import com.manager.shopmanager.validation.ValidDetails;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = Access.READ_ONLY)
    private Integer id;

    @NotEmpty(groups = OnCreateValidation.class)
    @NotBlankOrEmptyOrNull(groups = OnPatchValidation.class)
    @Valid
    @ValidDetails(groups = { OnCreateValidation.class, OnPatchValidation.class })
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductDetail> details = new LinkedList<>();

    @NotNull(groups = OnCreateValidation.class)
    @PositiveOrZero
    private Float price;

    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH })
    @JoinTable(name = "product_categories", joinColumns = @JoinColumn(name = "products_id"))
    @Valid
    private Set<Category> categories = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public List<ProductDetail> getDetails() {
        return details;
    }

    public void setDetails(List<ProductDetail> details) {
        this.details = details;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Set<Category> getCategories() {
        return categories == null ? null : new HashSet<>(categories);
    }

    public void setCategories(Set<Category> l) {
        categories = l;
    }

    public void addCategory(Category c) {
        categories.add(c);
    }

    public void modifyProduct(Product input) {
        if (input.getDetails() != null)
            setDetails(details);
        if (input.getCategories() != null)
            setCategories(input.getCategories());
        if (input.getPrice() != null)
            setPrice(input.getPrice());
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", details=" + details + ", price=" + price + ", categories=" + categories + "]";
    }

}
