package com.burger.java.burger.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.burger.java.burger.models.Burger;
import com.burger.java.burger.models.Menu_Burger;
import com.burger.java.burger.repositories.BurgerRepositories;

import lombok.extern.java.Log;

@Service
@Log
public class BurgerService {

@Autowired
private BurgerRepositories burgerRepositories;


public Burger addBurger (Burger burger){
    
    
    try {
        burgerRepositories.save(burger); 
        return burger;
    } catch (Exception e) {
        log.severe(e.getLocalizedMessage());
        throw e;
    }
}

public Burger updateBurger(Burger burger, Long id){
    Burger burgerUpdate = burgerRepositories.findById(id).get();
    burgerUpdate.setNom(burger.getNom());
    burgerUpdate.setDescription(burger.getDescription());
    burgerUpdate.setPrix(burger.getPrix());
    burgerUpdate.setImage(burger.getImage());
    burgerUpdate.setEtat(burger.getEtat());
    return burgerRepositories.saveAndFlush(burgerUpdate);
}



public List<Burger> getAllBurgers(){
    return burgerRepositories.findAll();
}

public Burger findBurgerById(Long id){
    return burgerRepositories.findById(id).orElse(null);
}


    
}
