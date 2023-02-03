package com.burger.java.burger.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Burger {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty
    private String nom;

    @NotNull
    private int prix;

   
    @NotEmpty
    private String description;

   
    private String image;

    private String etat;

    @OneToMany(mappedBy = "menu")
    private List<Menu_Burger> menuBurgers = new ArrayList<>();
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public int getPrix() {
        return prix;
    }
    public void setPrix(int prix) {
        this.prix = prix;
    }
    public List<Menu_Burger> getMenuBurgers() {
        return menuBurgers;
    }
    public void setMenuBurgers(List<Menu_Burger> menuBurgers) {
        this.menuBurgers = menuBurgers;
    }
    public String getEtat() {
        return etat;
    }
    public void setEtat(String etat) {
        this.etat = etat;
    }
    
}
