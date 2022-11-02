package com.manager.shopmanager.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Produit_Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProduit;
    private int idCategorie;

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    @Override
    public String toString() {
        return "Produit_Categorie{" +
                "idProduit=" + idProduit +
                ", idCategorie=" + idCategorie +
                '}';
    }
}
