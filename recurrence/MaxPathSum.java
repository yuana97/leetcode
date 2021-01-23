package graph;

/**
 * Max Path Sum (Facebook E5)
 * 
 * Question:
 * Given a nonempty binary tree find the maximum sum of a path in the binary tree,
 * where a path is defined as a sequence of unique nodes n1, n2, n3, ... where ni+1 is
 * either the child or parent of ni.
 * 
 * Solution:
 * Observe that any such path first goes up the binary tree to a root node and then goes down.
 * A max path rooted at a given node can be considered the sum of the max left descending path
 * and the max right descending path.
 * 
 * There's a simple recurrence for the max descending path rooted at the node n (call this f(n)).
 * f(n) = n.val + max(f(n.left), f(n.right)). So to get max path of n, just get f(n.left) + f(n.right) + n.val.
 * 
 * Script:
 * - Are we guaranteed the input is not null?
 * - In that case, let's handle it by throwing an exception.
 * 
 * - Let me give a quick overview of the problem.
 * - Observe that any such path first goes up the binary tree to a root node and then goes down.
 * - A max path rooted at a given node can be considered the sum of the max left descending path
 * and the max right descending path.
 * - We have a simple recurrence for the max sum of a descending path at a node n. If we call this recurrence
 * 'f', we have f(n) = n.val + max(f(n.left), f(n.right)).
 * - To solve the original problem, we can DFS through the tree and calculate n.val + f(n.left) + f(n.right)
 * for each node n, and update the max accordingly.
 * This should give us an O(N) solution to the problem where N is the size of the tree.
 * If you agree with my analysis, I can start coding it up.
 */

public class MaxPathSum {

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

  public int maxPathSum(TreeNode root) {
    // this is how we pass an int by ref in java: wrap it in an array
    // max[0] is the max path sum so far
    int[] max = new int[1];
    max[0] = Integer.MIN_VALUE;
    // find max path sum in the tree
    maxPathDown(max, root);
    return max[0];
  }

  // update max[0] and return the max path 
  private int maxPathDown(int[] max, TreeNode node) {
    if (node == null) return 0;
    // get max descending path from left and right
    int left = Math.max(0, maxPathDown(max, node.left));
    int right = Math.max(0, maxPathDown(max, node.right));
    // get the max path rooted at node
    max[0] = Math.max(max[0], left + right + node.val);
    // return max descending path rooted at node
    return Math.max(left, right) + node.val;
  }
}