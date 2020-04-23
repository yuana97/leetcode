package basic_data_structures;

/*

Max Sum Pair (Microsoft)

Question:
Given an int[] A of size 1 <= N <= 2*10^5 where each element Ai of A has 1 <= Ai <= 10^9,
write a function which returns the maximum sum of two numbers in A where they have equal digit sum.
If no two numbers have equal digit sum, return -1.

Solution:

*/

import java.util.*;

public class MaxSumPair {
  // return the digit sum of a number a
  private int computeDigitSum(int a) {
    a = Math.abs(a);
    int res = 0;
    // while a is not 0, add its last digit to res and remove its last digit
    while (a > 0) {
      res += a % 10;
      a /= 10;
    }
    return res;
  }

  public int maxSum(int[] A) {
    int N = A.length;
    if (N <= 1)
      return -1;
    // map digit sum to the greatest Ai with that digit sum
    Map<Integer, Integer> map = new HashMap<>();
    int res = -1;
    // for each number update the map and result
    for (int i = 0; i < N; ++i) {
      // put the digitsum in the map if its new
      int digitsum = computeDigitSum(A[i]);
      if (!map.containsKey(digitsum)) {
        map.put(digitsum, A[i]);
      } else {
        // otherwise update the map and result
        res = Math.max(res, map.get(digitsum) + A[i]);
        map.put(digitsum, Math.max(A[i], map.get(digitsum)));
      }
    }
    return res;
  }
  
  public static void main(String[] args) {
    MaxSumPair maxSumPair = new MaxSumPair();
    // expected output: 133, 93, 102, -1
    int[][] testcases = { { 51, 71, 17, 42, 33, 44, 24, 62 }, { 51, 71, 17, 42 }, { 42, 33, 60 }, { 51, 32, 43 } };
    for (int[] testcase : testcases)
      System.out.println(maxSumPair.maxSum(testcase));
  }
}