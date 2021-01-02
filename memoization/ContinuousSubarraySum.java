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
Q: Are we guaranteed that the int k is not zero?
S: If it is zero, let's handle the error by throwing an exception.

S: Now, let me give a quick overview of the problem.
S: Let's note, that we can obtain, a subarray sum, by subtracting
two partial sums of the array.
S: Let's also note that the problem is equivalent to asking if a subarray exists with
sum = 0 mod k.
S: And finally, let's notice that if two numbers are equal mod k, then their difference
equals 0 mod k.
S: Let's start by using a hash table to record partial sums mod k
and the earliest index they occur at.
S: As we go through the array, let's calculate partial sums mod k.
If we find a repeat, we should return true, and if the iteration completes,
we should return false.
S: This should give an O(N) solution to the problem.
S: If you agree with my analysis, I can start coding it up.

*/

public class ContinuousSubarraySum {
  public boolean checkSubarraySum(int[] nums, int k) throws IllegalArgumentException {
    if (nums == null) {
      throw new IllegalArgumentException("Array must not be null");
    }
    if (k == 0) {
      throw new IllegalArgumentException("Integer k must be nonzero");
    }
    // initialize result
    boolean flag = false;
    // map partial sum mod k to the index where it appears
    Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    map.put(0, -1);
    // cumulative sum mod k
    int partialSum = 0;
    for (int i = 0; i < nums.length; i++) {
      // get the partial sum at i, mod k
      partialSum = (partialSum + nums[i]) % k;
      // if we find a previous occurrence of the partial sum,
      // check if we're done
      Integer prev = map.get(partialSum);
      if (prev != null) {
        if (i - prev > 1) {
          flag = true;
          break;
        }
      } else {
        map.put(partialSum, i);
      }
    }
    return flag;
  }
}