package datastructures.graph;

public class App {
  private static int[][] testG = {
    {13, 13}, // vertices and edges
    {0, 5}, {4, 3}, {0, 1}, {9, 12}, {6, 4}, {5, 4}, {0, 2}, // adjacency lists
    {11, 12}, {9, 10}, {0, 6}, {7, 8}, {9, 11}, {5, 3}
  };
  
  private static int[][] testD = {
    {13, 22},
    {4, 2}, {2, 3}, {3, 2}, {6, 0}, {0, 1}, {2, 0}, {11, 12},
    {12, 9}, {9, 10}, {9, 11}, {8, 9}, {10, 12}, {11, 4}, {4, 3},
    {3, 5}, {7, 8}, {8, 7}, {5, 4}, {0, 5}, {6, 4}, {6, 9}, {7, 6}
  };
  
  private static double[][] testEWG = {
    {8, 16},
    {4, 5, 0.35}, {4, 7, 0.37}, {5, 7, 0.28}, {0, 7, 0.16},
    {1, 5, 0.32}, {0, 4, 0.38}, {2, 3, 0.17}, {1, 7, 0.19},
    {0, 2, 0.26}, {1, 2, 0.36}, {1, 3, 0.29}, {2, 7, 0.34},
    {6, 2, 0.40}, {3, 6, 0.52}, {6, 0, 0.58}, {6, 4, 0.93}
  };
  
  private static double[][] testEWD = {
    {8, 15},
    {4, 5, 0.35}, {5, 4, 0.35}, {4, 7, 0.37}, {5, 7, 0.28}, {7, 5, 0.28},
    {5, 1, 0.32}, {0, 4, 0.38}, {0, 2, 0.26}, {7, 3, 0.39}, {1, 3, 0.29},
    {2, 7, 0.34}, {6, 2, 0.40}, {3, 6, 0.52}, {6, 0, 0.58}, {6, 4, 0.93}
  };
  
  public static void main(String[] args) {
    System.out.println("Graph");
    Graph g = new Graph(testG);
    System.out.println(g.v() + " vertices, " + g.e() + " edges");
    
    for (int i = 0; i < g.v(); i++) {
      System.out.print(i + ": ");
      for (int v : g.adj(i)) {
        System.out.print(v + " ");
      }
      System.out.println();
    }
    
    System.out.println("Edge");
    Edge e = new Edge(12, 24, 5.67);
    System.out.println(e);
    
    System.out.println("Edge Weighted Graph");
    EdgeWeightedGraph ewg = new EdgeWeightedGraph(testEWG);
    System.out.println(ewg.v() + " vertices, " + ewg.e() + " edges");
    
    for (int i = 0; i < ewg.v(); i++) {
      System.out.print(i + ": ");
      for (Edge x : ewg.adj(i)) {
        System.out.print(x + " ");
      }
      System.out.println();
    }
    
    System.out.println("Directed Graph");
    Digraph d = new Digraph(testD);
    System.out.println(d.v() + " vertices, " + d.e() + " edges");
    
    for (int i = 0; i < d.v(); i++) {
      System.out.print(i + ": ");
      for (int v : d.adj(i)) {
        System.out.print(v + " ");
      }
      System.out.println();
    }
    
    System.out.println("Directed Edge");
    DirectedEdge de = new DirectedEdge(12, 24, 5.67);
    System.out.println(de);
    
    System.out.println("Edge Weighted Directed Graph");
    EdgeWeightedDigraph ewd = new EdgeWeightedDigraph(testEWD);
    System.out.println(ewd.v() + " vertices, " + ewd.e() + " edges");
    
    for (int i = 0; i < ewd.v(); i++) {
      System.out.print(i + ": ");
      for (DirectedEdge x : ewd.adj(i)) {
        System.out.print(x + " ");
      }
      System.out.println();
    }
  }
}