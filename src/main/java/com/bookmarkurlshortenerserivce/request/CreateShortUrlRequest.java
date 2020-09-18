package com.bookmarkurlshortenerserivce.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
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
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate expiryDate;
  private String description;
  private String category;
  private String userMail;
  private String tribe;
  private String application;
  private String bookmarkTitle;
}
