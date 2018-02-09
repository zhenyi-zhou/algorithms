package graphs.undirectedgraphs;

import datastructures.linkedlist.Stack;
import datastructures.graph.Graph;

public class DepthFirstPaths {
  private boolean[] marked; // has dfs() been called for this vertex?
  private int[] edgeTo;     // last vertex on known path to this vertex
  private final int s;      // source
  
  public DepthFirstPaths(Graph g, int s) {
    marked = new boolean[g.v()];
    edgeTo = new int[g.v()];
    this.s = s;
    dfs(g, s);
  }
  
  public boolean hasPathTo(int v) {
    return marked[v];
  }
  
  public Iterable<Integer> pathTo(int v) {
    if (!hasPathTo(v)) {
      return null;
    }
    Stack<Integer> path = new Stack<Integer>();
    for (int x = v; x != s; x = edgeTo[x]) {
      path.push(x);
    }
    path.push(s);
    return path;
  }
  
  private void dfs(Graph g, int v) {
    marked[v] = true;
    for (int w : g.adj(v)) {
      if (!marked[w]) {
        edgeTo[w] = v;
        dfs(g, w);
      }
    }
  }
}