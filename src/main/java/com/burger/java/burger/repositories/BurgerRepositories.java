package com.burger.java.burger.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.burger.java.burger.models.Burger;

@Repository
public interface BurgerRepositories extends JpaRepository<Burger,Long>{
    
    @Query("SELECT b FROM Burger b WHERE b.etat=?1")
    Burger FindBurgerByEtat(String etat);
}
