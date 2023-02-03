package com.burger.java.burger.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.burger.java.burger.models.Role;
import com.burger.java.burger.models.User;
import com.burger.java.burger.services.UserService;



@Controller
public class SecurityController {
    
    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @GetMapping("/login")
    public String getLoginView(){
        return "security/login";
    }

    @GetMapping("/admin/home")
    public String home(Model model,HttpServletRequest request) {
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        model.addAttribute("msg", "Bonjour" + user.getNom() + "" + user.getPrenom());
        
        return "admin/commande";
        
    }
    @GetMapping("/inscription")
    public String getViewAddBurger(Model model){
    User user = new User();
    model.addAttribute("user",user);
    return "security/inscription";
    }

    @PostMapping("/inscription")
    public String addUser(@ModelAttribute("user") @Valid User user,BindingResult bindingResult,Model model) {


    if(user == null){
        model.addAttribute("error","user incorrect");
        return "security/inscription";
    } 
    if(bindingResult.hasErrors()){
        return "security/inscription";
    }
    boolean valide = true;
    if(user.getNom()==null || user.getNom().equals("")){
        model.addAttribute("error","nom obligatoire");
       valide = false;
    }
    if(user.getPrenom()==null || user.getPrenom().equals("")){
        model.addAttribute("error","prenom obligatoire");
       valide = false;
    }
    if(user.getEmail()==null || user.getEmail().equals("")){
        model.addAttribute("error","email obligatoire");
       valide = false;
    }

    if(user.getPassword()==null || user.getPassword().equals("")){
        model.addAttribute("error","password obligatoire");
       valide = false;
    }

    if(valide){
        user.setPassword(encoder.encode(user.getPassword())); 
        Role role = userService.findRoleByLibelle("USER");
        user.setRole(role);
        userService.saveUser(user);
        
       
    return "redirect:/login";
    }
    return "redirect:/inscription";
}
}
