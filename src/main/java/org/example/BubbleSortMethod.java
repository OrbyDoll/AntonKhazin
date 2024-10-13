package org.example;
import java.util.List;
import java.util.ArrayList;

/**
 * Реализация алгоритма пузырьковой сортировки.
 */
public class BubbleSortMethod implements SortingMethod{
  private final int maxElements;

  /**
   * Конструктор с указанием максимального количества элементов для сортировки.
   *
   * @param maxElements Максимальное количество элементов для сортировки.
   */
  public BubbleSortMethod(int maxElements) {
    this.maxElements = maxElements;
  }

  /**
   * Сортирует список с использованием пузырьковой сортировки.
   *
   * @param list Список для сортировки.
   * @return Отсортированный список.
   * @throws Exception Если количество элементов в списке превышает допустимое значение.
   */
  @Override
  public List<Integer> sort(List<Integer> list) throws Exception {
    if (list.size() > maxElements) {
      throw new Exception("Слишком много элементов для BubbleSort. Максимальное количество - " + maxElements + ". Было передано - " + list.size());
    }
    List<Integer> sortedList = new ArrayList<>(list);
    int n = sortedList.size();
    for (int i = 0; i < n - 1; i++) {
      for (int j = 0; j < n - i - 1; j++) {
        if (sortedList.get(j) > sortedList.get(j + 1)) {
          int temp = sortedList.get(j);
          sortedList.set(j, sortedList.get(j + 1));
          sortedList.set(j + 1, temp);
        }
      }
    }
    return sortedList;
  }

  /**
   * Возвращает тип используемой сортировки
   * @return SortTypes.BUBBLE
   */
  public SortTypes type() {
    return SortTypes.BUBBLE;
  }

}
