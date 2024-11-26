package com.kibernumacademy.apirest.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kibernumacademy.apirest.dto.PostDTO;
import com.kibernumacademy.apirest.entity.Post;
import com.kibernumacademy.apirest.entity.User;
import com.kibernumacademy.apirest.exception.ResourceNotFoundException;
import com.kibernumacademy.apirest.repository.IPostRepository;
import com.kibernumacademy.apirest.repository.IUserRepository;

@Service
public class PostServiceImpl implements IPostService {

  private final IPostRepository postRepository;
  private final IUserRepository userRepository;

  public PostServiceImpl(IPostRepository postRepository, IUserRepository userRepository) {
    this.postRepository = postRepository;
    this.userRepository = userRepository;
  }

  @Override
  public PostDTO savePost(PostDTO postDTO) {
    User user = userRepository.findById(postDTO.getUserId()).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontado con el id: " + postDTO.getUserId()));

    Post post = new Post();
    post.setTitle(postDTO.getTitle());
    post.setContent(postDTO.getContent());
    post.setUser(user);

    Post savePost = postRepository.save(post);
    return new PostDTO(savePost.getId(), savePost.getTitle(), savePost.getContent(), savePost.getUser().getId());
    
  }

  @Override
  public PostDTO getPostById(Long id) {
    Post post = postRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Post no encontrado con el id: " + id));
    return new PostDTO(post.getId(), post.getTitle(), post.getContent(), post.getUser().getId());
  }

  @Override //* select * from post; 
  public List<PostDTO> getAllPosts() {
    return postRepository.findAll()
                         .stream()
                         .map(post -> new PostDTO(post.getId(), post.getTitle(), post.getContent(), post.getUser().getId()))
                         .collect(Collectors.toList());
  }

  @Override
  public List<PostDTO> getPostsByUserId(Long userId) {
    User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontado con el id: " + userId));
    return postRepository.findByUser(user).stream().map(post -> new PostDTO(post.getId(), post.getTitle(), post.getContent(), post.getUser().getId())).collect(Collectors.toList());
  }

  @Override
  public PostDTO updatePost(Long id, PostDTO postDTO) {

    Post postDB = postRepository.findById(id)
                              .orElseThrow(() -> new ResourceNotFoundException("Post no encontrado con el id: " + id));

    User userDB = userRepository.findById(postDTO.getUserId())
                              .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontado con el id: " + postDTO.getUserId()));

    postDB.setTitle(postDTO.getTitle());
    postDB.setContent(postDTO.getContent());
    postDB.setUser(userDB);

    Post updatedPost = postRepository.save(postDB);
    return new PostDTO(updatedPost.getId(), updatedPost.getTitle(), updatedPost.getContent(), updatedPost.getUser().getId());

  }

  @Override
  public void deletePost(Long id) {
    
  }
  
}
