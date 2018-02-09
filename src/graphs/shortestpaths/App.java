package graphs.shortestpaths;

import datastructures.graph.DirectedEdge;
import datastructures.graph.EdgeWeightedDigraph;

public class App {
  private static double[][] testEWD = {
    {8, 15},
    {4, 5, 0.35}, {5, 4, 0.35}, {4, 7, 0.37}, {5, 7, 0.28}, {7, 5, 0.28},
    {5, 1, 0.32}, {0, 4, 0.38}, {0, 2, 0.26}, {7, 3, 0.39}, {1, 3, 0.29},
    {2, 7, 0.34}, {6, 2, 0.40}, {3, 6, 0.52}, {6, 0, 0.58}, {6, 4, 0.93}
  };
  
  private static double[][] testEWDAG = {
    {8, 13},
    {5, 4, 0.35}, {4, 7, 0.37}, {5, 7, 0.28}, {5, 1, 0.32}, {4, 0, 0.38},
    {0, 2, 0.26}, {3, 7, 0.39}, {1, 3, 0.29}, {7, 2, 0.34}, {6, 2, 0.40},
    {3, 6, 0.52}, {6, 0, 0.58}, {6, 4, 0.93}
  };
  
  private static double[][] testEWDN = {
    {8, 15},
    {4, 5, 0.35}, {5, 4, 0.35}, {4, 7, 0.37}, {5, 7, 0.28}, {7, 5, 0.28},
    {5, 1, 0.32}, {0, 4, 0.38}, {0, 2, 0.26}, {7, 3, 0.39}, {1, 3, 0.29},
    {2, 7, 0.34}, {6, 2, -1.20}, {3, 6, 0.52}, {6, 0, -1.40}, {6, 4, -1.25}
  };
  
  public static void main(String[] args) {
    EdgeWeightedDigraph ewd = new EdgeWeightedDigraph(testEWD);
    EdgeWeightedDigraph ewdag = new EdgeWeightedDigraph(testEWDAG);
    EdgeWeightedDigraph ewdn = new EdgeWeightedDigraph(testEWDN);
    
    System.out.println("Dijkstra's Shortest Paths");
    int s = 0;
    DijkstraSP dsp = new DijkstraSP(ewd, s);
    for (int t = 0; t < ewd.v(); t++) {
      System.out.print(s + " to " + t);
      System.out.printf(" (%4.2f): ", dsp.distTo(t));
      if (dsp.hasPathTo(t)) {
        for (DirectedEdge e : dsp.pathTo(t)) {
          System.out.print(e + " ");
        }
      }
      System.out.println();
    }
    
    System.out.println("Acyclic Shortest Paths");
    s = 5;
    AcyclicSP asp = new AcyclicSP(ewdag, s);
    for (int t = 0; t < ewdag.v(); t++) {
      System.out.print(s + " to " + t);
      System.out.printf(" (%4.2f): ", asp.distTo(t));
      if (asp.hasPathTo(t)) {
        for (DirectedEdge e : asp.pathTo(t)) {
          System.out.print(e + " ");
        }
      }
      System.out.println();
    }
    
    System.out.println("Bellman-Ford Shortest Paths");
    s = 0;
    BellmanFordSP bfsp = new BellmanFordSP(ewdn, s);
    for (int t = 0; t < ewdn.v(); t++) {
      System.out.print(s + " to " + t);
      System.out.printf(" (%4.2f): ", bfsp.distTo(t));
      if (bfsp.hasPathTo(t)) {
        for (DirectedEdge e : bfsp.pathTo(t)) {
          System.out.print(e + " ");
        }
      }
      System.out.println();
    }
  }
}