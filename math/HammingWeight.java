package math;

/*
Question: Find the number of 1 bits in an integer.
Solution: Turn off rightmost bit (n=n&(n-1)) until n is 0.
*/

public class HammingWeight {

  // Kernighan's algorithm
  public int hammingWeight(int n) {
    int count = 0;
    while (n != 0) {
      // n -> n-1 flips ...10...0 to ...01...1
      // this is geometric partial sum formula with x=2
      // x^0+x^1+...+x^(n-1)=(x^n-1)/(x-1)
      // hence n -> n & (n-1) turns ...10...0 to ...00...0
      // e.g. turns off the rightmost bit
      n = n & (n - 1);
      count++;
    }
    return count;
  }
}

/*
Claim: x^0+x^1+...+x^(n-1)=(x^n-1)/(x-1)
Proof:
- Let Sn = 1+x^1+...+x^(n-1).
- (x-1)Sn = xSn - Sn = (x^1+x^2+...+x^n)-(x^0+x^1+...+x^(n-1)) = x^n-x^0 = x^n-1
- divide over (x-1): Sn=(x^n-1)/(x-1).
■
*/