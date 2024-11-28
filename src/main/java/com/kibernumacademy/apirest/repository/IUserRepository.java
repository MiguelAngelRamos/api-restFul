package com.kibernumacademy.apirest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kibernumacademy.apirest.entity.User;


public interface IUserRepository extends JpaRepository<User, Long> {
  
  // Consulta nativa consulta directamente hacia la tabla de la base de datos
  @Query(value = "SELECT * FROM user WHERE email = ?1", nativeQuery = true)
  Optional<User> findByEmail(String email);

  // JPQL "Pasa por el modelo User"
  @Query("SELECT FROM User WHERE username = ?1")
  Optional<User> findByUsernameJPSQL(String username);

  
}
// Jamas deberiamos usar el UserDto, sin o que se trabaja siempre con el Modelo