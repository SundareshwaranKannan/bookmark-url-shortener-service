package com.bookmarkurlshortenerserivce.controller;

import com.bookmarkurlshortenerserivce.model.DigitalUrl;
import com.bookmarkurlshortenerserivce.request.CreateShortUrlRequest;
import com.bookmarkurlshortenerserivce.service.UrlShortenerService;
import java.net.URI;
import java.net.URISyntaxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/sg-digital")
public class UrlShortenerController {

  @Autowired
  private UrlShortenerService urlShortenerService;

  @GetMapping("/{shortUrl}")
  public ResponseEntity<Void> getFullUrlAndRedirect(@PathVariable String shortUrl)
      throws URISyntaxException {
    DigitalUrl digitalUrl = urlShortenerService.getLongUrl(shortUrl);
    return ResponseEntity.status(HttpStatus.FOUND).location(new URI(digitalUrl.getLongUrl()))
        .build();
  }

  @GetMapping("/longUrl/{shortUrl}")
  public ResponseEntity<DigitalUrl> getFullUrl(@PathVariable String shortUrl) {
    return ResponseEntity.ok(urlShortenerService.getLongUrl(shortUrl));
  }

  @PostMapping("/create")
  public ResponseEntity<DigitalUrl> createShortUrl(
      @RequestBody CreateShortUrlRequest shortUrlRequest) {
    return ResponseEntity.ok(urlShortenerService.createLongUrl(shortUrlRequest));
  }
}
