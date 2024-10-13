package org.example;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс, управляющий сортировками.
 * Принимает список алгоритмов сортировки и пытается отсортировать список одним из них.
 */
public class Sorter {
  private final List<SortingMethod> sortings;

  /**
   * Конструктор с передачей списка доступных алгоритмов сортировки.
   *
   * @param sortings Список алгоритмов сортировки.
   */
  public Sorter(List<SortingMethod> sortings) {
    this.sortings = sortings;
  }

  /**
   * Пытается отсортировать список, используя каждый из алгоритмов, пока не найдет подходящий.
   *
   * @param list Список для сортировки.
   * @return Отсортированный список.
   */
  public List<Integer> sort(List<Integer> list, SortTypes type) {
    for (SortingMethod sortMethod : sortings) {
      if (sortMethod.type().equals(type)) {
        try {
          return sortMethod.sort(list);
        } catch (Exception e) {
          System.out.println(e.getMessage());
        }
      }
    }
    return new ArrayList<>();
  }
}
