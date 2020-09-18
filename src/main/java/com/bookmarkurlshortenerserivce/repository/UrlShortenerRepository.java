package com.bookmarkurlshortenerserivce.repository;

import com.bookmarkurlshortenerserivce.model.DigitalUrl;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlShortenerRepository extends JpaRepository<DigitalUrl, String> {

  Optional<DigitalUrl> findByShortUrl(String shortUrl);

  List<DigitalUrl> findAllByExpiryDateIsNull();
}
