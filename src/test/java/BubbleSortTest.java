import org.example.BubbleSortMethod;
import org.example.MergeSortMethod;
import org.example.SortTypes;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BubbleSortMethodTest {

  @Test
  void testSortWithValidList() throws Exception {
    BubbleSortMethod bubbleSort = new BubbleSortMethod(10);
    List<Integer> unsortedList = Arrays.asList(5, 3, 8, 1);
    List<Integer> sortedList = bubbleSort.sort(unsortedList);
    assertEquals(Arrays.asList(1, 3, 5, 8), sortedList);
  }

  @Test
  void testSortThrowsException() {
    BubbleSortMethod bubbleSort = new BubbleSortMethod(2);
    List<Integer> unsortedList = Arrays.asList(1, 2, 3);
    Exception exception = assertThrows(Exception.class, () -> {
      bubbleSort.sort(unsortedList);
    });
    assertEquals("Слишком много элементов для BubbleSort. Максимальное количество - 2. Было передано - 3", exception.getMessage());
  }

  @Test
  void testGetSortType() {
    BubbleSortMethod bubbleSort = new BubbleSortMethod(3);
    assertEquals(bubbleSort.type(), SortTypes.BUBBLE);
  }
}
