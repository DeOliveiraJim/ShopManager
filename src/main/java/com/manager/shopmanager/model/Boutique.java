package com.manager.shopmanager.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Boutique")
public class Boutique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String nom;
    private String horaires;
    private boolean conge;
    private Timestamp dateCreation;
    private int nbProduits;
    private int nbCategories;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public int getNbProduits() {
        return nbProduits;
    }

    public void setNbProduits(int nbProduits) {
        this.nbProduits = nbProduits;
    }

    public int getNbCategories() {
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
                ", nbProduits=" + nbProduits +
                ", nbCategories=" + nbCategories +
                '}';
    }
}
