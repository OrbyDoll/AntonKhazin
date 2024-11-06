package org.example.Exception;

public class InvalidPhoneFormatException extends Exception {
  public InvalidPhoneFormatException() {
    super("Номер телефона не можеть быть пустым или равен null");
  }
}
