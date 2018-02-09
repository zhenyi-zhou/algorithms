package strings.regularexpressions;

import datastructures.linkedlist.Bag;
import datastructures.linkedlist.Stack;
import datastructures.graph.Digraph;
import graphs.directedgraphs.DirectedDFS;

public class NFA {
  private char[] re; // match transitions
  private Digraph g; // epsilon transitions
  private int m;     // number of states
  
  public NFA(String regexp) {
    // Create the NFA for the given regular expression.
    Stack<Integer> ops = new Stack<Integer>();
    re = regexp.toCharArray();
    m = re.length;
    g = new Digraph(m + 1);
    for (int i = 0; i < m; i++) {
      int lp = i;
      if (re[i] == '(' || re[i] == '|') {
        ops.push(i);
      } else if (re[i] == ')') {
        int or = ops.pop();
        if (re[or] == '|') {
          lp = ops.pop();
          g.addEdge(lp, or + 1);
          g.addEdge(or, i);
        } else {
          lp = or;
        }
      }
      if (i < m - 1 && re[i + 1] == '*') { // lookahead
        g.addEdge(lp, i + 1);
        g.addEdge(i + 1, lp);
      }
      if (re[i] == '(' || re[i] == '*' || re[i] == ')') {
        g.addEdge(i, i + 1);
      }
    }
  }
  
  public boolean recognizes(String txt) {
    // Does the NFA recognize txt?
    Bag<Integer> pc = new Bag<Integer>();
    DirectedDFS dfs = new DirectedDFS(g, 0);
    for (int v = 0; v < g.v(); v++) {
      if (dfs.marked(v)) {
        pc.add(v);
      }
    }
    for (int i = 0; i < txt.length(); i++) {
      // Compute possible NFA states for txt[i + 1].
      Bag<Integer> match = new Bag<Integer>();
      for (int v : pc) {
        if (v < m) {
          if (re[v] == txt.charAt(i) || re[v] == '.') {
            match.add(v + 1);
          }
        }
      }
      pc = new Bag<Integer>();
      dfs = new DirectedDFS(g, match);
      for (int v = 0; v < g.v(); v++) {
        if (dfs.marked(v)) {
          pc.add(v);
        }
      }
    }
    for (int v : pc) {
      if (v == m) {
        return true;
      }
    }
    return false;
  }
}