package com.kibernumacademy.apirest.service;

import java.util.List;

import com.kibernumacademy.apirest.dto.PostDTO;

public interface IPostService {
  PostDTO savePost(PostDTO postDto);
  PostDTO getPostById(Long id);
  List<PostDTO> getAllPosts();
  List<PostDTO> getPostsByUserId(Long userId);
  PostDTO updatePost(Long id, PostDTO postDTO);
  void deletePost(Long id);
}
