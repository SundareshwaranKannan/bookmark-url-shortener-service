package com.bookmarkurlshortenerserivce.exception;

import static org.springframework.http.HttpStatus.NO_CONTENT;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(NO_CONTENT)
public class ExpiredUrlException extends RuntimeException {

  public ExpiredUrlException(String message) {
    super(message);
  }

  public ExpiredUrlException(String message, Throwable cause) {
    super(message, cause);
  }
}
