package com.kibernumacademy.apirest.dto;

public class PostCountResponseDTO {

  private long cantidadPosts;

  public PostCountResponseDTO(long cantidadPosts) {
    this.cantidadPosts = cantidadPosts;
  }

  public long getCantidadPosts() {
    return cantidadPosts;
  }

  public void setCantidadPosts(long cantidadPosts) {
    this.cantidadPosts = cantidadPosts;
  }


}
