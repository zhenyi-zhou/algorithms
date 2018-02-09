package graphs.undirectedgraphs;

import datastructures.graph.Graph;

public class CC {
  private boolean[] marked;
  private int[] id;
  private int count;
  
  public CC(Graph g) {
    marked = new boolean[g.v()];
    id = new int[g.v()];
    for (int v = 0; v < g.v(); v++) {
      if (!marked[v]) {
        dfs(g, v);
        count++;
      }
    }
  }
  
  public boolean connected(int v, int w) {
    return id[v] == id[w];
  }
  
  public int id(int v) {
    return id[v];
  }
  
  public int count() {
    return count;
  }
  
  private void dfs(Graph g, int v) {
    marked[v] = true;
    id[v] = count;
    for (int w : g.adj(v)) {
      if (!marked[w]) {
        dfs(g, w);
      }
    }
  }
}