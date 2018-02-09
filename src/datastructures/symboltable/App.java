package datastructures.symboltable;

public class App {
  private static String[] testData = {
    "S", "E", "A", "R", "C", "H", "E", "X", "A", "M", "P", "L", "E"
  };
  
  public static void main(String[] args) {
    SymbolTable<String, Integer> st = new SymbolTable<String, Integer>();
    int n = testData.length;
    
    for (int i = 0; i < n; i++) {
      st.put(testData[i], i);
    }
    
    for (String out : st.keys()) {
      System.out.println(out + " " + st.get(out));
    }
  }
}