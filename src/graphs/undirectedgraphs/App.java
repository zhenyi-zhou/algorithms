package graphs.undirectedgraphs;

import datastructures.linkedlist.Bag;
import datastructures.graph.Graph;

public class App {
  private static int[][] testData = {
    {6, 8}, // vertices and edges
    {0, 5}, {2, 4}, {2, 3}, {1, 2}, {0, 1}, {3, 4}, {3, 5}, {0, 2} // adjacency lists
  };
  
  private static int[][] testData2 = {
    {13, 13}, // vertices and edges
    {0, 5}, {4, 3}, {0, 1}, {9, 12}, {6, 4}, {5, 4}, {0, 2}, // adjacency lists
    {11, 12}, {9, 10}, {0, 6}, {7, 8}, {9, 11}, {5, 3}
  };
  
  public static void main(String[] args) {
    Graph g = new Graph(testData);
    int s = 0;
    
    System.out.println("Depth First Paths");
    DepthFirstPaths dfs = new DepthFirstPaths(g, s);
    for (int i = 0; i < g.v(); i++) {
      System.out.print(s + " to " + i + ": ");
      if (dfs.hasPathTo(i)) {
        for (int x : dfs.pathTo(i)) {
          if (x == s) {
            System.out.print(x);
          } else {
            System.out.print("-" + x);
          }
        }
      }
      System.out.println();
    }
    
    System.out.println("Breadth First Paths");
    BreadthFirstPaths bfs = new BreadthFirstPaths(g, s);
    for (int i = 0; i < g.v(); i++) {
      System.out.print(s + " to " + i + ": ");
      if (bfs.hasPathTo(i)) {
        for (int x : bfs.pathTo(i)) {
          if (x == s) {
            System.out.print(x);
          } else {
            System.out.print("-" + x);
          }
        }
      }
      System.out.println();
    }
    
    System.out.println("Connected Components");
    CC cc = new CC(g);
    
    int m = cc.count();
    System.out.println(m + " compontents");
    
    Bag<Integer>[] components = (Bag<Integer>[]) new Bag[m];
    
    for (int i = 0; i < m; i++) {
      components[i] = new Bag<Integer>();
    }
    for (int v = 0; v < g.v(); v++) {
      components[cc.id(v)].add(v);
    }
    for (int i = 0; i < m; i++) {
      for (int v : components[i]) {
        System.out.print(v + " ");
      }
      System.out.println();
    }
  }
}