package org.example.Exceptions;

public class InvalidUserException extends RuntimeException {
  public InvalidUserException() {
    super("User не может быть равен null");
  }
}
