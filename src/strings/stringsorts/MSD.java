package strings.stringsorts;

import sorting.insertionsort.Insertion;

public class MSD {
  private static int r = 256;      // radix
  private static final int m = 15; // cutoff for small subarrays
  private static String[] aux;     // auxiliary array for distribution
  
  public static void sort(String[] a) {
    int n = a.length;
    aux = new String[n];
    sort(a, 0, n - 1, 0);
  }
  
  private static void sort(String[] a, int low, int high, int d) {
    // Sort from a[low] to a[high], starting at the dth character.
    if (high <= low + m) {
      Insertion.sort(a, low, high, d);
      return;
    }
    
    int[] count = new int[r + 2];      // Compute frequency counts.
    for (int i = low; i <= high; i++) {
      count[charAt(a[i], d) + 2]++;
    }
    for (int i = 0; i < r + 1; i++) {  // Transform counts to indices.
      count[i + 1] += count[i];
    }
    for (int i = low; i <= high; i++) { // Distribute.
        aux[count[charAt(a[i], d) + 1]++] = a[i];
      }
    for (int i = low; i <= high; i++) { // Copy back.
      a[i] = aux[i - low];
    }
    
    // Recursively sort for each character value.
    for (int i = 0; i < r; i++) {
      sort(a, low + count[i], low + count[i + 1] - 1, d + 1);
    }
  }
  
  private static int charAt(String s, int d) {
    if (d < s.length()) {
      return s.charAt(d);
    } else {
      return -1;
    }
  }
}