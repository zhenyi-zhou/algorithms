package sorting.insertionsort;

public class Insertion {
  public static void sort(Comparable[] a) {
    // Sort a[] into increasing order
    int n = a.length;
    
    for (int i = 1; i < n; i++) {
      // Insert a[i] among a[i - 1] a[i - 2] a[i - 3]...
      for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
        exchange(a, j, j - 1);
      }
    }
  }
  
  public static void sort(String[] a, int low, int high, int d) {
    // String sort
    for (int i = low; i <= high; i++) {
      for (int j = i; j > low && less(a[j], a[j - 1], d); j--) {
        exchange(a, j, j - 1);
      }
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
  
  private static boolean less(String v, String w, int d) {
    for (int i = d; i < Math.min(v.length(), w.length()); i++) {
      if (v.charAt(i) < w.charAt(i)) {
        return true;
      }
      if (v.charAt(i) > w.charAt(i)) {
        return false;
      }
    }
    return v.length() < w.length();
  }
  
  private static void exchange(Comparable[] a, int i, int j) {
    Comparable temp = a[i];
    a[i] = a[j];
    a[j] = temp;
  }
}