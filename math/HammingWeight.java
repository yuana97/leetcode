package math;

/*
Question: Find the number of 1 bits in an integer
*/

public class HammingWeight {

  // most efficient: Kernighan's algorithm
  public int hammingWeight(int n) {
    int count = 0;

    while (n != 0) {
      // n -> n-1 flips ...10...0 to ...01...1
      // this is geometric series formula with x=2.
      // hence n -> n & (n-1) turns ...10...0 to ...00...0
      // e.g. turns off the rightmost bit
      n = n & (n - 1);
      count++;
    }

    return count;
  }
}