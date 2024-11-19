package com.kibernumacademy.apirest.dto;

import java.util.Collections;
import java.util.List;

import com.kibernumacademy.apirest.entity.Post;

public class UserDTO {

  private Long id;
  private String name;
  private String email;
  private List<Post> posts;

  public UserDTO(Long id, String name, String email, List<Post> posts) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.posts = (posts != null) ? posts: Collections.emptyList(); // []
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public List<Post> getPosts() {
    return posts;
  }

  public void setPosts(List<Post> posts) {
    this.posts = posts;
  }

  

  
}
