package com.bookmarkurlshortenerserivce;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.bookmarkurlshortenerserivce.exception.BadRequestException;
import com.bookmarkurlshortenerserivce.exception.ExpiredUrlException;
import com.bookmarkurlshortenerserivce.exception.NotFoundException;
import com.bookmarkurlshortenerserivce.model.DigitalUrl;
import com.bookmarkurlshortenerserivce.repository.UrlShortenerRepository;
import com.bookmarkurlshortenerserivce.request.CreateShortUrlRequest;
import com.bookmarkurlshortenerserivce.service.UrlShortenerService;
import java.time.LocalDate;
import java.util.Optional;
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
    String mockUrl = "sampleUrl";
    DigitalUrl mockDigitalUrl = new DigitalUrl("kpys01", "https://www.google.com", LocalDate.now(),
        null,
        null);
    Mockito.when(urlShortenerRepository.findByShortUrl(mockUrl))
        .thenReturn(Optional.of(mockDigitalUrl));
    assertThat(this.urlShortenerService.getLongUrl(mockUrl)).isEqualTo(mockDigitalUrl);
  }

  @Test
  @DisplayName("should not get long url from the database when the short url is not present")
  public void shouldNotGetLongUrlFromDatabaseWhenTheShortUrlIsNotPresent() {
    DigitalUrl mockDigitalUrl = new DigitalUrl("kpys01", "https://www.google.com", LocalDate.now(),
        null,
        null);
    urlShortenerRepository.save(mockDigitalUrl);
    Throwable exception = assertThrows(NotFoundException.class,
        () -> urlShortenerService.getLongUrl("sampleUrl"));
    assertEquals("Please enter a valid url alias!", exception.getMessage());
  }

  @Test
  @DisplayName("should throw expired url exception")
  public void shouldThrowExpiredUrlException() {
    String mockUrl = "sampleUrl";
    DigitalUrl mockDigitalUrl = new DigitalUrl("842d119b", "https://www.wikipedia.org/",
        LocalDate.now().minusDays(2), null, null);
    Mockito.when(urlShortenerRepository.findByShortUrl(mockUrl))
        .thenReturn(Optional.of(mockDigitalUrl));
    Throwable exception = assertThrows(ExpiredUrlException.class,
        () -> urlShortenerService.getLongUrl(mockUrl));
    assertEquals("The requested short url has expired!", exception.getMessage());
  }

  @Test
  @DisplayName("should create short url and store it in the database")
  public void shouldCreateShortUrlAndStoreItInTheDatabase() {
    DigitalUrl mockDigitalUrl = new DigitalUrl("842d119b", "https://www.wikipedia.org/",
        LocalDate.now(),
        null, null);
    CreateShortUrlRequest createShortUrlRequest = new CreateShortUrlRequest(
        "https://www.wikipedia.org/", LocalDate.now().plusDays(1), null, null);
    Mockito.when(urlShortenerRepository.save(mockDigitalUrl))
        .thenReturn(mockDigitalUrl);
    DigitalUrl digitalUrlResponse = urlShortenerService.createLongUrl(createShortUrlRequest);
    assertEquals(digitalUrlResponse.getLongUrl(), mockDigitalUrl.getLongUrl());
  }

  @Test
  @DisplayName("should not create short url for invalid url")
  public void shouldNotCreateShortUrlForInvalidUrl() {
    CreateShortUrlRequest createShortUrlRequest = new CreateShortUrlRequest(
        "www.dummy22.org/", LocalDate.now().plusDays(1), null, null);
    Throwable exception = assertThrows(BadRequestException.class,
        () -> urlShortenerService.createLongUrl(createShortUrlRequest));
    assertEquals("URL Invalid: www.dummy22.org/", exception.getMessage());
  }
}
