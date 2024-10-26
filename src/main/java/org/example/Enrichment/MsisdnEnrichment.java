package org.example.Enrichment;

import org.example.User.Message;
import org.example.User.User;
import org.example.Exceptions.InvalidPhoneFormatException;
import org.example.User.UserRepository;

import java.util.Map;

/**
 * Процессор обогащения для работы с номерами телефонов (MSISDN).
 * <p>
 * Объект {@code MsisdnEnrichment} использует репозиторий пользователей для извлечения информации
 * о пользователе на основе номера телефона и обогащает переданное содержимое.
 * </p>
 */
public class MsisdnEnrichment implements EnrichmentProcessor {

  /**
   * Репозиторий пользователей, используемый для поиска пользователей по номеру телефона.
   */
  private final UserRepository userRepository;

  /**
   * Создает новый экземпляр {@code MsisdnEnrichment} с указанным репозиторием пользователей.
   *
   * @param userRepository репозиторий пользователей
   */
  public MsisdnEnrichment(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  /**
   * Проверяет, поддерживает ли процессор указанный тип обогащения.
   *
   * @param enrichmentType тип обогащения, который необходимо проверить
   * @return {@code true}, если тип обогащения равен {@link Message.EnrichmentType#MSISDN},
   *         {@code false} в противном случае
   */
  @Override
  public boolean supports(Message.EnrichmentType enrichmentType) {
    return enrichmentType == Message.EnrichmentType.MSISDN;
  }

  /**
   * Обогащает переданное содержимое, добавляя информацию о пользователе,
   * связанную с указанным номером телефона (MSISDN).
   *
   * @param content карта пар ключ-значение, представляющая содержимое для обогащения
   * @return обновленная карта пар ключ-значение после обогащения
   * @throws InvalidPhoneFormatException если формат номера телефона неверен
   */
  @Override
  public Map<String, String> enrich(Map<String, String> content) throws InvalidPhoneFormatException {
    String msisdn = content.get("msisdn");
    if (msisdn == null) return content;

    User user = userRepository.findByMSISDN(msisdn);
    if (user != null) {
      content.put("firstName", user.getFirstName());
      content.put("lastName", user.getLastName());
    }
    return content;
  }
}
