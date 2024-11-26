package com.kibernumacademy.apirest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kibernumacademy.apirest.entity.Post;
import com.kibernumacademy.apirest.entity.User;

public interface IPostRepository extends JpaRepository<Post, Long>{
  List<Post>findByUser(User user);
}
