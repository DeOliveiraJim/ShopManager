package com.manager.shopmanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.manager.shopmanager.model.interfaces.ValidationGroups.OnCreateValidation;
import com.manager.shopmanager.model.interfaces.ValidationGroups.OnPatchValidation;
import com.manager.shopmanager.validation.NotBlankOrNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Entity
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Null(groups = {OnCreateValidation.class, OnPatchValidation.class})
    private Integer id;

    @NotBlank(groups = OnCreateValidation.class)
    @NotBlankOrNull(groups = OnPatchValidation.class)
    private String name;

    @NotBlank(groups = OnCreateValidation.class)
    @NotBlankOrNull(groups = OnPatchValidation.class)
    private String openingTime;

    @NotNull(groups = OnCreateValidation.class)
    private Boolean vacation;

    @Null(groups = {OnCreateValidation.class, OnPatchValidation.class})
    private Timestamp creationDate;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Product> products = new LinkedList<>();

    @JsonProperty(access = Access.READ_ONLY)
    private Integer nbCategories = 0;

    @JsonProperty(access = Access.READ_ONLY)
    private Integer nbProducts = 0;

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(String openingTime) {
        this.openingTime = openingTime;
    }

    public Boolean onVacation() {
        return vacation;
    }

    public void setVacation(boolean vacation) {
        this.vacation = vacation;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public List<Product> getProducts() {
        return new LinkedList<>(products);
    }

    public void addProduct(Product product) {
        this.products.add(product);
        updateNbs();
    }

    public void removeProduct(Product product) {
        this.products.remove(product);
        updateNbs();
    }

    public Product getProduct(int productId) {
        Product product = null;
        for (Product p : products) {
            if (p.getId() == productId) {
                product = p;
                break;
            }
        }
        return product;
    }

    public void modifyShop(Shop input) {
        if (input.getName() != null)
            setName(input.getName());
        if (input.getOpeningTime() != null)
            setOpeningTime(input.getOpeningTime());
        if (input.onVacation() != null)
            setVacation(input.onVacation());
    }

    public Integer getNbCategories() {
        return nbCategories;
    }

    public void updateNbs() {
        Set<Category> cats = new HashSet<>();
        for (Product p : products) {
            cats.addAll(p.getCategories());
        }
        this.nbCategories = cats.size();
        this.nbProducts = getProducts().size();
    }

    @Override
    public String toString() {
        return "Shop{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", openingTime='" + openingTime + '\'' +
                ", vacation=" + vacation +
                ", creationDate=" + creationDate +
                ", nbCategories=" + nbCategories +
                '}';
    }
}
