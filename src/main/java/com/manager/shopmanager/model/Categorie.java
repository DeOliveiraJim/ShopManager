package com.manager.shopmanager.model;

import javax.persistence.*;

@Entity
@Table(name = "Catégorie")
public class Categorie {

    private String nom;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Categorie{" +
                "nom='" + nom + '\'' +
                ", id=" + id +
                '}';
    }
}
