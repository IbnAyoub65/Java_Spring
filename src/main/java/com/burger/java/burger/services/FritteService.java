package com.burger.java.burger.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.burger.java.burger.models.Fritte;
import com.burger.java.burger.repositories.FritteRepositories;

import lombok.extern.java.Log;

@Service
@Log
public class FritteService {

   @Autowired
   private FritteRepositories fritteRepositories;

   public Fritte ajoutFritte(Fritte fritte){

    try {
        fritteRepositories.save(fritte);
        return fritte;
    } catch (Exception e) {
        log.severe(e.getLocalizedMessage());
        throw e;
    }
    
   }

   public List<Fritte> listFritte(){

    return fritteRepositories.findAll();

   }

    
}
