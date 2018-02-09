package datastructures.unionfind;

public class App {
  private static int[][] testData = {
    {4, 3}, {3, 8}, {6, 5}, {9, 4}, {2, 1}, {5, 0}, {7, 2}, {6, 1}
  };
  
  public static void main(String[] args) {
    UnionFind uf = new UnionFind(10);
    
    for (int[] pair : testData) {
      if (uf.connected(pair[0], pair[1])) {
        continue;
      }
      
      uf.union(pair[0], pair[1]);
      
      System.out.println(pair[0] + " " + pair[1]);
    }
    
    System.out.println(uf.count() + " components");
  }
}