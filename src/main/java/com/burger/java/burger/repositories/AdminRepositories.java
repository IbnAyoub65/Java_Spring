package com.burger.java.burger.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.burger.java.burger.models.Admin;

public interface AdminRepositories extends JpaRepository<Admin,Long> {
    
    Admin findByEmail(String email);
}
