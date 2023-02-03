package com.burger.java.burger.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.burger.java.burger.models.Marque;

@Repository
public interface MarqueRepositories extends JpaRepository<Marque ,Long>{
    
}
