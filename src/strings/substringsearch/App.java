package strings.substringsearch;

public class App {
  private static String[][] testData = {
    {"abracadabra", "abacadabrabracabracadabrabrabracad"},
    {"rab", "abacadabrabracabracadabrabrabracad"},
    {"bcara", "abacadabrabracabracadabrabrabracad"},
    {"rabrabracad", "abacadabrabracabracadabrabrabracad"},
    {"abacad", "abacadabrabracabracadabrabrabracad"}
  };
  
  public static void main(String[] args) {
    System.out.println("Knuth-Morris-Pratt Substring Search");
    for (String[] s : testData) {
      String pat = s[0];
      String txt = s[1];
      KMP kmp = new KMP(pat);
      System.out.println("text:    " + txt);
      int offset = kmp.search(txt);
      System.out.print("pattern: ");
      for (int i = 0; i < offset; i++) {
        System.out.print(" ");
      }
      System.out.println(pat);
    }
    
    System.out.println("Boyer-Moore Substring Search");
    for (String[] s : testData) {
      String pat = s[0];
      String txt = s[1];
      BoyerMoore bm = new BoyerMoore(pat);
      System.out.println("text:    " + txt);
      int offset = bm.search(txt);
      System.out.print("pattern: ");
      for (int i = 0; i < offset; i++) {
        System.out.print(" ");
      }
      System.out.println(pat);
    }
    
    System.out.println("Rabin-Karp Substring Search");
    for (String[] s : testData) {
      String pat = s[0];
      String txt = s[1];
      RabinKarp rk = new RabinKarp(pat);
      System.out.println("text:    " + txt);
      int offset = rk.search(txt);
      System.out.print("pattern: ");
      for (int i = 0; i < offset; i++) {
        System.out.print(" ");
      }
      System.out.println(pat);
    }
  }
}