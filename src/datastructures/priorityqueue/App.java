package datastructures.priorityqueue;

public class App {
  private static String[] testData = {
    "One", "Two", "Three", "-",
    "Four", "Five", "Six", "-",
    "Seven", "Eight", "Nine", "-", "Ten"
  };
  
  public static void main(String[] args) {
    MaxPQ<String> max = new MaxPQ<String>();
    
    for (String in : testData) {
      if (in == "-") {
        String temp = max.deleteMax();
      } else {
        max.insert(in);
      }
    }
    
    System.out.println("Max heap priority queue size is " + max.size());
    
    for (String out : max) {
      System.out.println(out);
    }
    
    MinPQ<String> min = new MinPQ<String>();
    
    for (String in : testData) {
      if (in == "-") {
        String temp2 = min.deleteMin();
      } else {
        min.insert(in);
      }
    }
    
    System.out.println("Min heap priority queue size is " + min.size());
    
    for (String out : min) {
      System.out.println(out);
    }
    
    IndexMaxPQ<String> idxMax = new IndexMaxPQ<String>(testData.length);
    
    for (int i = 0; i < testData.length; i++) {
      if (testData[i] == "-") {
        int temp3 = idxMax.deleteMax();
      } else {
        idxMax.insert(i, testData[i]);
      }
    }
    
    System.out.println("Index max heap priority queue size is " + idxMax.size());
    
    for (int idx : idxMax) {
      System.out.println(idx + " " + testData[idx]);
    }
    
    IndexMinPQ<String> idxMin = new IndexMinPQ<String>(testData.length);
    
    for (int i = 0; i < testData.length; i++) {
      if (testData[i] == "-") {
        int temp3 = idxMin.deleteMin();
      } else {
        idxMin.insert(i, testData[i]);
      }
    }
    
    System.out.println("Index min heap priority queue size is " + idxMin.size());
    
    for (int idx : idxMin) {
      System.out.println(idx + " " + testData[idx]);
    }
  }
}