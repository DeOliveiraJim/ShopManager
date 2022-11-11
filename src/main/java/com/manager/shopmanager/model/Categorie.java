package com.manager.shopmanager.model;

import com.manager.shopmanager.interfaces.ValidationGroups;
import com.manager.shopmanager.validation.NotBlankOrNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

@Entity
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Null
    private Integer id;

    @NotBlank(groups = ValidationGroups.OnCreateValidation.class)
    @NotBlankOrNull(groups = ValidationGroups.OnPatchValidation.class)
    private String nom;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getId() {
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
