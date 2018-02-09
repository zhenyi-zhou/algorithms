package sorting.shellsort;

public class Shell {
  public static void sort(Comparable[] a) {
    // Sort a[] into increasing order
    int n = a.length;
    int h = 1;
    
    while (h < n / 3) {
      h = 3 * h + 1; // 1, 4, 13, 40, 121, 364, 1093...
    }
    
    while (h >= 1) {
      // h-sort the array
      for (int i = h; i < n; i++) {
        // Insert a[i] among a[i - h] a[i - 2h] a[i - 3h]...
        for (int j = i; j >= h && less(a[j], a[j - 1]); j -= h) {
          exchange(a, j, j - h);
        }
      }
      
      h /= 3;
    }
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