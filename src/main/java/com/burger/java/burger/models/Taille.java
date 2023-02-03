package com.burger.java.burger.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Taille {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "taille")
    private List<Boisson> boissons = new ArrayList<>();
   
    @OneToMany(mappedBy="taille")
    private List<Menu_Taille> menuTailles = new ArrayList<>();


    private int prix;
    private String nom;

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
    public List<Boisson> getBoissons() {
        return boissons;
    }
    public void setBoissons(List<Boisson> boissons) {
        this.boissons = boissons;
    }
    public int getPrix() {
        return prix;
    }
    public void setPrix(int prix) {
        this.prix = prix;
    }
}
