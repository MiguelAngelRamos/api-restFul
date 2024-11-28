package com.kibernumacademy.apirest.dto;

public class PostUseDTO {
  private Long postId;
  private Long postTitle;
  private Long postContent;
  private Long userId;
  private Long userName;
  private Long userEmail;

  public PostUseDTO(Long postId, Long postTitle, Long postContent, Long userId, Long userName, Long userEmail) {
    this.postId = postId;
    this.postTitle = postTitle;
    this.postContent = postContent;
    this.userId = userId;
    this.userName = userName;
    this.userEmail = userEmail;
  }

  public Long getPostId() {
    return postId;
  }

  public void setPostId(Long postId) {
    this.postId = postId;
  }

  public Long getPostTitle() {
    return postTitle;
  }

  public void setPostTitle(Long postTitle) {
    this.postTitle = postTitle;
  }

  public Long getPostContent() {
    return postContent;
  }

  public void setPostContent(Long postContent) {
    this.postContent = postContent;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getUserName() {
    return userName;
  }

  public void setUserName(Long userName) {
    this.userName = userName;
  }

  public Long getUserEmail() {
    return userEmail;
  }

  public void setUserEmail(Long userEmail) {
    this.userEmail = userEmail;
  }

  
  
}
