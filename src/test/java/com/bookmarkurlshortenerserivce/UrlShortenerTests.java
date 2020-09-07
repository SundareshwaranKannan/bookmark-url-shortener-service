package com.bookmarkurlshortenerserivce;

import com.bookmarkurlshortenerserivce.exception.NotFoundException;
import com.bookmarkurlshortenerserivce.model.DigitalUrl;
import com.bookmarkurlshortenerserivce.repository.UrlShortenerRepository;
import com.bookmarkurlshortenerserivce.service.UrlShortenerService;
import java.util.Date;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@EnableAutoConfiguration
@ActiveProfiles("test")
public class UrlShortenerTests {

  @Autowired
  private UrlShortenerService urlShortenerService;

  @MockBean
  private UrlShortenerRepository urlShortenerRepository;

  @Test
  @DisplayName("should get long url from the database")
  public void shouldGetLongUrlFromDatabase() {
    DigitalUrl mockDigitalUrl = new DigitalUrl("kpys01", "https://www.google.com", new Date());
    Mockito.when(urlShortenerRepository.findByShortUrl("sampleUrl"))
        .thenReturn(Optional.of(mockDigitalUrl));
    Assertions.assertThat(this.urlShortenerService.getLongUrl("sampleUrl"))
        .isEqualTo(mockDigitalUrl);
  }

  @Test
  @DisplayName("should not get long url from the database")
  public void shouldNotGetLongUrlFromDatabase() {
    DigitalUrl mockDigitalUrl = new DigitalUrl("kpys01", "https://www.google.com", new Date());
    urlShortenerRepository.save(mockDigitalUrl);
    Throwable exception = org.junit.jupiter.api.Assertions
        .assertThrows(NotFoundException.class, () -> {
          urlShortenerService.getLongUrl("sampleUrl");
        });
    org.junit.jupiter.api.Assertions
        .assertEquals("Please enter a valid url alias!", exception.getMessage());
  }
}
