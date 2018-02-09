package sorting.mergesort;

public class App {
  private static Character[] testDataTD = {
    'S', 'O', 'R', 'T', 'E', 'X', 'A', 'M', 'P', 'L', 'E'
  };
  
  private static Character[] testDataBU = {
    'S', 'O', 'R', 'T', 'E', 'X', 'A', 'M', 'P', 'L', 'E'
  };
  
  private static void show(Comparable[] a) {
    for (Comparable item : a) {
      System.out.print(item + " ");
    }
  }
  
  public static void main(String[] args) {
    MergeTopDown.sort(testDataTD);
    assert MergeTopDown.isSorted(testDataTD);
    show(testDataTD);
    
    System.out.println();
    
    MergeBottomUp.sort(testDataBU);
    assert MergeBottomUp.isSorted(testDataBU);
    show(testDataBU);
  }
}