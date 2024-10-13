import org.example.MergeSortMethod;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MergeSortMethodTest {

  @Test
  void testSortWithValidList() throws Exception {
    MergeSortMethod mergeSort = new MergeSortMethod(10);
    List<Integer> unsortedList = Arrays.asList(3, 1, 2);
    List<Integer> sortedList = mergeSort.sort(unsortedList);
    assertEquals(Arrays.asList(1, 2, 3), sortedList);
  }

  @Test
  void testSortThrowsException() {
    MergeSortMethod mergeSort = new MergeSortMethod(2);
    List<Integer> unsortedList = Arrays.asList(1, 2, 3);
    Exception exception = assertThrows(Exception.class, () -> {
      mergeSort.sort(unsortedList);
    });
    assertEquals("Слишком много элементов для MergeSort. Максимальное количество - 2. Было передано - 3", exception.getMessage());
  }
}
