package strings.substringsearch;

public class KMP {
  private String pat;
  private int[][] dfa;
  
  public KMP(String pat) {
    // Build DFA from pattern.
    this.pat = pat;
    int m = pat.length();
    int r = 256;
    dfa = new int[r][m];
    dfa[pat.charAt(0)][0] = 1;
    for (int x = 0, j = 1; j < m; j++) {
      for (int c = 0; c < r; c++) {
        dfa[c][j] = dfa[c][x];       // copy mismatch cases.
      }
      dfa[pat.charAt(j)][j] = j + 1; // set match case.
      x = dfa[pat.charAt(j)][x];     // update restart state.
    }
  }
  
  public int search(String txt) {
    // Simulate operation of DFA on txt.
    int i, j, n = txt.length(), m = pat.length();
    for (i = 0, j = 0; i < n && j < m; i++) {
      j = dfa[txt.charAt(i)][j];
    }
    if (j == m) {
      return i - m; // found (hit end of pattern)
    } else {
      return n;     // not found (hit end of text)
    }
  }
}