package psets.amzn;

import java.util.*;

public class winter_2021 {
  // Swap two numbers
  // Q: Swap two numbers w/o temp var, w/o arithmetic operators
  // S: Note a^b^b=a, so we can get b=a by a=a^b, b=a^b
  // After that we get a = a^b to complete the swap
  public void swap() {
    int a = 5, b = 10;
    a = a^b; // we do this step to store a^b
    b = a^b; // b=a^b^b=a
    a = a^b; // a=a^b^a=b
  }

  // Asteroid collision
  // Q: Given an array asteroids of integers. Absolute value = size of asteroid,
  // positive sign = going right, negative sign = going left. Each asteroid moves at the same speed.
  // If two asteroids meet, the smaller one explodes. If they are the same size, both will explode.
  // Find the state of the asteroids after all collisions. Return it as an array of the surviving asteroids
  // from left to right.
  // S: As you iterate thru the array from left to right, maintain a stack of surviving asteroids
  // where the top of the stack is the rightmost surviving asteroid. When we encounter a positive int
  // we can just add it to the stack (it won't collide w/ any preceding asteroids). When we encounter a negative int i
  // we should remove all the smaller positive asteroids on top of the stack. If we encounter a negative asteroid
  // or the stack is empty, we can push i onto the stack. If we encounter a bigger positive asteroid, we don't push i
  // onto the stack (representing i being destroyed). If we encounter an equal positive asteroid, we remove that
  // asteroid from the stack and do not push i onto the stack (representing both asteroids being destroyed)

  public int[] asteroidCollision(int[] asteroids) {
    // stack containing the surviving asteroids
    // the top of the stack is the current rightmost asteroid
    Stack<Integer> s = new Stack<Integer>();
    for (int i : asteroids) {
      if (i > 0) {
        // positive asteroids don't collide, this will be the rightmost asteroid
        s.push(i);
      } else { // if i is negative
        // smash all the smaller asteroids going right
        while (!s.isEmpty() && s.peek() > 0 && s.peek() < Math.abs(i)) {
          s.pop();
        }
        // if we've smashed all the asteroids or encounter another negative asteroid,
        // i will become the rightmost asteroid
        if (s.isEmpty() || s.peek() < 0) {
          s.push(i);
        } else if (i + s.peek() == 0) { // if equal size, smash both asteroids
          s.pop();
        } // unspoken third case: if i is smaller than the next asteroid, smash i
        // this is represented by not pushing i onto the stack
      }
    }
    // stack has the rightmost elements on top so fill the resulting array
    // in backwards order
    int[] res = new int[s.size()];
    for (int i = res.length - 1; i >= 0; i--) {
      res[i] = s.pop();
    }
    return res;
  }

  // Product of array except self
  // Q: Given an array nums of n integers where n > 1, return an array output such that
  // output[i] is equal to the product of all the elements of nums except nums[i]. Guaranteed
  // that the product over the array fits in a 32 bit integer. You're not allowed to use division
  // S: Memoize partial products from the left and right, for each output[i] return left[i-1]*right[i+1].
  // As an optimization we can memoize i-1-th left partial product in output[i], and just store the 
  // right partial product in a variable, and calculate output[i] as we go along from the right.

  public int[] productExceptSelf(int[] nums) {
    int n = nums.length;
    int[] res = new int[n];
    // memoize left partial products
    int left = 1;
    for (int i = 0; i < n; i++) {
      if (i > 0) {
        left *= nums[i-1];
      }
      res[i] = left;
    }
    // calculate right partial product and res[i]
    int right = 1;
    for (int i = n - 1; i >= 0; i--) {
      if (i < n - 1) {
        right *= nums[i + 1];
      }
      res[i] *= right;
    }
    return res;
  }

  // House Robber 2
  // Q: Given a binary tree of 'houses' where the value represents how much money is in the house.
  // If two directly linked houses are broken into, the police will be alerted.
  // Return the max money the robber can steal w/o alerting the police.
  // S: Recurrence for max money robbing current subtree, switch on robbing/not robbing current house.

  public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
  }

  public int rob(TreeNode root) {
    // need an additional map to memoize max money at a given node
    return robHelper(root, new HashMap<TreeNode, Integer>());
  }

  private int robHelper(TreeNode root, Map<TreeNode, Integer> map) {
    if (root == null) return 0;
    if (map.containsKey(root)) return map.get(root);

    // max money from the current subtree
    int val = 0;

    // try robbing the current house
    // add values from left.left and left.right
    if (root.left != null) {
      val += robHelper(root.left.left, map) + robHelper(root.left.right, map);
    }

    // ditto for right
    if (root.right != null) {
      val += robHelper(root.right.left, map) + robHelper(root.right.right, map);
    }

    // compare value from robbing here to skipping and robbing left and right.
    val = Math.max(val + root.val, robHelper(root.left, map) + robSub(root.right, map));
    map.put(root, val);

    return val;
  }
}
