package sorting.mergesort;

public class MergeTopDown {
  private static Comparable[] aux; // auxiliary array for merges
  
  public static void sort(Comparable[] a) {
    aux = new Comparable[a.length];
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
  
  private static void sort(Comparable[] a, int low, int high) {
    // Sort a[low...high]
    if (high <= low) {
      return;
    }
    
    int medium = low + (high - low) / 2;
    sort(a, low, medium);
    sort(a, medium + 1, high);
    merge(a, low, medium, high);
  }
  
  private static void merge(Comparable[] a, int low, int medium, int high) {
    // Merge a[low...medium] with a[medium...high]
    int i = low, j = medium + 1;
    
    // Copy a[low...high] to aux[low...high]
    for (int k = low; k <= high; k++) {
      aux[k] = a[k];
    }
    
    // Merge back to a[low...high]
    for (int k = low; k <= high; k++) {
      if (i > medium) {                  // left part is empty
        a[k] = aux[j++];                 //   use right element
      } else if (j > high) {             // right part is empty
        a[k] = aux[i++];                 //   use left element
      } else if (less(aux[j], aux[i])) { // right element < left element
        a[k] = aux[j++];                 //   use right element
      } else {                           // right element >= left element
        a[k] = aux[i++];                 //   use left element
      }
    }
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