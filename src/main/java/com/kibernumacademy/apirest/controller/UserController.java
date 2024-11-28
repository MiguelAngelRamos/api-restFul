package com.kibernumacademy.apirest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kibernumacademy.apirest.dto.UserDTO;
import com.kibernumacademy.apirest.entity.User;
import com.kibernumacademy.apirest.service.IUserService;
;

@RestController
@RequestMapping("api/users")
public class UserController {
  private final IUserService userService;
  
  public UserController(IUserService userService) {
    this.userService = userService;
  }

  @PostMapping
  public ResponseEntity<UserDTO> createUser(@RequestBody User user) {
     UserDTO savedUser = userService.saveUser(user);
     return ResponseEntity.ok(savedUser);
  }
 // GET: localhost:8080/api/users/3
  @GetMapping("{id}")
  public ResponseEntity<UserDTO>getUserById(@PathVariable Long id) {
    UserDTO userDTO = userService.getUserById(id);
    return ResponseEntity.ok(userDTO);
    // return ResponseEntity.ok(userService.getUserById(id));
  }

  @GetMapping
  public ResponseEntity<List<UserDTO>> getAllUsers() {
    return ResponseEntity.ok(userService.getAllUsers());
  }

  @PutMapping("/{id}") //localhost:8080/api/users/4
  public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
    User userdb = userService.updateUser(id, userDetails);
    return ResponseEntity.ok(userdb);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    userService.deleteUser(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/by-email/{email}")
  public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
    UserDTO user = userService.getUserByEmail(email);
    return ResponseEntity.ok(user);
  }


  // @GetMapping()
  // public ResponseEntity<User> getUserByUsernameJPQL() {

  // }

  // Store Procedure
  
  @GetMapping("/{userId}/posts")
  public ResponseEntity<List<Object[]>> getPostsByUser(@PathVariable Long userId) {
    List<Object[]> posts = userService.getPostsByUser(userId);
    return ResponseEntity.ok(posts);
  }
}
