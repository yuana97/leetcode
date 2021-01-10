package graph;

/*
Clone Graph (Facebook Phone Screen)

Question:
Given a node of a connected graph, return a clone of the graph.

Solution:
Just use your favorite graph traversal method with a map to prevent overcounting.
This solution uses a BFS.

*/

import java.util.*;

public class CloneGraph {
  // definition for a Node
  class Node {
    public int val;
    public List<Node> neighbors;
    
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
  }

  public Node cloneGraph(Node node) {
    if (node == null) return null;

    Node newNode = new Node(node.val, new ArrayList<>());
    // map old to new nodes
    Map<Node, Node> map = new HashMap();
    map.put(node, newNode);
    // store next level of BFS
    Queue<Node> queue = new LinkedList();
    queue.add(node);
    // bfs
    while(!queue.isEmpty()) {
      // clone each neighbor of the next node and add them to the BFS
      Node oldNode = queue.poll();
      for (Node oldNeighbor : oldNode.neighbors) {
        if (!map.containsKey(oldNeighbor)) {
          Node newNeighbor = new Node(oldNeighbor.val, new ArrayList<>());
          map.put(oldNeighbor, newNeighbor);
          queue.add(oldNeighbor);
        }
        // draw the edges between new neighbors and new nodes
        map.get(oldNode).neighbors.add(map.get(oldNeighbor));
      }
    }
    return newNode;
  }

  public static void main(String[] args) {
    // insert unit test here
  }
}