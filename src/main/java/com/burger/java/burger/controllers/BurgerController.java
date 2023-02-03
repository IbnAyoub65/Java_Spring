package com.burger.java.burger.controllers;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.burger.java.burger.models.Burger;
import com.burger.java.burger.repositories.BurgerRepositories;
import com.burger.java.burger.services.BurgerService;

@Controller
public class BurgerController {

@Autowired
private BurgerRepositories burgerRepositories;

@Autowired
private BurgerService burgerService;

@GetMapping("/admin/burger")
public String getListBurger(Model model,@RequestParam(name ="page",defaultValue ="0" )int p){
    Page <Burger> burgers = burgerRepositories.findAll(PageRequest.of(p, 2));
    int NumPage = burgers.getTotalPages();
    int pages[]=new int[NumPage];
    for(int i=0;i<NumPage;i++)pages[i]=i;
    model.addAttribute("pages", pages);
    model.addAttribute("burgers",burgers);
    return "burger/burger";
    
}

@GetMapping("/admin/add-burger")
public String getViewAddBurger(Model model){
    Burger burger = new Burger();
    model.addAttribute("burger",burger);
    return "burger/addBurger";
}

@GetMapping("/admin/burger/edit/{id}")
public String detailBurger(@PathVariable Long id,Model model){
   Burger b = burgerService.findBurgerById(id);
		model.addAttribute("burger", b);
		return "burger/editBurger";
}  

@PostMapping("/update/{id}")
public String updateBurger(@PathVariable("id") long id, Model model,Burger burger,BindingResult result,
@RequestParam("fileImage")MultipartFile fileImage ) throws IOException {

    if (result.hasErrors()) { 
    burger.setId(id); 
    return "burger/editBurger"; 
    } 
    String imageUUID;
    if(!fileImage.isEmpty()){
        imageUUID = fileImage.getOriginalFilename();
        Path fileNameAndPath = Paths.get(uploadDirectory, imageUUID);
        Files.write(fileNameAndPath, fileImage.getBytes());
        burger.setImage(imageUUID);
       
        }
    burgerService.addBurger(burger);
    return "redirect:/admin/burger";
}

    
public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/images"; 


@PostMapping("/add-burger")
public String addBurger(@ModelAttribute("burger") @Valid Burger burger,BindingResult bindingResult,Model model
        ,@RequestParam("fileImage")MultipartFile fileImage ) throws IOException {


    if(burger == null){
        model.addAttribute("error","burger incorrect");
        return "burger/addBurger";
    }

    
 
    if(bindingResult.hasErrors()){
        return "burger/addBurger";
    }

    boolean valide = true;
    if(burger.getNom()==null || burger.getNom().equals("")){
        model.addAttribute("error","nom obligatoire");
       valide = false;
    }
    if(burger.getDescription()==null || burger.getDescription().equals("")){
        model.addAttribute("error","description obligatoire");
       valide = false;
    }
 /*    if(fileImage.isEmpty()){
        return "burger/list-burger";
    }  */
    if(burger.getPrix()== 0){
        model.addAttribute("error","prix obligatoire");
       valide = false;
    }
    String imageUUID;
    if(valide){
     if(!fileImage.isEmpty()){
        imageUUID = fileImage.getOriginalFilename();
        Path fileNameAndPath = Paths.get(uploadDirectory, imageUUID);
        Files.write(fileNameAndPath, fileImage.getBytes());
        burger.setImage(imageUUID);
        burger.setEtat("en_cours");
        burgerService.addBurger(burger);
        }
       
    return "redirect:/admin/burger";
    }
    return "redirect:/admin/add-burger";
}



}
