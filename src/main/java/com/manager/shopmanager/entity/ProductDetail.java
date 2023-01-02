package com.manager.shopmanager.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.manager.shopmanager.entity.interfaces.ValidationGroups.OnCreateValidation;
import com.manager.shopmanager.entity.interfaces.ValidationGroups.OnPatchValidation;
import com.manager.shopmanager.validation.LanguageString;

@Entity
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;

    @NotBlank(groups = { OnCreateValidation.class, OnPatchValidation.class })
    @LanguageString(groups = { OnCreateValidation.class, OnPatchValidation.class })
    private String lang;

    @NotBlank(groups = { OnCreateValidation.class, OnPatchValidation.class })
    @Size(min = 1, max = 255)
    private String name;

    @Column(columnDefinition = "TEXT")
    @Size(min = 0, max = 65535)
    private String description = "";

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null)
            this.description = "";
        else
            this.description = description;
    }

    @Override
    public String toString() {
        return "ProductDetail [id=" + id + ", lang=" + lang + ", name=" + name + ", description=" + description + "]";
    }
}