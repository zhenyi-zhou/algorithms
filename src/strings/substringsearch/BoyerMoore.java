package strings.substringsearch;

public class BoyerMoore {
  private String pat;
  private int[] right;
  
  public BoyerMoore(String pat) {
    // Compute skip table.
    this.pat = pat;
    int m = pat.length();
    int r = 256;
    right = new int[r];
    for (int c = 0; c < r; c++) {
      right[c] = -1;            // -1 for chars not in pattern
    }
    for (int j = 0; j < m; j++) {
      right[pat.charAt(j)] = j; // rightmost position for chars in pattern
    }
  }
  
  public int search(String txt) {
    // Search for pattern in txt.
    int n = txt.length();
    int m = pat.length();
    int skip;
    for (int i = 0; i <= n - m; i += skip) {
      // Does the pattern match the text at position i?
      skip = 0;
      for (int j = m - 1; j >= 0; j--) {
        if (pat.charAt(j) != txt.charAt(i + j)) {
          skip = j - right[txt.charAt(i + j)];
          if (skip < 1) {
            skip = 1;
            break;
          }
        }
      }
      if (skip == 0) {
        return i; // found
      }
    }
    return n;     // not found
  }
}