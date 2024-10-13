import org.example.*;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SorterTest {

  @Test
  void testSorterWithMergeSort() throws Exception {
    List<SortingMethod> methods = new ArrayList<>();
    methods.add(new MergeSortMethod(10));
    Sorter sorter = new Sorter(methods);

    List<Integer> unsortedList = Arrays.asList(9, 5, 2);
    List<Integer> sortedList = sorter.sort(unsortedList, SortTypes.MERGE);
    assertEquals(Arrays.asList(2, 5, 9), sortedList);
  }

  @Test
  void testSorterWithBubbleSort() throws Exception {
    List<SortingMethod> methods = new ArrayList<>();
    methods.add(new BubbleSortMethod(10));
    Sorter sorter = new Sorter(methods);

    List<Integer> unsortedList = Arrays.asList(8, 4, 1);
    List<Integer> sortedList = sorter.sort(unsortedList, SortTypes.BUBBLE);
    assertEquals(Arrays.asList(1, 4, 8), sortedList);
  }
}
