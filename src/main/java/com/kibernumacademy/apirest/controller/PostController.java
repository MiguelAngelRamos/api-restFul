package com.kibernumacademy.apirest.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kibernumacademy.apirest.dto.PostCountResponseDTO;
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

  // * GET http://localhost:8080/api/posts/1
  @GetMapping("/{id}")
  public ResponseEntity<PostDTO> getPostById(@PathVariable long id) {
    PostDTO postDTO = postService.getPostById(id);
    return ResponseEntity.ok(postDTO);
  }

  @GetMapping
  public ResponseEntity<List<PostDTO>> getAllPosts() {
    List<PostDTO> posts = postService.getAllPosts();
    return ResponseEntity.ok(posts);
  }

  // * GET http://localhost:8080/api/posts/user/1
  @GetMapping("/user/{userId}")
  public ResponseEntity<List<PostDTO>> getPostsByUserId(@PathVariable Long userId) {
    List<PostDTO> posts = postService.getPostsByUserId(userId);
    return ResponseEntity.ok(posts);
  }

  @PutMapping("/{id}")
  public ResponseEntity<PostDTO> updatePost(@PathVariable Long id, @RequestBody PostDTO postDTO) {
    PostDTO updatedPost = postService.updatePost(id, postDTO);
    return ResponseEntity.ok(updatedPost);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePost(@PathVariable Long id) {
    postService.deletePost(id);
    return ResponseEntity.noContent().build();
  }

  //* http://localhost:8080/api/posts/search/1?title=Angular
  @GetMapping("/search/{userId}")
  public ResponseEntity<PostDTO> getPostByTitleAndUserId(@PathVariable Long userId, @RequestParam String title ) {
    PostDTO post = postService.getPostByTitleAndUserId(title, userId);
    return ResponseEntity.ok(post);
  }

    //* http://localhost:8080/api/posts/count/1
  @GetMapping("/count/{userId}")
  public ResponseEntity<PostCountResponseDTO> getCountPostByUserId(@PathVariable Long userId) {
    PostCountResponseDTO response = postService.getCountPostsByUserId(userId);
    return ResponseEntity.ok(response);
  }

}
