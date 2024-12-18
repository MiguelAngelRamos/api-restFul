package com.kibernumacademy.apirest.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kibernumacademy.apirest.dto.PostDTO;
import com.kibernumacademy.apirest.dto.PostUseDTO;
import com.kibernumacademy.apirest.dto.UserActivitySummaryDTO;
import com.kibernumacademy.apirest.dto.UserDTO;
import com.kibernumacademy.apirest.entity.User;
import com.kibernumacademy.apirest.exception.ResourceNotFoundException;
import com.kibernumacademy.apirest.repository.IUserRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;

@Service
public class UserServiceImpl implements IUserService {

  private final IUserRepository userRepository;

  @PersistenceContext
  private EntityManager entityManager;

  public UserServiceImpl(IUserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDTO saveUser(User user) {

    User savedUser = userRepository.save(user);
    return new UserDTO(savedUser.getId(), savedUser.getUsername(), savedUser.getEmail(), null);
  }

  @Override
  public UserDTO getUserById(Long id) {

    User user = userRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Usuario no encontrado con ese id: " + id));

    return new UserDTO(
                user.getId(), 
                user.getUsername(), 
                user.getEmail(),
                user.getPosts().stream()
                        .map(post -> new PostDTO(post.getId(), post.getTitle(), post.getContent(), user.getId()))
                        .collect(Collectors.toList())
                     );
  }

  @Override
  public List<UserDTO> getAllUsers() {
    return userRepository.findAll().stream()
    .map(user -> new UserDTO(user.getId(), user.getUsername(), user.getEmail(),
            user.getPosts().stream()
                    .map(post -> new PostDTO(post.getId(), post.getTitle(), post.getContent(), user.getId()))
                    .collect(Collectors.toList())))
    .collect(Collectors.toList());
  }


  @Override
  public User updateUser(Long id, User userDetails) {
    User userDb = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con este id: " + id));
    userDb.setUsername(userDetails.getUsername());
    userDb.setEmail(userDetails.getEmail());
    return userRepository.save(userDb);
  }

  @Override
  public void deleteUser(Long id) {
    User userDb = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con este id: " + id));

    userRepository.delete(userDb);
  }

  @Override
  public UserDTO getUserByEmail(String email) {
    return userRepository.findByEmail(email).
      map(user -> new UserDTO(
        user.getId(), 
        user.getUsername(), 
        user.getEmail(), 
        user.getPosts()
            .stream()
            .map(post -> new PostDTO(post.getId(), post.getTitle(), post.getContent(), user.getId()))
            .collect(Collectors.toList()) ))
      .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con el email: " + email));
  }

  @Override
  public Optional<User> getUserByUsernameJPQL(String username) {
    return userRepository.findByUsernameJPQL(username);
  }

  @Override
  public List<PostUseDTO> getPostsByUser(Long userId) {

    List<Object[]> results = userRepository.getPostsByUser(userId);

    return results.stream()
            .map(result -> new PostUseDTO(
                ((Number)result[0]).longValue(), 
                (String)result[1], 
                (String)result[2], 
                ((Number)result[3]).longValue(), 
                (String)result[4],
                (String)result[5]
              )).collect(Collectors.toList());
  
  }

  @Override
  public UserActivitySummaryDTO getUserActivySummary(Long userId) {
    StoredProcedureQuery query = entityManager.createStoredProcedureQuery("GetUserActivitySummary");
    query.registerStoredProcedureParameter("p_user_id", Long.class, ParameterMode.IN);
    query.registerStoredProcedureParameter("total_posts", Integer.class, ParameterMode.OUT);
    query.registerStoredProcedureParameter("last_post_title", String.class, ParameterMode.OUT);

    query.setParameter("p_user_id", userId);

    Integer totalPosts = (Integer) query.getOutputParameterValue("total_posts");
    String lastPostTitle = (String) query.getOutputParameterValue("last_post_title");

    return new UserActivitySummaryDTO(totalPosts, lastPostTitle);
  }

  
 
}
