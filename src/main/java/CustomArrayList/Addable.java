package CustomArrayList;

public interface Addable<T> {
  /**
   * Добавляет элемент в конец списка. При превышении емкости происходит увеличение массива.
   * @param element элемент, который нужно добавить.
   * @throws IllegalArgumentException если элемент равен null.
   */
  void add(T element);
}
