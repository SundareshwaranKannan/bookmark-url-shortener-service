package com.bookmarkurlshortenerserivce.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder(toBuilder = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "DIGITAL_URL")
public class DigitalUrl {

  @Id
  @Column(name = "SHORT_URL", nullable = false)
  private String shortUrl;

  @Column(name = "LONG_URL", nullable = false)
  private String longUrl;

  @Column(name = "EXPIRY_DATE")
  private LocalDate expiryDate;

  @Column(name = "DESCRIPTION")
  private String description;

  @Column(name = "CATEGORY")
  private String category;

  @Column(name = "TRIBE")
  private String tribe;

  @Column(name = "APPLICATION")
  private String application;

  @Column(name = "USER_MAIL")
  private String userMail;

  @Column(name = "BOOKMARK_TITLE")
  private String bookmarkTitle;
}
