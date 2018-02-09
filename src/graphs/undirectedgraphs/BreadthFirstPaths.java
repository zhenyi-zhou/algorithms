package graphs.undirectedgraphs;

import datastructures.linkedlist.Queue;
import datastructures.linkedlist.Stack;
import datastructures.graph.Graph;

public class BreadthFirstPaths {
  private boolean[] marked; // has dfs() been called for this vertex?
  private int[] edgeTo;     // last vertex on known path to this vertex
  private final int s;      // source
  
  public BreadthFirstPaths(Graph g, int s) {
    marked = new boolean[g.v()];
    edgeTo = new int[g.v()];
    this.s = s;
    bfs(g, s);
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
  
  private void bfs(Graph g, int s) {
    Queue<Integer> queue = new Queue<Integer>();
    marked[s] = true;
    queue.enqueue(s);
    while (!queue.isEmpty()) {
      int v = queue.dequeue();
      for (int w : g.adj(v)) {
        if (!marked[w]) {
          edgeTo[w] = v;
          marked[w] = true;
          queue.enqueue(w);
        }
      } 
    }
  }
}