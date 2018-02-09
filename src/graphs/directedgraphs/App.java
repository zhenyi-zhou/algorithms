package graphs.directedgraphs;

import datastructures.linkedlist.Bag;
import datastructures.linkedlist.Queue;
import datastructures.graph.Digraph;
import datastructures.graph.DirectedEdge;
import datastructures.graph.EdgeWeightedDigraph;

public class App {
  private static int[][] testDG = {
    {13, 22}, // vertices and edges
    {4, 2}, {2, 3}, {3, 2}, {6, 0}, {0, 1}, {2, 0}, {11, 12}, // adjacency lists
    {12, 9}, {9, 10}, {9, 11}, {8, 9}, {10, 12}, {11, 4}, {4, 3},
    {3, 5}, {7, 8}, {8, 7}, {5, 4}, {0, 5}, {6, 4}, {6, 9}, {7, 6}
  };
  
  private static int[][] testDAG = {
    {13, 15}, // vertices and edges
    {2, 3}, {0, 6}, {0, 1}, {2, 0}, {11, 12}, {9, 12}, {9, 10}, // adjacency lists
    {9, 11}, {3, 5}, {8, 7}, {5, 4}, {0, 5}, {6, 4}, {6, 9}, {7, 6}
  };
  
  private static double[][] testEWD = {
    {8, 15},
    {4, 5, 0.35}, {5, 4, 0.35}, {4, 7, 0.37}, {5, 7, 0.28}, {7, 5, 0.28},
    {5, 1, 0.32}, {0, 4, 0.38}, {0, 2, 0.26}, {7, 3, 0.39}, {1, 3, 0.29},
    {2, 7, 0.34}, {6, 2, 0.40}, {3, 6, 0.52}, {6, 0, 0.58}, {6, 4, 0.93}
  };
  
  public static void main(String[] args) {
    Digraph g = new Digraph(testDG);
    Digraph dag = new Digraph(testDAG);
    EdgeWeightedDigraph ewd = new EdgeWeightedDigraph(testEWD);
    
    System.out.println("Directed Depth First Search");
    Bag<Integer> sources = new Bag<Integer>();
    sources.add(1);
    sources.add(2);
    sources.add(6);
    
    DirectedDFS dfs = new DirectedDFS(g, sources);
    for (int i = 0; i < g.v(); i++) {
      if (dfs.marked(i)) {
        System.out.print(i + " ");
      }
    }
    System.out.println();
    
    System.out.println("Directed Cycle");
    DirectedCycle finder = new DirectedCycle(g);
    if (finder.hasCycle()) { 
      for (int v : finder.cycle()) {
        System.out.print(v + " ");
      }
    }
    System.out.println();
    
    System.out.println("Directed Depth First Order");
    DepthFirstOrder order = new DepthFirstOrder(dag);

    System.out.print("Preorder:  ");
    for (int v : order.pre()) {
        System.out.print(v + " ");
    }
    System.out.println();

    System.out.print("Postorder: ");
    for (int v : order.post()) {
        System.out.print(v + " ");
    }
    System.out.println();

    System.out.print("Reverse postorder: ");
    for (int v : order.reversePost()) {
        System.out.print(v + " ");
    }
    System.out.println();
    
    System.out.println("Topological Sort");
    Topological top = new Topological(dag);
    for (int v : top.order()) {
      System.out.print(v + " ");
    }
    System.out.println();
    
    System.out.println("Strong Connected Components");
    KosarajuSCC scc = new KosarajuSCC(g);
    
    int m = scc.count();
    System.out.println(m + " compontents");
    
    Queue<Integer>[] components = (Queue<Integer>[]) new Queue[m];
    
    for (int i = 0; i < m; i++) {
      components[i] = new Queue<Integer>();
    }
    for (int v = 0; v < g.v(); v++) {
      components[scc.id(v)].enqueue(v);
    }
    for (int i = 0; i < m; i++) {
      for (int v : components[i]) {
        System.out.print(v + " ");
      }
      System.out.println();
    }
    
    System.out.println("Edge Weighted Directed Cycle");
    EdgeWeightedDirectedCycle ewFinder = new EdgeWeightedDirectedCycle(ewd);
    if (ewFinder.hasCycle()) { 
      for (DirectedEdge e : ewFinder.cycle()) {
        System.out.print(e + " ");
      }
    }
    System.out.println();
  }
}