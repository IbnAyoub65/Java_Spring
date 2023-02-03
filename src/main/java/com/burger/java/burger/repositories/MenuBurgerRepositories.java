package com.burger.java.burger.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.burger.java.burger.models.Menu_Burger;

@Repository
public interface MenuBurgerRepositories extends JpaRepository<Menu_Burger, Long> {
    
}
