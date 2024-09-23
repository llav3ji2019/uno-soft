package com.uno.test.utils.exception;

public class FileNameNotFound extends RuntimeException {
  public FileNameNotFound(String message) {
    super(message);
  }
}
