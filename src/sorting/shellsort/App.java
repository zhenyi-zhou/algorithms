package sorting.shellsort;

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
    Shell.sort(testData);
    assert Shell.isSorted(testData);
    show(testData);
  }
}