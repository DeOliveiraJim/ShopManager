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

@Entity
public class Boutique {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotBlank
    private String nom;
    @NotBlank
    private String horaires;
    @NotNull
    private boolean conge;
    @NotNull
    private Timestamp dateCreation;
    @JsonProperty(access = Access.WRITE_ONLY)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Produit> produits = new LinkedList<>();
    @Null
    private Integer nbCategories;

    public int getId() {
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
