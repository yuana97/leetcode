package basic_data_structures;

/*
SubarraySumTarget (2020 Facebook Telephone Screen)

Question:
Given an int[] A and an int k, find the number of subarrays of A which sum up to k.

Solution:
Partial sums. Let Si = partial sum of A at index i. The idea is the sum of a subarray
[Ai,...,Aj] equals Sj - Si-1.

So to solve this question we just map partial sum -> frequency it occurs and at each step
check if current partial sum - k exists in the map.

*/

import java.util.*;

public class SubarraySumTarget {
  public int subarraySum(int[] nums, int k) {
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