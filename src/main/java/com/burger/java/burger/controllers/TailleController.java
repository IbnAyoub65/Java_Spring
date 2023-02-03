package com.burger.java.burger.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.burger.java.burger.models.Taille;
import com.burger.java.burger.services.BoissonService;

@Controller
public class TailleController {
    @Autowired
    private BoissonService tailleBoisson;


    @GetMapping("/admin/boisson/add-taille")
    public String viewTaille(Model model){

        Taille taille = new Taille();
        model.addAttribute("taille", taille);
        return "boisson/addTaille";
        
    }

    @GetMapping("/admin/taille")
    public String listTaille(Model model){

        List<Taille> tailles = tailleBoisson.getAllTailles();
        model.addAttribute("tailles",tailles);
        return "/boisson/listTaille";

    }

    @PostMapping("/admin/boisson/add-taille")
    public String adddTaille(@ModelAttribute("taille")Taille taille, Model model){
        
        if(taille == null){
            model.addAttribute("error", "taille inexistante");
            return "boisson/addTaille";
        }

        boolean valide =true;
        if(taille.getNom()== null || taille.getNom().equals("")){
            model.addAttribute("error","nom obligatoire");
            valide = false;
        }

        if(taille.getPrix()== 0){
            model.addAttribute("error","prix obligatoire");
            valide = false;
        }

        if(valide){

            tailleBoisson.addTaille(taille);
            return "redirect:/admin/taille";
        }

        return "redirect:/admin/boisson/add-taille";
    }
}
