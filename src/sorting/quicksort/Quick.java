package sorting.quicksort;

import java.util.Random;

public class Quick {
  private static Random random;
  
  static {
    random = new Random(System.currentTimeMillis());
  }
  
  public static void sort(Comparable[] a) {
    shuffle(a); // eliminate dependence on input
    sort(a, 0, a.length - 1);
  }
  
  public static boolean isSorted(Comparable[] a) {
    for (int i = 1; i < a.length; i++) {
      if (less(a[i], a[i - 1])) {
        return false;
      }
    }
    
    return true;
  }
  
  private static void shuffle(Comparable[] a) {
    int n = a.length;
    
    for (int i = 0; i < n; i++) {
      int r = i + random.nextInt(n - i);
      Comparable temp = a[i];
      a[i] = a[r];
      a[r] = temp;
    }
  }
  
  private static void sort(Comparable[] a, int low, int high) {
    if (high <= low) {
      return;
    }
    
    int i = partition(a, low, high);
    sort(a, low, i - 1);
    sort(a, i + 1, high);
  }
  
  private static int partition(Comparable[] a, int low, int high) {
    // Partition into a[low...i - 1] a[i] a[i + 1...high]
    int i = low, j = high + 1; // left and right scan indices
    Comparable item = a[low];  // partitioning item
    
    while (true) {
      while (less(a[++i], item)) { // scan left ->
        if (i == high) {
          break;
        }
      }
      
      while (less(item, a[--j])) { // scan right <-
        if (j == low) {
          break;
        }
      }
      
      if (i >= j) {                // check for scan complete
        break;
      }
      
      exchange(a, i, j);
    }
    
    // Put item = a[j] into position with a[low...j - 1] <= a[j] <= a[j + 1...high]
    exchange(a, low, j);
    
    return j;
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