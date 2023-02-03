package com.burger.java.burger.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.burger.java.burger.controllers.dto.MenuDTO;
import com.burger.java.burger.models.Burger;
import com.burger.java.burger.models.Fritte;
import com.burger.java.burger.models.Menu;
import com.burger.java.burger.models.Menu_Burger;
import com.burger.java.burger.models.Menu_Fritte;
import com.burger.java.burger.models.Menu_Taille;
import com.burger.java.burger.models.Taille;
import com.burger.java.burger.services.BoissonService;
import com.burger.java.burger.services.BurgerService;
import com.burger.java.burger.services.FritteService;
import com.burger.java.burger.services.MenuService;


@Controller
public class MenuController {
    
    @Autowired
    private MenuService menuService;

    @Autowired
    private BoissonService boissonService;

    @Autowired
    private BurgerService burgerService;

    @Autowired
    private FritteService fritteService;


    @GetMapping("/admin/add-menu")
    public String ajoutMenu(Model model){
        MenuDTO menuDTO = new MenuDTO();
        Menu menu = new Menu();
        menuDTO.setMenu(menu);
        List<Burger> burgers =burgerService.getAllBurgers();
        List<Taille> tailles = boissonService.getAllTailles();
        List<Fritte> frittes =fritteService.listFritte();
        model.addAttribute("menu", menuDTO);
        model.addAttribute("burgers", burgers);
        model.addAttribute("tailles", tailles);
        model.addAttribute("frittes", frittes);
        

        return "menu/addMenu";
    } 

    public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/images"; 
    
    @PostMapping("/add-menu")
    public String addMenu(@ModelAttribute("menu")MenuDTO menuDTO,Model model,
    @RequestParam("fileImage")MultipartFile fileImage) throws IOException{

            Menu menu = menuService.addMenu(menuDTO.getMenu());

            if(menu == null || menu.getNom()==null || menu.getNom().equals("")){
                model.addAttribute("error Nom","champ obligatoire");
                return "menu/addMenu";
            } 
            if(menu == null || menu.getDescription()==null || menu.getDescription().equals("")){
                model.addAttribute("error Description","champ obligatoire");
                return "menu/addMenu";
            } 
            
            boolean valide =true;
            String imageUID;
            if(valide){
                if(!fileImage.isEmpty()){
                    imageUID = fileImage.getOriginalFilename();
                    Path fileNameAndPath = Paths.get(uploadDirectory, imageUID);
                    Files.write(fileNameAndPath,fileImage.getBytes());
                    menu.setImage(imageUID);
                    menuService.addMenu(menu);
                }
                List<Menu_Burger> menuBurgers = new ArrayList<>();
                List<Menu_Fritte> menuFrittes  = new ArrayList<>();
                List<Menu_Taille> menuTailles = new ArrayList<>();

                for(Burger burger: menuDTO.getBurgers()){
                    
                    Menu_Burger menuBurger = new Menu_Burger();
                    menuBurger.setBurger(burger);
                    menuBurger.setMenu(menu);
                    menuBurger.setQuantity(menuDTO.getQuantiteBurger());
                    menuBurgers.add(menuBurger);
                }

                for(Taille taille: menuDTO.getTailles()){

                    Menu_Taille menuTaille = new Menu_Taille();
                    menuTaille.setTaille(taille);
                    menuTaille.setMenu(menu);
                    menuTaille.setQuantity(menuDTO.getQuantiteTaille());
                    menuTailles.add(menuTaille);
                }

                for(Fritte fritte : menuDTO.getFrittes()){

                    Menu_Fritte menuFritte  = new Menu_Fritte();
                    menuFritte.setFritte(fritte);
                    menuFritte.setMenu(menu);
                    menuFritte.setQuantity(menuDTO.getQuantiteFritte());
                    menuFrittes.add(menuFritte);

                }

                menuService.addListMenuBurger(menuBurgers);
                menuService.addListMenuFritte(menuFrittes);
                menuService.addListMenuTaille(menuTailles);

                if(menu.getId() != null){

                    return "redirect:/add-menu";
                }else{
                    model.addAttribute("error", "Echec de l'Operation");
                    return "redirect:/add-menu";
                }
            }
            return "redirect:/add-menu";
    }
    
}
