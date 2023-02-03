package com.burger.java.burger.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Boisson {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="marque" , referencedColumnName = "id")
    private Marque marque;

    @ManyToOne
    @JoinColumn(name="taille" , referencedColumnName = "id")
    private Taille taille;
    
    private String nom;
    private String image;
    private int prix;
    private int quantity;
    
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
    public Taille getTaille() {
        return taille;
    }
    public void setTaille(Taille taille) {
        this.taille = taille;
    }
    public Marque getMarque() {
        return marque;
    }
    public void setMarque(Marque marque) {
        this.marque = marque;
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
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
