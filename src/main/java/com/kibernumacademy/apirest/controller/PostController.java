package com.kibernumacademy.apirest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kibernumacademy.apirest.dto.PostDTO;
import com.kibernumacademy.apirest.service.IPostService;

@RestController
@RequestMapping("/api/posts") // GET, POST, DELETE, PUT
public class PostController {

  private IPostService postService;

  public PostController(IPostService postService) {
    this.postService = postService;
  }

  @PostMapping
  public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO) {
    PostDTO savedPost = postService.savePost(postDTO);
    return ResponseEntity.ok(savedPost);
  }

  //* GET http://localhost:8080/api/posts/1
  @GetMapping("/{id}")
  public ResponseEntity<PostDTO> getPostById(@PathVariable long id) {
    PostDTO postDTO = postService.getPostById(id);
    return ResponseEntity.ok(postDTO);
  }
  
}
