package searching.hashtables;

public class App {
  private static String[] testData = {
    "S", "E", "A", "R", "C", "H", "E", "X", "A", "M", "P", "L", "E"
  };
  
  public static void main(String[] args) {
    SeparateChainingHashST<String, Integer> sc = new SeparateChainingHashST<String, Integer>();
    int n = testData.length;
    
    for (int i = 0; i < n; i++) {
      sc.put(testData[i], i);
    }
    
    System.out.println("Seperate Chaining Hash Table");
    for (String out : sc.keys()) {
      System.out.println(out + " " + sc.get(out));
    }
    
    LinearProbingHashST<String, Integer> lb = new LinearProbingHashST<String, Integer>();
    n = testData.length;
    
    for (int i = 0; i < n; i++) {
      lb.put(testData[i], i);
    }
    
    System.out.println("Linear Probing Hash Table");
    for (String out : lb.keys()) {
      System.out.println(out + " " + lb.get(out));
    }
  }
}