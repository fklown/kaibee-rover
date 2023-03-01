package com.kaibee.karl.rover.movement.domain.exception;

public class InvalidActionCommandException extends RuntimeException {
  public InvalidActionCommandException(Character command) {
    super(String.format("Error reading unsupported action command %s", command));
  }
}
