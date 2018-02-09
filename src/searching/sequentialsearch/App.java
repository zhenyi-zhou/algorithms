package searching.sequentialsearch;

public class App {
  private static String[] testData = {
    "S", "E", "A", "R", "C", "H", "E", "X", "A", "M", "P", "L", "E"
  };
  
  public static void main(String[] args) {
    SequentialSearchST<String, Integer> st = new SequentialSearchST<String, Integer>();
    int n = testData.length;
    
    for (int i = 0; i < n; i++) {
      st.put(testData[i], i);
    }
    
    for (String out : st.keys()) {
      System.out.println(out + " " + st.get(out));
    }
  }
}