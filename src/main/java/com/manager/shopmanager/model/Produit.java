package com.manager.shopmanager.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.PositiveOrZero;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.manager.shopmanager.interfaces.ValidationGroups.OnCreateValidation;
import com.manager.shopmanager.interfaces.ValidationGroups.OnPatchValidation;
import com.manager.shopmanager.validation.NotBlankOrNull;

@Entity
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Null(groups = { OnCreateValidation.class, OnPatchValidation.class })
    private Integer id;

    @NotBlank(groups = OnCreateValidation.class)
    @NotBlankOrNull(groups = OnPatchValidation.class)
    private String nom;

    @NotBlank(groups = OnCreateValidation.class)
    @NotBlankOrNull(groups = OnPatchValidation.class)
    @PositiveOrZero(groups = OnCreateValidation.class)
    private Integer prix;

    @NotBlankOrNull
    private String description;

    @JsonProperty(access = Access.WRITE_ONLY)
    @OneToMany(cascade = CascadeType.ALL)
    private List<Categorie> categories = new LinkedList<>();

    public Integer getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Categorie> getCategories() {
        return new LinkedList<>(categories);
    }

    public void setCategories(List<Categorie> l) {
        categories = l;
    }

    public void addCategorie(Categorie c) {
        categories.add(c);
    }

    public void modifyProduit(Produit input) {
        if (input.getNom() != null)
            setNom(input.getNom());
        if (input.getCategories() != null)
            setCategories(input.getCategories());
        if (input.getDescription() != null)
            setDescription(input.description);
        if (input.getPrix() > -1)
            setPrix(input.getPrix());
    }

    @Override
    public String toString() {
        return "Produit{" +
                "id='" + id + '\'' +
                ", nom='" + nom + '\'' +
                ", prix=" + prix +
                ", description='" + description + '\'' +
                '}';
    }
}
