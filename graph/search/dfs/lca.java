package graph.search.dfs;

import java.util.*;

/*
Q: Given a binary tree and two nodes of the tree, return the node which is the least
common ancestor of the two.

S: DFS for p and q, while recording a map of child->parent. Once we've found both p and q,
form a set of all the ancestors of p. Then move q up the tree until it coincides with
an ancestor of p.

Script:
- Are we guaranteed that the inputs are not null?
- Let's handle the null case by throwing an exception.
- Are we guaranteed that p and q are in the tree?
- Yes


- Let me give a quick overview of the problem.
- We want to search the tree for p and q. Having found them, we want to
travel back up the parents until we find the first intersection.
- In order to travel back up the tree, we will maintain a child->parent mapping
as we do the initial search.
- Having found both nodes, we can travel up the parents to create a set of 'ancestors'
of one of the nodes.
- Then we can travel up the other node until we find an intersection with the 'ancestors' set.
- This intersection will be the LCA, which completes the solution.
- This solution will run in O(N) time, where N is the size of the tree.
- If you agree with my analysis, I can start coding it up.
*/

public class lca {
    // node class
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
      }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) throws IllegalArgumentException {
      if (root == null || p == null || q == null) {
        throw new IllegalArgumentException("TreeNode must not be null");
      }
      // a map of child to parent so we can traverse back up the tree
      Map<TreeNode, TreeNode> parent = new HashMap<TreeNode, TreeNode>();

      // Stack for DFS (contains the nodes we need to search, from latest to earliest)
      Stack<TreeNode> stack = new Stack<TreeNode>();
      // put a parent mapping for the root
      parent.put(root, null);
      // start the search path w/ root
      stack.push(root);

      // While we have not yet found both p and q, search for p and q
      // and populate the parent map
      while (!parent.containsKey(p) || !parent.containsKey(q)) {
        TreeNode node = stack.pop();

        // populate the parent map and search left
        if (node.left != null) {
          parent.put(node.left, node);
          stack.push(node.left);
        }

        // populate the parent map and search right
        if (node.right != null) {
          parent.put(node.right, node);
          stack.push(node.right);
        }
      }

      // set of ancestors of p. We use a set for fast lookup of duplicates
      // when we go through the ancestors of q.
      Set<TreeNode> ancestors = new HashSet<TreeNode>();
      while (p != null) {
        ancestors.add(p);
        p = parent.get(p);
      }

      // move q up the tree until we find a common ancestor with p.
      while (!ancestors.contains(q)) {
        q = parent.get(q);
      }

      // once the above while loop has ended, q is an ancestor of both p and q.
      // Hence return.
      return q;
    }
}
