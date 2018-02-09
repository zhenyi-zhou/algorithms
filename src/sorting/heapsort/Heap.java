package sorting.heapsort;

public class Heap {
  public static void sort(Comparable[] a) { // 1-based index
    int n = a.length;
    
    for (int i = n / 2; i >= 1; i--) {
      sink(a, i ,n);
    }
    
    while (n > 1) {
      exchange(a, 1, n--);
      sink(a, 1, n);
    }
  }
  
  public static boolean isSorted(Comparable[] a) {
    for (int i = 2; i < a.length; i++) {
      if (less(a, i, i - 1)) {
        return false;
      }
    }
    
    return true;
  }
  
  private static boolean less(Comparable[] a, int i, int j) {
    return a[i - 1].compareTo(a[j - 1]) < 0; // support 1-based indexing
  }
  
  private static void exchange(Comparable[] a, int i, int j) {
    Comparable temp = a[i - 1];
    a[i - 1] = a[j - 1];
    a[j - 1] = temp;
  }
  
  private static void sink(Comparable[] a, int i, int j) {
    while (2 * i <= j) {
      int k = 2 * i;
      
      if (k < j && less(a, k, k + 1)) {
        k++;
      }
      
      if (!less(a, i, k)) {
        break;
      }
      
      exchange(a, i, k);
      
      i = k;
    }
  }
}