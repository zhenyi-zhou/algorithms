package searching.binarysearchtree;

public class App {
  private static String[] testData = {
    "S", "E", "A", "R", "C", "H", "E", "X", "A", "M", "P", "L", "E"
  };
  
  public static void main(String[] args) {
    BinarySearchTree<String, Integer> st = new BinarySearchTree<String, Integer>();
    int n = testData.length;
    
    for (int i = 0; i < n; i++) {
      st.put(testData[i], i);
    }
    
    System.out.println("Binary Search Tree");
    for (String out : st.keys()) {
      System.out.println(out + " " + st.get(out));
    }
    
    RedBlackBST<String, Integer> rb = new RedBlackBST<String, Integer>();
    n = testData.length;
    
    for (int i = 0; i < n; i++) {
      rb.put(testData[i], i);
    }
    
    System.out.println("Red-Black Binary Search Tree");
    for (String out : rb.keys()) {
      System.out.println(out + " " + rb.get(out));
    }
  }
}