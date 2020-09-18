package com.bookmarkurlshortenerserivce.service;

import com.bookmarkurlshortenerserivce.exception.NotFoundException;
import com.bookmarkurlshortenerserivce.model.UserEntity;
import com.bookmarkurlshortenerserivce.repository.UserRepository;
import com.bookmarkurlshortenerserivce.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public UserResponse validateCurrentUser(UserEntity userEntity) {
    UserEntity user = userRepository.findByEmail(userEntity.getEmail())
        .orElseThrow(() -> new NotFoundException("Please enter a valid login"));
    if (user.getPassword().equals(userEntity.getPassword())) {
      UserResponse userResponse = new UserResponse();
      userResponse.setEmail(user.getEmail());
      userResponse.setCategory(user.getCategory());
      return userResponse;
    }
    throw new NotFoundException("Invalid login credentials");
  }

  public UserResponse createUser(UserEntity userEntity) {
    userRepository.save(userEntity);
    UserResponse userResponse = new UserResponse();
    userResponse.setEmail(userEntity.getEmail());
    userResponse.setCategory(userEntity.getCategory());
    return userResponse;
  }
}
