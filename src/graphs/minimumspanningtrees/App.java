package graphs.minimumspanningtrees;

import datastructures.graph.Edge;
import datastructures.graph.EdgeWeightedGraph;

public class App {
  private static double[][] testData = {
    {8, 16},
    {4, 5, 0.35}, {4, 7, 0.37}, {5, 7, 0.28}, {0, 7, 0.16},
    {1, 5, 0.32}, {0, 4, 0.38}, {2, 3, 0.17}, {1, 7, 0.19},
    {0, 2, 0.26}, {1, 2, 0.36}, {1, 3, 0.29}, {2, 7, 0.34},
    {6, 2, 0.40}, {3, 6, 0.52}, {6, 0, 0.58}, {6, 4, 0.93}
  };
  
  public static void main(String[] args) {
    EdgeWeightedGraph g = new EdgeWeightedGraph(testData);
    
    System.out.println("Prim's Minimum Spanning Trees");
    PrimMST pmst = new PrimMST(g);
    for (Edge e : pmst.edges()) {
      System.out.println(e);
    }
    System.out.printf("%.5f\n", pmst.weight());
    
    System.out.println("Kruskal's Minimum Spanning Trees");
    KruskalMST kmst = new KruskalMST(g);
    for (Edge e : kmst.edges()) {
      System.out.println(e);
    }
    System.out.printf("%.5f\n", kmst.weight());
  }
}