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

import com.burger.java.burger.models.Fritte;
import com.burger.java.burger.services.FritteService;

@Controller
public class FritteController {
    
    @Autowired
    private FritteService fritteService;

    @GetMapping("/add-fritte")
    public String getViewsAddFritte(Model model){
        Fritte fritte = new Fritte();
        model.addAttribute("fritte",fritte);
        return "fritte/addFritte";
    }

    @GetMapping("/fritte")
    public String getListFritte(Model model){
        List<Fritte> frittes = fritteService.listFritte();
        model.addAttribute("frittes", frittes);
        return "fritte/listFritte";
    }

    public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/images"; 

    @PostMapping("/add-fritte")
    public String addFritte(@ModelAttribute("fritte")Fritte fritte,Model model,
        @RequestParam("fileImage")MultipartFile fileImage) throws IOException{

            if(fritte == null){
                model.addAttribute("error", "fritte incorrect");
                return "fritte/addFritte";
            }

            boolean valide = true;
            if(fritte.getNom()==null || fritte.getNom().equals("")){
                model.addAttribute("error", "nom obligatoire");
                valide = false;
            }

            if(fritte.getPrix()== 0){
                model.addAttribute("error","prix obligatoire");
                valide =false;
            }
            String imageUUID;
            if(valide){
              if(!fileImage.isEmpty()){
                  imageUUID = fileImage.getOriginalFilename();
                  Path fileNameAndPath = Paths.get(uploadDirectory, imageUUID);
                  Files.write(fileNameAndPath, fileImage.getBytes());
                  fritte.setImage(imageUUID);
                  fritteService.ajoutFritte(fritte);
                  }     
              return "redirect:/fritte";
              }
              return "redirect:/add-fritte";
          }
            
 }

