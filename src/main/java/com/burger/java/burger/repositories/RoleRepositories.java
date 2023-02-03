package com.burger.java.burger.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.burger.java.burger.models.Role;

@Repository
public interface RoleRepositories extends JpaRepository<Role,Long> {
    
    Role findByLibelle(String libelle);
}
