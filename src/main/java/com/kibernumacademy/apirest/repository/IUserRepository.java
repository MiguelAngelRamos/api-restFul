package com.kibernumacademy.apirest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kibernumacademy.apirest.entity.User;


public interface IUserRepository extends JpaRepository<User, Long> {
  
  // Consulta nativa consulta directamente hacia la tabla de la base de datos
  @Query(value = "SELECT * FROM user WHERE email = ?1", nativeQuery = true)
  Optional<User> findByEmail(String email);

  // JPQL "Pasa por el modelo User"
  @Query("SELECT u FROM User u WHERE u.username = ?1")
  Optional<User> findByUsernameJPQL(String username);

  // Store procedure
  @Query(value = "CALL GetPostsByUser(:userId)", nativeQuery = true)
  List<Object[]> getPostsByUser(@Param("userId") Long userId);
  
}
// Jamas deberiamos usar el UserDto, sin o que se trabaja siempre con el Modelo