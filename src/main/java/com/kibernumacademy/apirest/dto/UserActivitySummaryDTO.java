package com.kibernumacademy.apirest.dto;

public class UserActivitySummaryDTO {
  
  private Integer totalPosts;
  private String lastPostTitle;

  public UserActivitySummaryDTO(Integer totalPosts, String lastPostTitle) {
    this.totalPosts = totalPosts;
    this.lastPostTitle = lastPostTitle;
  }

  public Integer getTotalPosts() {
    return totalPosts;
  }

  public void setTotalPosts(Integer totalPosts) {
    this.totalPosts = totalPosts;
  }

  public String getLastPostTitle() {
    return lastPostTitle;
  }

  public void setLastPostTitle(String lastPostTitle) {
    this.lastPostTitle = lastPostTitle;
  }

  

  

}
