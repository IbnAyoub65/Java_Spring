package com.burger.java.burger.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.burger.java.burger.models.Boisson;
import com.burger.java.burger.models.Marque;
import com.burger.java.burger.models.Taille;
import com.burger.java.burger.services.BoissonService;

@Controller
public class BoissonController {
    
    @Autowired
    private BoissonService boissonService;

    @GetMapping("/admin/add-boisson")
    public String ajoutBoisson(Model model){

        Boisson boisson = new Boisson();
        List<Taille> tailles = boissonService.getAllTailles();
        List<Marque> marques = boissonService.getAllMarques();
        model.addAttribute("boisson", boisson);
        model.addAttribute("taille", tailles);
        model.addAttribute("marque", marques);

        return "boisson/addBoisson";
        
    }

    @GetMapping("/admin/boisson")
    public String listBoisson(Model model){
        List<Boisson> boissons = boissonService.listBoisson();
        model.addAttribute("boissons",boissons);
        return "boisson/listBoisson";

    }

    public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/images"; 

    @PostMapping("/admin/add-boisson")
    public String addBoisson(@ModelAttribute("boisson")Boisson boisson ,Model model
        ,@RequestParam("fileImage")MultipartFile fileImage ) throws IOException{

        if(boisson == null){
            model.addAttribute("error", "boisson incorrect");
            return "boisson/addBoisson";
        }

        boolean valide = true;
        if(boisson.getNom()== null || boisson.getNom().equals("")){
            model.addAttribute("error", "nom obligatoire");
            valide = false;
        }

        if(boisson.getPrix()== 0){
            model.addAttribute("error","prix obligatoire");
           valide = false;
        }

        if(boisson.getMarque()==null || boisson.getMarque().getId()==null){
            model.addAttribute("error marque", "marque obligatoire");
            valide = false;
        }

        if(boisson.getQuantity()== 0){

            model.addAttribute("error ","quantity obligatoire");
            valide = false;
        }

        if(boisson.getTaille()== null || boisson.getTaille().getId() == null){
            model.addAttribute("error taille", "taille obligatoire");
            valide = false;
        }
        String imageUUID;
          if(valide){
            if(!fileImage.isEmpty()){
                imageUUID = fileImage.getOriginalFilename();
                Path fileNameAndPath = Paths.get(uploadDirectory, imageUUID);
                Files.write(fileNameAndPath, fileImage.getBytes());
                boisson.setImage(imageUUID);
                boissonService.addBoisson(boisson);
                }     
            return "redirect:/admin/boisson";
            }
            return "redirect:/admin/add-boisson";
        }
    

}
