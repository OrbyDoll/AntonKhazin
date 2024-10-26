package org.example.User;

import org.example.Exceptions.InvalidPhoneFormatException;
import org.example.Exceptions.InvalidUserException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Реализация интерфейса {@link UserRepository}, хранящая данные пользователей в памяти.
 * <p>
 * Объект {@code MemoryRepository} использует {@link ConcurrentHashMap} для
 * хранения пользователей, обеспечивая безопасный доступ в многопоточной среде.
 * </p>
 */
public class MemoryRepository implements UserRepository {

  /**
   * Карта для хранения пользователей, где ключом является номер телефона (MSISDN),
   * а значением - объект {@link User}.
   */
  private final Map<String, User> users = new ConcurrentHashMap<>();

  /**
   * Ищет пользователя по его номеру телефона (MSISDN).
   *
   * @param msisdn номер телефона пользователя в формате MSISDN
   * @return объект {@link User}, соответствующий заданному номеру телефона
   * @throws InvalidPhoneFormatException если формат номера телефона неверен (null или пустой)
   */
  @Override
  public User findByMSISDN(String msisdn) throws InvalidPhoneFormatException {
    if (msisdn == null || msisdn.isEmpty()) {
      throw new InvalidPhoneFormatException();
    }
    return users.get(msisdn);
  }

  /**
   * Обновляет информацию о пользователе по его номеру телефона (MSISDN).
   *
   * @param msisdn номер телефона пользователя в формате MSISDN
   * @param user   объект {@link User}, содержащий обновленную информацию
   * @throws InvalidPhoneFormatException если формат номера телефона неверен (null или пустой)
   * @throws InvalidUserException если объект пользователя равен null
   */
  @Override
  public void updateByMSISDN(String msisdn, User user) throws InvalidPhoneFormatException {
    if (msisdn == null || msisdn.isEmpty()) {
      throw new InvalidPhoneFormatException();
    } else if (user == null) {
      throw new InvalidUserException();
    }
    users.put(msisdn, user);
  }
}
