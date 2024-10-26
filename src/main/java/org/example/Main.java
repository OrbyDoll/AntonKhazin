package org.example;
import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    ArrayList<Integer> list = new ArrayList<>();
    System.out.println("Введите список чисел через пробел");
    String[] stringList = input.nextLine().split(" ");
    for (String num : stringList) {
      list.add(Integer.parseInt(num));
    }
    ArrayList<SortingMethod> sorts = new ArrayList<>();
    sorts.add(new MergeSortMethod(10));
    sorts.add(new BubbleSortMethod(15));

    Sorter sorter = new Sorter(sorts);
    System.out.println("Выберите алгоритм сортировки:\n" +
                       "1. Сортировка слиянием (MergeSort)\n" +
                       "2. Пузырьковая сортировка (BubbleSort)");
    int choice = input.nextInt();
    List<Integer> sortedList;
    try {
      switch (choice) {
        case 1:
          sortedList = sorter.sort(list, SortTypes.MERGE);
          break;
        case 2:
          sortedList = sorter.sort(list, SortTypes.BUBBLE);
          break;
        default:
          throw new Exception("Такого типа сортировки не сущетсвует");
      }
      System.out.println("Отсортированный список: " + sortedList);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    } finally {
      input.close();
    }
  }
}