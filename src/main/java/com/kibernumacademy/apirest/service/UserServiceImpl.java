package com.kibernumacademy.apirest.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kibernumacademy.apirest.dto.PostDTO;
import com.kibernumacademy.apirest.dto.UserDTO;
import com.kibernumacademy.apirest.entity.User;
import com.kibernumacademy.apirest.exception.ResourceNotFoundException;
import com.kibernumacademy.apirest.repository.IUserRepository;

@Service
public class UserServiceImpl implements IUserService {

  private final IUserRepository userRepository;

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
  public Optional<UserDTO> findByEmail(String email) {
    return userRepository.findByEmail(email).map( user -> new UserDTO(user.getId(), user.getUsername(), user.getEmail(), null));
  }

  @Override
  public User updateUser(Long id, UserDTO userDetails) {
    User userDb = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con este id: " + id));
    userDb.setUsername(userDetails.getName());
    userDb.setEmail(userDetails.getEmail());
    return userRepository.save(userDb);
  }

  @Override
  public void deleteUser(Long id) {
    User userDb = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con este id: " + id));

    userRepository.delete(userDb);
  }
  
}
