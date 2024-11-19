package com.kibernumacademy.apirest.service;

import java.util.List;
import java.util.Optional;

import com.kibernumacademy.apirest.dto.UserDTO;

public interface IUserService {
  UserDTO saveUser(UserDTO userDto);
  UserDTO getUserById(Long id);
  List<UserDTO> getAllUsers();
  Optional<UserDTO> findByEmail(String email);
  UserDTO updateUser(Long id, UserDTO userDetails);
  void deleteUser(Long id);
}
