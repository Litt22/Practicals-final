package com.example.lib.exception;

public class ExpiredBatchException extends RuntimeException {
  public ExpiredBatchException(String message) {
    super(message);
  }
}

