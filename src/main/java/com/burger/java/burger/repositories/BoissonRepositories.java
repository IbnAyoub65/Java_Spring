package com.burger.java.burger.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.burger.java.burger.models.Boisson;

@Repository
public interface BoissonRepositories extends JpaRepository<Boisson ,Long>{


}