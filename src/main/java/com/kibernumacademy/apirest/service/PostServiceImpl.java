package com.kibernumacademy.apirest.service;

import java.util.List;

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

  @Override
  public List<PostDTO> getAllPosts() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getAllPosts'");
  }

  @Override
  public List<PostDTO> getPostsByUserId(Long userId) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getPostsByUserId'");
  }

  @Override
  public PostDTO updatePost(Long id, PostDTO postDTO) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'updatePost'");
  }

  @Override
  public void deletePost(Long id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deletePost'");
  }
  
}
