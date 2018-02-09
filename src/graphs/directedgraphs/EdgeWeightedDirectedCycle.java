package graphs.directedgraphs;

import datastructures.linkedlist.Stack;
import datastructures.graph.DirectedEdge;
import datastructures.graph.EdgeWeightedDigraph;

public class EdgeWeightedDirectedCycle {
  private boolean[] marked;           // has dfs() been called for this vertex?
  private DirectedEdge[] edgeTo;      // last vertex on known path to this vertex
  private Stack<DirectedEdge> cycle;  // vertices on a cycle (if one exists)
  private boolean[] onStack;          // vertices on recursive call stack
  
  public EdgeWeightedDirectedCycle(EdgeWeightedDigraph g) {
    onStack = new boolean[g.v()];
    edgeTo = new DirectedEdge[g.v()];
    marked = new boolean[g.v()];
    for (int v = 0; v < g.v(); v++) {
      if (!marked[v]) {
        dfs(g, v);
      }
    }
  }
  
  public boolean hasCycle() {
    return cycle != null;
  }
  
  public Iterable<DirectedEdge> cycle() {
    return cycle;
  }
  
  private void dfs(EdgeWeightedDigraph g, int v) {
    onStack[v] = true;
    marked[v] = true;
    for (DirectedEdge e : g.adj(v)) {
      int w = e.to();
      if (this.hasCycle()) {
        return;
      } else if (!marked[w]) {
        edgeTo[w] = e;
        dfs(g, w);
      } else if (onStack[w]) {
        cycle = new Stack<DirectedEdge>();
        
        DirectedEdge f = e;
        while(f.from() != w) {
          cycle.push(f);
          f = edgeTo[f.from()];
        }
        cycle.push(f);
        
        return;
      }
    }
    onStack[v] = false;
  }
}