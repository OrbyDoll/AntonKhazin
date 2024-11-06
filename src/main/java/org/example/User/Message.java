package org.example.User;

import java.util.Map;
import org.example.Enrichment.EnrichmentType;
/**
 * Представляет сообщение с содержимым и типом обогащения.
 * <p>
 * Объект {@code Message} содержит {@code Map} пар ключ-значение как свое содержимое и
 * тип обогащения {@code EnrichmentType}. {@code Map}
 * может хранить различные типы информации в зависимости от типа обогащения.
 * </p>
 */
public class Message {

  /**
   * Map, с содержимым сообщения в виде пар ключ-значение.
   */
  private final Map<String, String> content;

  /**
   * Тип обогащения, примененный к сообщению.
   */
  private final EnrichmentType enrichmentType;

  /**
   * Создает объект {@code Message} с указанным содержимым и типом обогащения.
   *
   * @param content         Map пар ключ-значение, представляющий содержимое сообщения
   * @param enrichmentType  тип обогащения сообщения {@link EnrichmentType}
   */
  public Message(Map<String, String> content, EnrichmentType enrichmentType) {
    this.content = content;
    this.enrichmentType = enrichmentType;
  }

  /**
   * Возвращает содержимое сообщения.
   *
   * @return Map пар ключ-значение, представляющая содержимое сообщения
   */
  public Map<String, String> getContent() {
    return content;
  }

  /**
   * Возвращает тип обогащения сообщения.
   *
   * @return тип обогащения, представленный объектом {@link EnrichmentType}
   */
  public EnrichmentType getEnrichmentType() {
    return enrichmentType;
  }
}
