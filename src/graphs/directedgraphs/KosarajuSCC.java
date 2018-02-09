package graphs.directedgraphs;

import datastructures.graph.Digraph;

public class KosarajuSCC {
  private boolean[] marked;
  private int[] id;
  private int count;
  
  public KosarajuSCC(Digraph g) {
    marked = new boolean[g.v()];
    id = new int[g.v()];
    DepthFirstOrder dfs = new DepthFirstOrder(g.reverse());
    for (int v : dfs.reversePost()) {
      if (!marked[v]) {
        dfs(g, v);
        count++;
      }
    }
  }
  
  public boolean stronglyConnected(int v, int w) {
    return id[v] == id[w];
  }
  
  public int id(int v) {
    return id[v];
  }
  
  public int count() {
    return count;
  }
  
  private void dfs(Digraph g, int v) {
    marked[v] = true;
    id[v] = count;
    for (int w : g.adj(v)) {
      if (!marked[w]) {
        dfs(g, w);
      }
    }
  }
}