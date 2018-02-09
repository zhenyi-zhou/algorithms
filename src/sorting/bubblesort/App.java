package sorting.bubblesort;

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
    Bubble.sort(testData);
    assert Bubble.isSorted(testData);
    show(testData);
  }
}