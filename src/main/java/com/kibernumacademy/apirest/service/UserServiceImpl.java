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
  public UserDTO saveUser(UserDTO userDto) {

    User user = new User();
    
    user.setUsername(userDto.getName());
    user.setEmail(userDto.getEmail());

    User savedUser = userRepository.save(user);
    return new UserDTO(savedUser.getId(), savedUser.getUsername(), savedUser.getEmail(), null);
  }

  @Override
  public UserDTO getUserById(Long id) {

    User user = userRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Usuario no encontrado con ese id: " + id));

    return new UserDTO(user.getId(), user.getUsername(), user.getEmail(),
                user.getPosts().stream()
                        .map(post -> new PostDTO(post.getId(), post.getTitle(), post.getContent(), user.getId()))
                        .collect(Collectors.toList()));
  }

  @Override
  public List<UserDTO> getAllUsers() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getAllUsers'");
  }

  @Override
  public Optional<UserDTO> findByEmail(String email) {
    return userRepository.findByEmail(email).map( user -> new UserDTO(user.getId(), user.getUsername(), user.getEmail(), null));
  }

  @Override
  public UserDTO updateUser(Long id, UserDTO userDetails) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
  }

  @Override
  public void deleteUser(Long id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deleteUser'");
  }
  
}