package org.example.Enrichment;

import org.example.User.Message;
import org.example.Exceptions.InvalidPhoneFormatException;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Сервис для обогащения сообщений.
 * <p>
 * Объект {@code EnrichmentService} использует список процессоров обогащения для обработки
 * и обновления содержимого сообщений в зависимости от типа обогащения.
 * </p>
 */
public class EnrichmentService {

  /**
   * Список процессоров обогащения, используемых для обработки сообщений.
   */
  private final List<EnrichmentProcessor> enrichmentProcessors;

  /**
   * Создает новый экземпляр {@code EnrichmentService} с указанными процессорами обогащения.
   *
   * @param enrichmentProcessors список процессоров обогащения
   */
  public EnrichmentService(List<EnrichmentProcessor> enrichmentProcessors) {
    this.enrichmentProcessors = new CopyOnWriteArrayList<>(enrichmentProcessors);
  }

  /**
   * Обогащает переданное сообщение, используя поддерживающий процессор обогащения.
   *
   * @param message сообщение, которое необходимо обогатить
   * @return новое сообщение с обогащенным содержимым, или исходное сообщение, если процессор не найден
   */
  public Message enrich(Message message) {
    return enrichmentProcessors.stream()
        .filter(processor -> processor.supports(message.getEnrichmentType()))
        .findFirst()
        .map(processor -> {
          Map<String, String> enrichedContent = null;
          try {
            enrichedContent = processor.enrich(message.getContent());
          } catch (InvalidPhoneFormatException e) {
            throw new RuntimeException(e);
          }
          return new Message(enrichedContent, message.getEnrichmentType());
        })
        .orElse(message);
  }
}
