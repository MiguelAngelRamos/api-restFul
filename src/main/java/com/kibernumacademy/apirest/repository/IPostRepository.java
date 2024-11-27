package com.kibernumacademy.apirest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kibernumacademy.apirest.entity.Post;
import com.kibernumacademy.apirest.entity.User;

public interface IPostRepository extends JpaRepository<Post, Long>{
  
  List<Post>findByUser(User user);

  // Buscar posts por un titulo exacto de un usuario por id
  //* SELECT * FROM post WHERE title = 'Angular' AND user_id = 1;
  // findBy: Recuperar datos basados en condiciones. 
  Optional<Post> findByTitleAndUserId(String title, Long userId);

  // Contar posts por usuario
  //* SELECT COUNT(*) FROM post WHERE user_id = 5;
  long countByUserId(Long userId);

}
