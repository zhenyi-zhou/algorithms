package strings.stringsorts;

public class LSD {
  public static void sort(String[] a, int w) {
    // Sort a[] on leading w characters.
    int n = a.length;
    int r = 256;
    String aux[] = new String[n];
    for (int d = w - 1; d >= 0; d--) {
      // Sort by key-indexed counting on dth char.
      int[] count = new int[r + 1]; // Compute frequency counts.
      for (int i = 0; i < n; i++) {
        count[a[i].charAt(d) + 1]++;
      }
      for (int i = 0; i < r; i++) { // Transform counts to indices.
        count[i + 1] += count[i];
      }
      for (int i = 0; i < n; i++) { // Distribute.
        aux[count[a[i].charAt(d)]++] = a[i];
      }
      for (int i = 0; i < n; i++) { // Copy back.
        a[i] = aux[i];
      }
    }
  }
}