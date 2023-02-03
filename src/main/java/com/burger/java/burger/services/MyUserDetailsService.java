package com.burger.java.burger.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.burger.java.burger.models.User;
import com.burger.java.burger.repositories.UserRepositories;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepositories userRepositories;
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepositories.findByEmail(email);
        if(user != null){
            List<GrantedAuthority> roles = new ArrayList<>();
            roles.add(new SimpleGrantedAuthority(user.getRole().getLibelle()));
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), true, true, true, true, roles);
        }
        return null;
    }
    
}
