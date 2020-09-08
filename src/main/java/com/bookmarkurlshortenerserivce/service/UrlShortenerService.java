package com.bookmarkurlshortenerserivce.service;

import com.bookmarkurlshortenerserivce.exception.BadRequestException;
import com.bookmarkurlshortenerserivce.exception.ExpiredUrlException;
import com.bookmarkurlshortenerserivce.exception.NotFoundException;
import com.bookmarkurlshortenerserivce.model.DigitalUrl;
import com.bookmarkurlshortenerserivce.repository.UrlShortenerRepository;
import com.bookmarkurlshortenerserivce.request.CreateShortUrlRequest;
import com.google.common.hash.Hashing;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Date;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlShortenerService {

  @Autowired
  private UrlShortenerRepository urlShortenerRepository;

  public DigitalUrl getLongUrl(String shortUrl) {
    DigitalUrl digitalUrl = urlShortenerRepository.findByShortUrl(shortUrl)
        .orElseThrow(() -> new NotFoundException("Please enter a valid url alias!"));
    if (digitalUrl.getExpiryDate() == null
        || digitalUrl.getExpiryDate().compareTo(LocalDate.now()) >= 0) {
      return digitalUrl;
    }
    throw new ExpiredUrlException("The requested short url has expired!");
  }

  public DigitalUrl createLongUrl(CreateShortUrlRequest createShortUrlRequest) {
    UrlValidator urlValidator = new UrlValidator(new String[]{"http", "https"});
    if (urlValidator.isValid(createShortUrlRequest.getLongUrl())) {
      String shortUrlCode = Hashing.murmur3_32()
          .hashString(createShortUrlRequest.getLongUrl() + (new Date()).toString(),
              StandardCharsets.UTF_8).toString();
      DigitalUrl digitalUrl = new DigitalUrl(shortUrlCode, createShortUrlRequest.getLongUrl(),
          createShortUrlRequest.getExpiryDate(), createShortUrlRequest.getDescription(),
          createShortUrlRequest.getCategory());
      urlShortenerRepository.save(digitalUrl);
      return digitalUrl;
    }

    throw new BadRequestException("URL Invalid: " + createShortUrlRequest.getLongUrl());
  }

}
