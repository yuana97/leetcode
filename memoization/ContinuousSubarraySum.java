package memoization;

import java.util.*;

/*

Question (Microsoft):
Given an int[] nums and an int k check if nums has a subarray of length >= 2 which sums to a multiple
of k.

Solution:
Memoize partial sums sums mod k. If we find a repeat, we're done. (Since we take the subarray [i+1,...,j]
where i and j are the first and second occurences. Reason this works: Let Sn = nth partial sum, let S = Ai+1 + ... + Aj,
and let r = Sj mod k = Si mod k. Then S mod k = Sj - Si mod k = r - r mod k = 0 mod k => S is a multiple of k)

*/
public class ContinuousSubarraySum {
  public boolean checkSubarraySum(int[] nums, int k) {
    // map partial sum mod k to the index where it appears
    Map<Integer, Integer> map = new HashMap<Integer, Integer>(){{put(0,-1);}};;
    // partial sum mod k
    int runningSum = 0;
    for (int i = 0; i < nums.length; i++) {
      // get the partial sum at i, mod k
      runningSum += nums[i];
      if (k != 0) runningSum %= k;
      // repeat residue mod k => done
      Integer prev = map.get(runningSum);
      if (prev != null) {
        if (i - prev > 1) return true;
      }
      // else memoize the partial sum
      else map.put(runningSum, i);
    }
    return false;
  }
}