package com.manager.shopmanager.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.manager.shopmanager.entity.interfaces.ValidationGroups.OnCreateValidation;
import com.manager.shopmanager.entity.interfaces.ValidationGroups.OnPatchValidation;
import com.manager.shopmanager.validation.NotBlankOrEmptyOrNull;
import com.manager.shopmanager.validation.ValidOpeningTimes;

@Entity
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = Access.READ_ONLY)
    private Integer id;

    @NotBlank(groups = OnCreateValidation.class)
    @NotBlankOrEmptyOrNull(groups = OnPatchValidation.class)
    @Size(min = 1, max = 255)
    private String name;

    @NotEmpty(groups = OnCreateValidation.class)
    @NotBlankOrEmptyOrNull(groups = OnPatchValidation.class)
    @Valid
    @ValidOpeningTimes(groups = { OnCreateValidation.class, OnPatchValidation.class })
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OpeningTime> openingTimes;

    @NotNull(groups = OnCreateValidation.class)
    private Boolean vacation;

    @JsonProperty(access = Access.READ_ONLY)
    private Timestamp creationDate;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Product> products = new LinkedList<>();

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

    public List<OpeningTime> getOpeningTimes() {
        return openingTimes;
    }

    public void setOpeningTimes(List<OpeningTime> openingTimes) {
        if (this.openingTimes != null) {
            // pour éviter une erreur hibernate lors du patch
            this.openingTimes.clear();
            this.openingTimes.addAll(openingTimes);
            return;
        }
        this.openingTimes = openingTimes;
    }

    public Boolean getVacation() {
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
        if (input.getOpeningTimes() != null)
            setOpeningTimes(input.getOpeningTimes());
        if (input.getVacation() != null)
            setVacation(input.getVacation());
    }

    public Integer getNbCategories() {
        Set<Category> cats = new HashSet<>();
        for (Product p : products) {
            cats.addAll(p.getCategories());
        }
        return cats.size();
    }

    public void updateNbs() {
        this.nbProducts = getProducts().size();
    }

    @Override
    public String toString() {
        return "Shop{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", openingTimes='" + openingTimes + '\'' +
                ", vacation=" + vacation +
                ", creationDate=" + creationDate +
                // ", nbCategories=" + nbCategories +
                '}';
    }
}
