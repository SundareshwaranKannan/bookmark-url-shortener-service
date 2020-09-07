package com.bookmarkurlshortenerserivce.service;

import com.bookmarkurlshortenerserivce.exception.NotFoundException;
import com.bookmarkurlshortenerserivce.model.DigitalUrl;
import com.bookmarkurlshortenerserivce.repository.UrlShortenerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlShortenerService {

  @Autowired
  private UrlShortenerRepository urlShortenerRepository;

  public DigitalUrl getLongUrl(String shortUrl) {
    return urlShortenerRepository.findByShortUrl(shortUrl)
        .orElseThrow(() -> new NotFoundException("Please enter a valid url alias!"));
  }

}
