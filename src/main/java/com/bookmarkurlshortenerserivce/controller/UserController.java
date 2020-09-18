package com.bookmarkurlshortenerserivce.controller;

import com.bookmarkurlshortenerserivce.model.UserEntity;
import com.bookmarkurlshortenerserivce.response.UserResponse;
import com.bookmarkurlshortenerserivce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/digital-users")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping("/validate")
  public ResponseEntity<UserResponse> getUserDetails(@RequestParam String email,
      @RequestParam String password, @RequestParam String category) {
    UserEntity userEntity = new UserEntity(email, password, category);
    return ResponseEntity.ok(userService.validateCurrentUser(userEntity));
  }

  @PostMapping("/create")
  public ResponseEntity<UserResponse> createShortUrl(@RequestBody UserEntity userEntity) {
    return ResponseEntity.ok(userService.createUser(userEntity));
  }

}
