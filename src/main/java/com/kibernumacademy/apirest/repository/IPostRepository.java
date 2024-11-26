package com.kibernumacademy.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kibernumacademy.apirest.entity.Post;

public interface IPostRepository extends JpaRepository<Post, Long>{

}
