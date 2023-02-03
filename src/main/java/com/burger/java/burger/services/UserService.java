package com.burger.java.burger.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.burger.java.burger.models.Admin;
import com.burger.java.burger.models.Role;
import com.burger.java.burger.models.User;
import com.burger.java.burger.repositories.AdminRepositories;
import com.burger.java.burger.repositories.RoleRepositories;
import com.burger.java.burger.repositories.UserRepositories;

@Service
public class UserService {
    
    private UserRepositories userRepositories;
    private RoleRepositories roleRepositories;

    @Autowired
    private AdminRepositories adminRepositories;
    
    @Autowired
    public UserService(UserRepositories userRepositories, RoleRepositories roleRepositories){
        this.userRepositories = userRepositories;
        this.roleRepositories = roleRepositories;
    }

    public User findUserByEmail(String email){
        return userRepositories.findByEmail(email);
    }

    public Admin findAdminByEmail(String email){
        return adminRepositories.findByEmail(email);
    }

    public Role findRoleByLibelle(String libelle){
        return roleRepositories.findByLibelle(libelle);
    }

    public User saveUser (User user) {
        try {
            userRepositories.save(user);
            return user;
        } catch (Exception e) {
            throw e;
        }
    }

}
