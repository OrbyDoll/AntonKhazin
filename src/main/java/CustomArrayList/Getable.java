package CustomArrayList;

public interface Getable<T> {
  /**
   * Получает элемент по индексу.
   *
   * @param getIndex индекс элемента.
   * @return элемент по указанному индексу.
   */
  T get(int getIndex);
}
