package com.bookmarkurlshortenerserivce.repository;

import com.bookmarkurlshortenerserivce.model.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

  Optional<UserEntity> findByEmail(String email);
}
