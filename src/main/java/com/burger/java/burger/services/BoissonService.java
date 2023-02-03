package com.burger.java.burger.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.burger.java.burger.models.Boisson;
import com.burger.java.burger.models.Marque;
import com.burger.java.burger.models.Taille;
import com.burger.java.burger.repositories.BoissonRepositories;
import com.burger.java.burger.repositories.MarqueRepositories;
import com.burger.java.burger.repositories.TailleRepositories;

import lombok.extern.java.Log;

@Service
@Log
public class BoissonService {
    
    @Autowired
    private BoissonRepositories boissonRepositories;

    @Autowired
    private MarqueRepositories marqueRepositories;

    @Autowired
    private TailleRepositories tailleRepositories;

    public Boisson addBoisson(Boisson boisson){
        try {
            boissonRepositories.save(boisson);
            return boisson;
        } catch (Exception e) {
            log.severe(e.getLocalizedMessage());
            throw e;
        }
    }

    public Taille addTaille(Taille taille){

        try{
            tailleRepositories.save(taille);
            return taille;
         } catch (Exception e){
            log.severe(e.getLocalizedMessage());
            throw e;
        }
    }

    public List<Boisson> listBoisson(){
        return boissonRepositories.findAll();       
    }

    public List<Marque> getAllMarques(){
        return marqueRepositories.findAll();
    }

    public List<Taille> getAllTailles(){
        return tailleRepositories.findAll();
    }
}
