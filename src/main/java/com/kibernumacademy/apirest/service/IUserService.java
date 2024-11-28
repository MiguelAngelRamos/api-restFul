package com.kibernumacademy.apirest.service;

import java.util.List;
import java.util.Optional;

import com.kibernumacademy.apirest.dto.UserDTO;
import com.kibernumacademy.apirest.entity.User;

public interface IUserService {
  UserDTO saveUser(User userDto);
  UserDTO getUserById(Long id);
  List<UserDTO> getAllUsers();
  Optional<UserDTO> findByEmail(String email);
  User updateUser(Long id, User userDetails);
  void deleteUser(Long id);
  Optional<User> getUserByEmail(String email);
  Optional<User> getUserByUsernameJPSQL(String username);
}
