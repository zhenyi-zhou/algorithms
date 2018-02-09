package datastructures.array;

public class App {
  private static String[] testData = {
    "One", "Two", "Three", "-",
    "Four", "Five", "Six", "-",
    "Seven", "Eight", "Nine", "-", "Ten"
  };
  
  public static void main(String[] args) {
    Bag<String> bag = new Bag<String>();
    Queue<String> queue = new Queue<String>();
    Stack<String> stack = new Stack<String>();
    
    for (String in : testData) {
      if (in == "-") {
        String qItem = queue.dequeue();
        String sItem = stack.pop();
      } else {
        bag.add(in);
        queue.enqueue(in);
        stack.push(in);
      }
    }
    
    System.out.println("*** Bag ***");
    
    for (String out : bag) {
      System.out.println(out);
    }
    
    System.out.println("*** Queue ***");
    
    for (String out : queue) {
      System.out.println(out);
    }
    
    System.out.println("*** Stack ***");
    
    for (String out : stack) {
      System.out.println(out);
    }
  }
}