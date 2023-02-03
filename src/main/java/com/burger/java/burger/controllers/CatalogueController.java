package com.burger.java.burger.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.burger.java.burger.models.Burger;
import com.burger.java.burger.models.Menu;
import com.burger.java.burger.services.BurgerService;
import com.burger.java.burger.services.MenuService;



@Controller
public class CatalogueController {

@Autowired
private BurgerService burgerService;

@Autowired 
private MenuService menuService;

@GetMapping("/")
public String catalogue(Model model){
    List<Burger> burgers = burgerService.getAllBurgers();
    model.addAttribute("burgers",burgers);
    List<Menu> menus = menuService.getAllMenus();
    model.addAttribute("menus",menus);
    return "catalogue/index";

}    

@GetMapping("/index")
public String defaultPage(Model model){
    List<Burger> burgers = burgerService.getAllBurgers();
    model.addAttribute("burgers",burgers);
    List<Menu> menus = menuService.getAllMenus();
    model.addAttribute("menus",menus);
    return "catalogue/index";

} 
@GetMapping("/catalogue/detail/{id}")
public String detailBurger(@PathVariable Long id,Model model){
    Burger burger = burgerService.findBurgerById(id);
        model.addAttribute("burger",burger);
        return "catalogue/detailBurger";
}
  
/* @GetMapping("/")
public String getListBurger(Model model){
    List<Burger> burgers = burgerService.getAllBurgers();
    model.addAttribute("burgers",burgers);
    return "catalogue/index";
    
} */

}
