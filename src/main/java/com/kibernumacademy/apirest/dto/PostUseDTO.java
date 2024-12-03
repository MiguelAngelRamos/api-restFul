package com.kibernumacademy.apirest.dto;

public class PostUseDTO {

  private Long postId;
  private String postTitle;
  private String postContent;
  private Long userId;
  private String userName;
  private String userEmail;

  public PostUseDTO(Long postId, String postTitle, String postContent, Long userId, String userName, String userEmail) {
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

  public String getPostTitle() {
    return postTitle;
  }

  public void setPostTitle(String postTitle) {
    this.postTitle = postTitle;
  }

  public String getPostContent() {
    return postContent;
  }

  public void setPostContent(String postContent) {
    this.postContent = postContent;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserEmail() {
    return userEmail;
  }

  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }

  

  
  
  
}
