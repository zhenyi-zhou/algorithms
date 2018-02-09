package strings.regularexpressions;

public class App {
  private static String[][] testData = {
    {"(A*B|AC)D", "AAAABD"},
    {"(A*B|AC)D", "AAAAC"},
    {"(a|(bc)*d)*", "abcbcd"},
    {"(a|(bc)*d)*", "abcbcbcdaaaabcbcdaaaddd"}
  };
  
  public static void main(String[] args) {
    for (String[] s : testData) {
      String regexp = s[0];
      String txt = s[1];
      NFA nfa = new NFA(regexp);
      System.out.println("text: " + txt);
      System.out.println("regexp: " + regexp);
      System.out.println(nfa.recognizes(txt));
    }
  }
}