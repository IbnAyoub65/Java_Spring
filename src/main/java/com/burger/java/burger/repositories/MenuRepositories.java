package com.burger.java.burger.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.burger.java.burger.models.Menu;

@Repository
public interface MenuRepositories extends JpaRepository<Menu,Long> {
    
}
