package com.bookmarkurlshortenerserivce.controller;

import com.bookmarkurlshortenerserivce.model.DigitalUrl;
import com.bookmarkurlshortenerserivce.service.BookmarkService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/digital-bookmarks")
public class BookmarkController {

  @Autowired
  private BookmarkService bookmarkService;

  @GetMapping("/bookmarks")
  public ResponseEntity<List<DigitalUrl>> getAllBookmarks() {
    return ResponseEntity.ok(bookmarkService.getAllBookmarks());
  }

  @DeleteMapping("/{shortUrl}")
  public ResponseEntity deleteBookmark(@PathVariable String shortUrl) {
    bookmarkService.deleteBookmark(shortUrl);
    return ResponseEntity.ok(true);
  }

}
