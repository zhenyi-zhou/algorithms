package graphs.directedgraphs;

import datastructures.graph.Digraph;

public class DirectedDFS {
  private boolean[] marked; // has dfs() been called for this vertex?
  
  public DirectedDFS(Digraph g, int s) {
    marked = new boolean[g.v()];
    dfs(g, s);
  }
  
  public DirectedDFS(Digraph g, Iterable<Integer> sources) {
    marked = new boolean[g.v()];
    for (int s : sources) {
      if (!marked[s]) {
        dfs(g, s);
      }
    }
  }
  
  public boolean marked(int v) {
    return marked[v];
  }
  
  private void dfs(Digraph g, int v) {
    marked[v] = true;
    for (int w : g.adj(v)) {
      if (!marked[w]) {
        dfs(g, w);
      }
    }
  }
}