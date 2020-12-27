package memoization;

/*
SubarraySumTarget (2020 Facebook Telephone Screen)

Question:
Given an int[] A and an int k, find the number of subarrays of A which sum up to k.

Script:
Q: To clarify, the input array is an array of integers, is that correct?
Q: Are we guaranteed that this array is not null?
S: If it is null, let's handle the error by throwing an exception.

S: Now, let me give a quick overview of the problem.
S: Let's note that the sum of a subarray is the difference of two partial sums of the array.
S: That is, to get the sum of a given subarray, we subtract the cumulative sum up to the end
of the array from the cumulative sum up to the beginning of the array.
S: (because Ai + Ai+1 + ... + Aj = (A1 + ... + Aj) - (A1 + ... + Ai-1))
S: Now we can formulate the problem as finding the number of pairs where
Sj - Si = k, and j > i.
S: To efficiently solve the problem, we can memoize the mapping of partial sum to
frequency as we go through the array. At each point j of the array, we can check for
how many (Sj - k)'s there are in the array. These form a match with j, so we can
add the frequency of that number to our total count.
S: This should give an O(N) solution to the problem.
S: If you agree with my analysis, I can start coding it up.

*/

import java.util.*;

public class SubarraySumTarget {
  public int subarraySum(int[] nums, int k) throws IllegalArgumentException {
    if (nums == null) {
      throw new IllegalArgumentException("Array must not be null");
    }
    int sum = 0, result = 0;
    // map partial sum -> frequency
    Map<Integer, Integer> preSum = new HashMap<>();
    preSum.put(0, 1);
    // for each partial sum look up how many preceding partial sums 'complete' it
    for (int i = 0; i < nums.length; i++) {
      sum += nums[i];
      if (preSum.containsKey(sum - k)) {
        result += preSum.get(sum - k);
      }
      preSum.put(sum, preSum.getOrDefault(sum, 0) + 1);
    }

    return result;
  }

  // unit test
  public static void main(String[] args) {
    SubarraySumTarget subarraySumTarget = new SubarraySumTarget();
    int[] test1 = {1, 1, 1};
    int ans = subarraySumTarget.subarraySum(test1, 2);
    // expected value: 2
    System.out.println(ans);
  }
}