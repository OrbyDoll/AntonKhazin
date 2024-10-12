package CustomArrayList;

public interface CustomList<T> {

  /**
   * Добавляет элемент в конец списка. При превышении емкости происходит увеличение массива.
   * @param element элемент, который нужно добавить.
   * @throws IllegalArgumentException если элемент равен null.
   */
  void add(T element);

  /**
   * Получает элемент по индексу.
   *
   * @param getIndex индекс элемента.
   * @return элемент по указанному индексу.
   */
  T get(int getIndex);

  /**
   * Удаляет элемент по индексу, смещая последующие элементы влево.
   *
   * @param removeIndex индекс элемента, который нужно удалить.
   * @return удаленный элемент.
   */
  T remove(int removeIndex);
}
