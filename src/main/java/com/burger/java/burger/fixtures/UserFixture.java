package com.burger.java.burger.fixtures;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.burger.java.burger.models.Admin;
import com.burger.java.burger.models.Role;
import com.burger.java.burger.models.User;
import com.burger.java.burger.services.UserService;

@Component
public class UserFixture {
    
    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public void loadDefaultAdmin(){

        Admin user = new Admin();
        user.setEmail("admin@mail");
        user.setNom("admin");
        user.setPrenom("Yaya");
        user.setPassword(encoder.encode("passer@123"));
        Role role = userService.findRoleByLibelle("ADMIN");
        user.setRole(role);
        userService.saveUser(user);

    }

    public void loadDefaultUser(){

        User user = new User();
        user.setEmail("user@mail");
        user.setNom("user");
        user.setPrenom("Yaya");
        user.setPassword(encoder.encode("passer@123"));
        Role role = userService.findRoleByLibelle("USER");
        user.setRole(role);
        userService.saveUser(user);

    }
}
