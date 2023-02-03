package com.burger.java.burger.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.burger.java.burger.models.Burger;
import com.burger.java.burger.models.Fritte;
import com.burger.java.burger.models.Menu;
import com.burger.java.burger.models.Menu_Burger;
import com.burger.java.burger.models.Menu_Fritte;
import com.burger.java.burger.models.Menu_Taille;
import com.burger.java.burger.models.Taille;
import com.burger.java.burger.repositories.BurgerRepositories;
import com.burger.java.burger.repositories.FritteRepositories;
import com.burger.java.burger.repositories.MenuBurgerRepositories;
import com.burger.java.burger.repositories.MenuFritteRepositories;
import com.burger.java.burger.repositories.MenuRepositories;
import com.burger.java.burger.repositories.MenuTailleRepositories;
import com.burger.java.burger.repositories.TailleRepositories;

import lombok.extern.java.Log;

@Service
@Log
public class MenuService {

    @Autowired
    private MenuRepositories menuRepositories;
    
    @Autowired
    private MenuBurgerRepositories menuBurgerRepositories;

    @Autowired
    private MenuFritteRepositories menuFritteRepositories;

    @Autowired
    private MenuTailleRepositories menuTailleRepositories;

    @Autowired
    private BurgerRepositories burgerRepositories;

    @Autowired
    private TailleRepositories tailleRepositories;

    @Autowired
    private FritteRepositories fritteRepositories;

    //########### Ajout Menu ############

    public Menu addMenu(Menu menu){
        try {
            menuRepositories.save(menu);
            return menu;
        } catch (Exception e) {
           
            log.severe(e.getLocalizedMessage());
            throw e;
        }
    }

    public List<Menu> getAllMenus(){
        return menuRepositories.findAll();
    }

    //############## Ajout Menu_Burger ##################

    public Menu_Burger addMenuBurger(Menu_Burger menuBurger){

        try {
            menuBurgerRepositories.save(menuBurger);
            return menuBurger;
        } catch (Exception e) {
            log.severe(e.getLocalizedMessage());
            throw e;
        }
    }

    public List<Menu_Burger> addListMenuBurger(List<Menu_Burger>menuBurgers){

        try {
            menuBurgerRepositories.saveAll(menuBurgers);
            return menuBurgers;
        } catch (Exception e) {
            log.severe(e.getLocalizedMessage());
            throw e;
        }
    }
    public List<Burger> getAllBurgers(){
        return burgerRepositories.findAll();
    }
  //############ Ajout Menu_Fritte ##########//
  public Menu_Fritte  addMenuFritte(Menu_Fritte menuFritte){
    try {
        menuFritteRepositories.save(menuFritte);
        return menuFritte;
    } catch (Exception e) {
        log.severe(e.getLocalizedMessage());
        throw e;
    }
  }

  public List<Menu_Fritte> addListMenuFritte(List<Menu_Fritte>menuFrittes){

    try {
        menuFritteRepositories.saveAll(menuFrittes);
        return menuFrittes;  
    }catch (Exception e) {
        log.severe(e.getLocalizedMessage());
        throw e;
    }
  }

  public List<Fritte> getAllFrittes(){
    return fritteRepositories.findAll();
}

  //############# Ajoout Menu_Taille #################//#

  public List<Menu_Taille> addListMenuTaille(List<Menu_Taille>menuTailles){

    try {
        menuTailleRepositories.saveAll(menuTailles);
        return menuTailles;  
    }catch (Exception e) {
        log.severe(e.getLocalizedMessage());
        throw e;
    }
  }

  public List<Taille> getAllTailles(){
    return tailleRepositories.findAll();
}
}