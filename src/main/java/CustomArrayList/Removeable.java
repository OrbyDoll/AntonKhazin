package CustomArrayList;

public interface Removeable<T> {
  /**
   * Удаляет элемент по индексу, смещая последующие элементы влево.
   *
   * @param removeIndex индекс элемента, который нужно удалить.
   * @return удаленный элемент.
   */
  T remove(int removeIndex);
}
