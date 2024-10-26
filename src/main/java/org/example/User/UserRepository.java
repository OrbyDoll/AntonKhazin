package org.example.User;

import org.example.Exceptions.InvalidPhoneFormatException;

/**
 * Интерфейс для работы с хранилищем пользователей.
 * <p>
 * Объект {@code UserRepository} предоставляет методы для поиска и обновления
 * пользователей по их номеру телефона (MSISDN). В случае неверного формата номера
 * телефона выбрасывается {@link InvalidPhoneFormatException}.
 * </p>
 */
public interface UserRepository {

  /**
   * Ищет пользователя по его номеру телефона (MSISDN).
   *
   * @param msisdn номер телефона пользователя в формате MSISDN
   * @return объект {@link User}, соответствующий заданному номеру телефона
   * @throws InvalidPhoneFormatException если формат номера телефона неверен
   */
  User findByMSISDN(String msisdn) throws InvalidPhoneFormatException;

  /**
   * Обновляет информацию о пользователе по его номеру телефона (MSISDN).
   *
   * @param msisdn номер телефона пользователя в формате MSISDN
   * @param user   объект {@link User}, содержащий обновленную информацию
   * @throws InvalidPhoneFormatException если формат номера телефона неверен
   */
  void updateByMSISDN(String msisdn, User user) throws InvalidPhoneFormatException;
}
