package graphs.directedgraphs;

import datastructures.graph.Digraph;
import datastructures.graph.EdgeWeightedDigraph;

public class Topological {
  private Iterable<Integer> order; // topological order
  
  public Topological(Digraph g) {
    DirectedCycle cyclefinder = new DirectedCycle(g);
    if (!cyclefinder.hasCycle()) {
      DepthFirstOrder dfs = new DepthFirstOrder(g);
      order = dfs.reversePost();
    }
  }
  
  public Topological(EdgeWeightedDigraph g) {
    EdgeWeightedDirectedCycle cyclefinder = new EdgeWeightedDirectedCycle(g);
    if (!cyclefinder.hasCycle()) {
      DepthFirstOrder dfs = new DepthFirstOrder(g);
      order = dfs.reversePost();
    }
  }
  
  public Iterable<Integer> order() {
    return order;
  }
  
  public boolean isDAG() {
    return order == null;
  }
}