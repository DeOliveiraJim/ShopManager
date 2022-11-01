package com.manager.shopmanager.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nom;
    private int prix;
    private String description;
    private int boutiqueAssocie;

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

    public int getBoutiqueAssocie() {
        return boutiqueAssocie;
    }

    public void setBoutiqueAssocie(int boutiqueAssocie) {
        this.boutiqueAssocie = boutiqueAssocie;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "id='" + id + '\'' +
                ", nom='" + nom + '\'' +
                ", prix=" + prix +
                ", description='" + description + '\'' +
                ", boutiqueAssocie=" + boutiqueAssocie +
                '}';
    }
}
