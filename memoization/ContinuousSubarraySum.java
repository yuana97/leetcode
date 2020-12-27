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

Script:
Q: To clarify, the input array is an array of integers, is that correct?
Q: Are we guaranteed that this array is not null?
S: If it is null, let's handle the error by throwing an exception.

S: Now, let me give a quick overview of the problem.
S: Let's note that the problem is equivalent to asking if a subarray exists with
sum = 0 mod k.
S: Let's also note that the sum of a subarray is equal to the difference of two partial sums
of the array.
S: And finally, let's notice that if two numbers are equal mod k, then their difference
equals 0 mod k.
S: This suggests to us that we should memoize partial sums mod k. If we encounter
a repeated number in our cache, we can return true, because we can subtract the two
partial sums and get a subarray that works.
S: Because of the requirement that the array length is at least 2, we'll map the partial sums
mod k to the index where they appear (so we can check the array length)
S: This should give an O(N) solution to the problem.
S: If you agree with my analysis, I can start coding it up.

*/

public class ContinuousSubarraySum {
  public boolean checkSubarraySum(int[] nums, int k) throws IllegalArgumentException {
    if (nums == null) {
      throw new IllegalArgumentException("Array must not be null");
    }
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