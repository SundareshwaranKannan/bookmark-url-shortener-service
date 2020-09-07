package com.bookmarkurlshortenerserivce.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
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

  @Column(name = "EXPIRY_DATE", nullable = true)
  private Date expiryDate;
}
