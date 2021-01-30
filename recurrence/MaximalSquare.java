package recurrence;

import java.util.*;

/*
Question: Given a binary matrix, return the area of the biggest square made up of 1's.

Script:

- Is it possible that the array is null?
- In that case, let's handle it by throwing an exception.

- Let me give a quick overview of the problem.
- We can consider a recurrence for the largest square 'ending' at a given point (i, j),
meaning the lower right corner of the square is (i, j).
- Let's call this recurrence f(i, j). We can derive f(i, j) by looking at the largest squares
directly north, west, and northwest of (i, j).
*/

public class MaximalSquare {

  public int maximalSquare(boolean[][] a) {
    if (a.length == 0) {
      return 0;
    }
    int m = a.length, n = a[0].length, result = 0;
    // dp[i][j] = side length of the biggest square with lower right corner at (i-1, j-1)
    // we make the dp array larger to avoid out of bounds errors
    int[][] dp = new int[m][n];
    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        // add '1' onto the 
        if (a[i-1][j-1]) {
          dp[i][j] = Math.min(Math.min(dp[i][j-1], dp[i-1][j-1]), dp[i-1][j]) + 1;
          result = Math.max(dp[i][j], result);
        }
      }
    }
    return result*result;
  }
  
}
