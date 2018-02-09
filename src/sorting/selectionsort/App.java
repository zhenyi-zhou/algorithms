package sorting.selectionsort;

public class App {
  private static Character[] testData = {
    'S', 'O', 'R', 'T', 'E', 'X', 'A', 'M', 'P', 'L', 'E'
  };
  
  private static void show(Comparable[] a) {
    for (Comparable item : a) {
      System.out.print(item + " ");
    }
  }
  
  public static void main(String[] args) {
    Selection.sort(testData);
    assert Selection.isSorted(testData);
    show(testData);
  }
}