package org.example.Enrichment;

import org.example.User.Message;
import org.example.Exceptions.InvalidPhoneFormatException;

import java.util.Map;

/**
 * Интерфейс для обработки обогащения данных.
 * <p>
 * Объект {@code EnrichmentProcessor} определяет методы для проверки поддержки
 * определенного типа обогащения и для обогащения содержимого.
 * </p>
 */
public interface EnrichmentProcessor {

  /**
   * Проверяет, поддерживает ли данный процессор указанный тип обогащения.
   *
   * @param enrichmentType тип обогащения, который необходимо проверить
   * @return {@code true}, если тип обогащения поддерживается, {@code false} в противном случае
   */
  boolean supports(Message.EnrichmentType enrichmentType);

  /**
   * Обогащает переданное содержимое, добавляя или изменяя данные в Map.
   *
   * @param content Map пар ключ-значение, представляющий содержимое для обогащения
   * @return обновленный Map пар ключ-значение после обогащения
   * @throws InvalidPhoneFormatException если формат номера телефона неверен
   */
  Map<String, String> enrich(Map<String, String> content) throws InvalidPhoneFormatException;
}
