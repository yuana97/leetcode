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
S: Let's note, that we can obtain, a subarray sum, by subtracting
two partial sums of the array.
S: Let's start by using a hash table to record how often a given
partial sum occurs.
S: As we go through the array, let's keep track of the current partial
sum S. We want to find how many partial sums 'x' there are, such that 
S - x = k. To find that, we can simply look up S - k in the hashmap,
and add that to our answer. Then we add 1 to the frequency of S in the map, and continue.
S: This should give an O(N) solution to the problem.
S: If you agree with my analysis, I can start coding it up.

*/

import java.util.*;

public class SubarraySumTarget {
  public int subarraySum(int[] nums, int k) throws IllegalArgumentException {
    if (nums == null) {
      throw new IllegalArgumentException("Array must not be null");
    }
    // initialize our result
    int count = 0;
    // map partial sum -> frequency
    Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    // represents the 'empty sum' at the beginning of the array.
    map.put(0, 1);
    // initialize our partial sum
    int sum = 0;
    // for each partial sum look up how many preceding partial sums 'complete' it
    for (int i = 0; i < nums.length; i++) {
      sum += nums[i];
      // if we find a match, add its frequency to our answer
      if (map.containsKey(sum - k)) {
        count += map.get(sum - k);
      }
      map.put(sum, map.getOrDefault(sum, 0) + 1);
    }

    return count;
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