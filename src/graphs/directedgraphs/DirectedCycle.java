package graphs.directedgraphs;

import datastructures.linkedlist.Stack;
import datastructures.graph.Digraph;

public class DirectedCycle {
  private boolean[] marked;     // has dfs() been called for this vertex?
  private int[] edgeTo;         // last vertex on known path to this vertex
  private Stack<Integer> cycle; // vertices on a cycle (if one exists)
  private boolean[] onStack;    // vertices on recursive call stack
  
  public DirectedCycle(Digraph g) {
    onStack = new boolean[g.v()];
    edgeTo = new int[g.v()];
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
  
  public Iterable<Integer> cycle() {
    return cycle;
  }
  
  private void dfs(Digraph g, int v) {
    onStack[v] = true;
    marked[v] = true;
    for (int w : g.adj(v)) {
      if (this.hasCycle()) {
        return;
      } else if (!marked[w]) {
        edgeTo[w] = v;
        dfs(g, w);
      } else if (onStack[w]) {
        cycle = new Stack<Integer>();
        for (int i = v; i != w; i = edgeTo[i]) {
          cycle.push(i);
        }
        cycle.push(w);
        cycle.push(v);
      }
    }
    onStack[v] = false;
  }
}