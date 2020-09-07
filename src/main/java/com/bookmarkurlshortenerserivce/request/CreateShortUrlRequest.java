package com.bookmarkurlshortenerserivce.request;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateShortUrlRequest {

  private String longUrl;
  private Date expiryDate;
  private String description;
  private String category;
}
