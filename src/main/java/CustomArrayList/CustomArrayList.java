package CustomArrayList;
import java.util.Arrays;

/**
 * CustomArrayList — это кастомная реализация структуры данных ArrayList.
 * Она предоставляет динамический массив, позволяя автоматически изменять
 * размер и хранить объекты любого типа.
 *
 * @param <T> тип элементов в этом списке
 */
public class CustomArrayList<T> implements CustomList<T>{
  private Object[] elements;
  private int size;

  /**
   Конструктор по умолчанию, инициализирует массив размером 5.
   */
  public CustomArrayList() {
    this.elements = new Object[5];
    this.size = 0;
  }

  /**
   * Добавляет элемент в конец списка. При превышении емкости происходит увеличение массива.
   * @param element элемент, который нужно добавить.
   * @throws IllegalArgumentException если элемент равен null.
   */
  @Override
  public void add(T element) {
    if (element == null) {
      throw new IllegalArgumentException("Элемент не может быть null");
    }
    if (size == elements.length) {
      // Увеличиваем capacity массива в два раза
      elements = Arrays.copyOf(elements, elements.length * 2);
    }
    elements[size] = element;
    size++;
  }

  /**
   * Получает элемент по индексу.
   *
   * @param getIndex индекс элемента.
   * @return элемент по указанному индексу.
   */
  @Override
  public T get(int getIndex) {
    return (T) elements[getIndex];
  }

  /**
   * Удаляет элемент по индексу, смещая последующие элементы влево.
   *
   * @param removeIndex индекс элемента, который нужно удалить.
   * @return удаленный элемент.
   */
  @Override
  public T remove(int removeIndex) {
    T removedElement = (T) elements[removeIndex];
    for (int i = removeIndex; i < size - 1; i++) {
      elements[i] = elements[i + 1];
    }
    size--;
    elements[size] = null; // Избегаем утечки памяти
    return removedElement;
  }

  /**
   * Возвращает размер массива.
   * @return размер массива.
   */
  public int size(){
    // Реализация была необязательна, но для итерации по массиву будет необходима
    return size;
  }
}


