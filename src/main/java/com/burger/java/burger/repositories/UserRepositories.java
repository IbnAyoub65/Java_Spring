package com.burger.java.burger.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.burger.java.burger.models.User;

@Repository
public interface UserRepositories extends JpaRepository<User,Long>{
  /*   @Query("SELECT u FROM User u WHERE u.email=?1") */
    User findByEmail(String email);
}
