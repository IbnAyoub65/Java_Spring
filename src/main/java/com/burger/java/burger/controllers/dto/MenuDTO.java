package com.burger.java.burger.controllers.dto;

import java.util.ArrayList;
import java.util.List;

import com.burger.java.burger.models.Burger;
import com.burger.java.burger.models.Fritte;
import com.burger.java.burger.models.Menu;
import com.burger.java.burger.models.Taille;

public class MenuDTO {
    
    private Menu menu;

    private int quantiteBurger;
    private int quantiteTaille;
    private int quantiteFritte;
    
    private List<Fritte> frittes = new ArrayList<>();
    private List<Burger> burgers = new ArrayList<>();
    private List<Taille> tailles = new ArrayList<>();
    
    public Menu getMenu() {
        return menu;
    }
    public void setMenu(Menu menu) {
        this.menu = menu;
    }
    public int getQuantiteBurger() {
        return quantiteBurger;
    }
    public void setQuantiteBurger(int quantiteBurger) {
        this.quantiteBurger = quantiteBurger;
    }
    public int getQuantiteTaille() {
        return quantiteTaille;
    }
    public void setQuantiteTaille(int quantiteTaille) {
        this.quantiteTaille = quantiteTaille;
    }
    public int getQuantiteFritte() {
        return quantiteFritte;
    }
    public void setQuantiteFritte(int quantiteFritte) {
        this.quantiteFritte = quantiteFritte;
    }
    public List<Fritte> getFrittes() {
        return frittes;
    }
    public void setFrittes(List<Fritte> frittes) {
        this.frittes = frittes;
    }
    public List<Burger> getBurgers() {
        return burgers;
    }
    public void setBurgers(List<Burger> burgers) {
        this.burgers = burgers;
    }
    public List<Taille> getTailles() {
        return tailles;
    }
    public void setTailles(List<Taille> tailles) {
        this.tailles = tailles;
    }
  
}
