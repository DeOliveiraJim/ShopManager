package com.manager.shopmanager.model;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.manager.shopmanager.interfaces.ValidationGroups.OnCreateValidation;
import com.manager.shopmanager.interfaces.ValidationGroups.OnPatchValidation;
import com.manager.shopmanager.validation.NotBlankOrNull;

@Entity
public class Boutique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Null(groups = { OnCreateValidation.class, OnPatchValidation.class })
    private Integer id;

    @NotBlank(groups = OnCreateValidation.class)
    @NotBlankOrNull(groups = OnPatchValidation.class)
    private String nom;

    @NotBlank(groups = OnCreateValidation.class)
    @NotBlankOrNull(groups = OnPatchValidation.class)
    private String horaires;

    @NotNull(groups = OnCreateValidation.class)
    private boolean conge;

    @Null(groups = OnCreateValidation.class)
    private Timestamp dateCreation;

    @JsonProperty(access = Access.WRITE_ONLY)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Produit> produits = new LinkedList<>();

    @Null
    private Integer nbCategories;

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getHoraires() {
        return horaires;
    }

    public void setHoraires(String horaires) {
        this.horaires = horaires;
    }

    public boolean isConge() {
        return conge;
    }

    public void setConge(boolean conge) {
        this.conge = conge;
    }

    public Timestamp getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Timestamp dateCreation) {
        this.dateCreation = dateCreation;
    }

    public List<Produit> getProduits() {
        return new LinkedList<>(produits);
    }

    public void addProduit(Produit produit) {
        this.produits.add(produit);
    }

    public void removeProduit(Produit produit) {
        this.produits.remove(produit);
    }

    public Produit getProduit(int produitId) {
        Produit produit = null;
        for (Produit p : produits) {
            if (p.getId() == produitId) {
                produit = p;
                break;
            }
        }
        return produit;
    }

    public void modifyBoutique(Boutique input) {
        if (input.getNom() != null)
            setNom(input.getNom());
        if (input.getHoraires() != null)
            setHoraires(input.getHoraires());
        if (input.getDateCreation() != null)
            setDateCreation(input.getDateCreation());
    }

    public Integer getNbCategories() {
        return nbCategories;
    }

    public void setNbCategories(int nbCategories) {
        this.nbCategories = nbCategories;
    }

    @Override
    public String toString() {
        return "Boutique{" +
                "id='" + id + '\'' +
                ", nom='" + nom + '\'' +
                ", horaires='" + horaires + '\'' +
                ", conge=" + conge +
                ", dateCreation=" + dateCreation +
                ", nbCategories=" + nbCategories +
                '}';
    }
}
