package basic_data_structures;

import java.util.*;

/*

Question:
Given an integer array of positive numbers, write a function which return true if we can remove two elements
of the array to form three subarrays with equal sum, and false otherwise.

Script:

Q: Can we assume the input to be an array of integers?
Q: Are we guaranteed that the input is not null?
S: In that case, let's handle the error by throwing an exception.
Q: Now, let's notice that it doesn't make sense if the request size is negative,
so are we guaranteed that the array is only positive numbers?
S: In that case, let's also throw an exception.

S: Let me give a quick overview of the problem.
S: Let's start with two pointers at the ends of the array representing the cut points.
S: Let's also maintain a 'prefix sum' adding up all the elements from the beginning
to the left pointer, and a 'suffix sum' adding up all the elements from the end to the right pointer.
S: Let's also notice that if we know the total sum of the array, we can infer
the middle sum by subtracting the prefix and suffix sums from the total sum.
S: So let's start by using two pointers at the ends of the array representing the 
cut points we will try. If the prefix sum is too small, we can move the left cut point
to the right, and make the prefix sum bigger. In the same way, if the suffix sum is too small
we can move the right cut point to the left, and make the suffix sum bigger. In this way,
we can quickly 'balance' the sums.
S: If we find a point at which all the sums are equal, we can return true. If the iteration completes
without finding an answer, we return false.
S: This should give us an O(N) solution to the problem. If you agree with my analysis,
I can begin coding it up.

*/

public class LoadBalancer {
  public boolean loadBalance(int[] arr) throws IllegalArgumentException {
    // null input
    if (arr == null) {
      throw new IllegalArgumentException("Array must not be null");
    }

    // too small
    if (arr.length < 5) {
      throw new IllegalArgumentException("Array is not sufficiently large");
    }

    boolean flag = false;

    // calculate the full sum
    int sum = 0;
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] < 0) {
        throw new IllegalArgumentException("Request sizes must be nonnegative");
      }
      sum += arr[i];
    }

    // initialize two pointers (cut points), and the prefix/suffix sums
    int lo = 0, hi = arr.length - 1;
    int prefixSum = 0, suffixSum = 0;

    // balance the prefix, suffix, and middle sums
    while (lo < hi - 1) { // while the cut points are not at the center together
      // calculate the middle sum
      int midSum = sum - prefixSum - suffixSum - arr[lo] - arr[hi];
      // success: return
      if (prefixSum == midSum && suffixSum == midSum) {
        flag = true;
        break;
      } else {
        if (prefixSum < suffixSum) {
          prefixSum += arr[lo];
          lo++;
        } else {
          suffixSum += arr[hi];
          hi--;
        }
      }
    }

    return flag;
  }
}
