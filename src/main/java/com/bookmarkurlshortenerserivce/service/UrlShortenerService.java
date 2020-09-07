package com.bookmarkurlshortenerserivce.service;

import com.bookmarkurlshortenerserivce.exception.BadRequestException;
import com.bookmarkurlshortenerserivce.exception.NotFoundException;
import com.bookmarkurlshortenerserivce.model.DigitalUrl;
import com.bookmarkurlshortenerserivce.repository.UrlShortenerRepository;
import com.bookmarkurlshortenerserivce.request.CreateShortUrlRequest;
import com.google.common.hash.Hashing;
import java.nio.charset.StandardCharsets;
import org.apache.commons.validator.routines.UrlValidator;
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

  public DigitalUrl createLongUrl(CreateShortUrlRequest createShortUrlRequest) {
    UrlValidator urlValidator = new UrlValidator(new String[]{"http", "https"});
    if (urlValidator.isValid(createShortUrlRequest.getLongUrl())) {
      String shortUrlCode = Hashing.murmur3_32()
          .hashString(createShortUrlRequest.getLongUrl(), StandardCharsets.UTF_8).toString();
      System.out.println("URL Id generated: " + shortUrlCode);
      DigitalUrl digitalUrl = new DigitalUrl(shortUrlCode, createShortUrlRequest.getLongUrl(),
          createShortUrlRequest.getExpiryDate(), createShortUrlRequest.getDescription(),
          createShortUrlRequest.getCategory());
      urlShortenerRepository.save(digitalUrl);
      return digitalUrl;
    }

    throw new BadRequestException("URL Invalid: " + createShortUrlRequest.getLongUrl());
  }

}
