package com.kibernumacademy.apirest.entity;

import jakarta.persistence.*;

@Entity
public class Post {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  private String content;

  @ManyToOne
  @JoinColumn(name="user_id")
  private User user;
}
