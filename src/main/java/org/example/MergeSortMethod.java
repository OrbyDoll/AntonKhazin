package org.example;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;

/**
 * Реализация алгоритма сортировки с использованием Collections.sort (сортировка слиянием).
 */
public class MergeSortMethod implements SortingMethod{
  private final int maxElements;

  /**
   * Конструктор с указанием максимального количества элементов для сортировки.
   *
   * @param maxElements Максимальное количество элементов для сортировки.
   */
  public MergeSortMethod(int maxElements) {
    this.maxElements = maxElements;
  }

  /**
   * Сортирует список с использованием сортировки слиянием.
   *
   * @param list Список для сортировки.
   * @return Отсортированный список.
   * @throws Exception Если количество элементов в списке превышает допустимое значение.
   */
  @Override
  public List<Integer> sort(List<Integer> list) throws Exception {
    if (list.size() > maxElements) {
      throw new Exception("Слишком много элементов для MergeSort. Максимальное количество - " + maxElements + ". Было передано - " + list.size());
    }
    List<Integer> sortedList = new ArrayList<>(list);
    Collections.sort(sortedList);
    return sortedList;
  }

  /**
   * Возвращает тип используемой сортировки
   * @return SortTypes.MERGE
   */
  public SortTypes type() {
    return SortTypes.MERGE;
  }
}
