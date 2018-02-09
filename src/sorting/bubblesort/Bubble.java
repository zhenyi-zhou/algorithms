package sorting.bubblesort;

public class Bubble {
  public static void sort(Comparable[] a) {
    int n = a.length;
    boolean isExchanged;
    
    do {
      isExchanged = false;
      
      for (int i = 1; i < n; i++) {
        if (less(a[i], a[i - 1])) {
          exchange(a, i, i - 1);
          isExchanged = true;
        }
      }
      
      n--;
    } while (isExchanged);
  }
  
  public static boolean isSorted(Comparable[] a) {
    for (int i = 1; i < a.length; i++) {
      if (less(a[i], a[i - 1])) {
        return false;
      }
    }
    
    return true;
  }
  
  private static boolean less(Comparable x, Comparable y) {
    return x.compareTo(y) < 0;
  }
  
  private static void exchange(Comparable[] a, int i, int j) {
    Comparable temp = a[i];
    a[i] = a[j];
    a[j] = temp;
  }
}