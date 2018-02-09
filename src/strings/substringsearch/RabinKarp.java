package strings.substringsearch;

import java.math.BigInteger;
import java.util.Random;

public class RabinKarp {
  private String pat;   // pattern (only needed for Las Vegas)
  private long patHash; // pattern hash value
  private int m;        // pattern length
  private long q;       // a large prime
  private int r = 256;  // alphabet size
  private long rm;      // r ^ (m - 1) & q
  
  public RabinKarp(String pat) {
    this.pat = pat;
    m = pat.length();
    q = longRandomPrime();
    rm = 1;
    for (int i = 1; i <= m - 1; i++) { // compute r ^ (m - 1) % q for use
      rm = (r * rm) % q;               //   in removing leading digit.
    }
    patHash = hash(pat, m);
  }
  
  public int search(String txt) {
    // Search for hash match in text.
    int n = txt.length();
    if (n < m) {
      return n;
    }
    long txtHash = hash(txt, m);
    if ((patHash == txtHash) && check(txt, 0)) {
      return 0;        // match at beginning
    }
    for (int i = m; i < n; i++) {
      // Remove leading digit, add trailing digit, check for match.
      txtHash = (txtHash + q - rm * txt.charAt(i - m) % q) % q;
      txtHash = (txtHash * r + txt.charAt(i)) % q;
      int offset = i - m + 1;
      if ((patHash == txtHash) && check(txt, offset)) {
        return offset; // match
      }
    }
    return n;          // no match found
  }
  
  private boolean check(int i) { // Monte Carlo: always return true
    return true;
  }
  
  private boolean check(String txt, int i) { // Las Vegas: check pat vs txt(i..i - m + 1)
    for (int j = 0; j < m; j++) {
      if (pat.charAt(j) != txt.charAt(i + j)) {
        return false;
      }
    }
    return true;
  }
  
  private long hash(String key, int m) {
    // Compute hash for key[0..m - 1]
    long h = 0;
    for (int j = 0; j < m; j++) {
      h = (r * h + key.charAt(j)) % q;
    }
    return h;
  }
  
  private static long longRandomPrime() {
    BigInteger prime = BigInteger.probablePrime(31, new Random());
    return prime.longValue();
  }
}