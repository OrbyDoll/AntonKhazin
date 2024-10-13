package org.example;
import java.util.List;

/**
 * Интерфейс для алгоритмов сортировки.
 * Реализуется для каждого алгоритма сортировки.
 */
public interface SortingMethod {
  /**
   * Метод для сортировки списка.
   *
   * @param list Список элементов для сортировки.
   * @return Отсортированный список.
   * @throws Exception Исключение, если количество элементов в списке превышает допустимое.
   */
  List<Integer> sort(List<Integer> list) throws Exception;

  /**
   * Возвращает тип используемой сортировки, в нашем случае это или MERGE или BUBBLE
   * @return Тип используемой сортировки
   */
  SortTypes type();
}
