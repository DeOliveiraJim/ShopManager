package com.manager.shopmanager.model;

import com.manager.shopmanager.model.interfaces.ValidationGroups.OnCreateValidation;
import com.manager.shopmanager.model.interfaces.ValidationGroups.OnPatchValidation;
import com.manager.shopmanager.validation.NotBlankOrNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Null(groups = {OnCreateValidation.class, OnPatchValidation.class})
    private Integer id;

    @NotBlank(groups = OnCreateValidation.class)
    @NotBlankOrNull(groups = OnPatchValidation.class)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
