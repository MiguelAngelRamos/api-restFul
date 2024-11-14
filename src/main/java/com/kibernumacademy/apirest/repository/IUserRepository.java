package com.kibernumacademy.apirest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.kibernumacademy.apirest.entity.User;

public interface IUserRepository extends JpaRepository<User, Long> {
  
  @Query(value = "SELECT * FROM users WHERE email = ?1", nativeQuery = true)
  Optional<User> findByEmail(String email);


}
