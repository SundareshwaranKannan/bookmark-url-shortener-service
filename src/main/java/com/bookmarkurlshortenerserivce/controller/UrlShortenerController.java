package com.bookmarkurlshortenerserivce.controller;

import com.bookmarkurlshortenerserivce.model.DigitalUrl;
import com.bookmarkurlshortenerserivce.service.UrlShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/socgen-shortUrl/")
public class UrlShortenerController {

  @Autowired
  private UrlShortenerService urlShortenerService;

  @GetMapping("/{shortUrl}")
  public ResponseEntity<DigitalUrl> getFullUrl(@PathVariable String shortUrl) {
    return ResponseEntity.ok(urlShortenerService.getLongUrl(shortUrl));
  }
}
