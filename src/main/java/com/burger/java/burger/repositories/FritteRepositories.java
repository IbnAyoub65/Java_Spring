package com.burger.java.burger.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.burger.java.burger.models.Fritte;

@Repository
public interface FritteRepositories  extends JpaRepository<Fritte , Long>{
    
}
