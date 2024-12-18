package com.kibernumacademy.apirest.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
// @Table(name="posts")
public class Post {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  // "                  "
  @NotBlank(message= "Title is mandatory")
  @Size(max=255, message="Title must be less than 255 characteres")
  private String title;


  @NotBlank(message = "Content is mandatory")
  @Size(max=5000, message = "Content must be leess than 5000 characteres")
  @Column(length = 4999)
  // @Lob
  // @Column(columnDefinition = "TEXT")
  private String content;

  @ManyToOne(fetch = FetchType.EAGER) // Carga al usuario asociado al Post
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @CreationTimestamp
  private LocalDateTime createAt;

  @UpdateTimestamp
  private LocalDateTime updateAt;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public LocalDateTime getCreateAt() {
    return createAt;
  }

  public void setCreateAt(LocalDateTime createAt) {
    this.createAt = createAt;
  }

  public LocalDateTime getUpdateAt() {
    return updateAt;
  }

  public void setUpdateAt(LocalDateTime updateAt) {
    this.updateAt = updateAt;
  }

  

}
