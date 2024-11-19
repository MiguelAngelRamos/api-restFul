package com.kibernumacademy.apirest.exception;

public class ResourceNotFoundException extends RuntimeException {
  
  public ResourceNotFoundException(String message) {
    super(message);
  }
}
