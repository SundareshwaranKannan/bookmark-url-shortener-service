package com.bookmarkurlshortenerserivce.service;

import com.bookmarkurlshortenerserivce.model.DigitalUrl;
import com.bookmarkurlshortenerserivce.repository.UrlShortenerRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookmarkService {

  @Autowired
  private UrlShortenerRepository urlShortenerRepository;

  public List<DigitalUrl> getAllBookmarks() {
    return urlShortenerRepository.findAllByExpiryDateIsNull();
  }

  public void deleteBookmark(String shortUrl) {
    urlShortenerRepository.deleteById(shortUrl);
  }

}
