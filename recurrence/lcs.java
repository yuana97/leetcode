package recurrence;

public class lcs {
  // Question:
  // Given two strings, determine the length of the longest common subsequence.
  // Solution:
  // 
  public int longestCommonSubsequence(String s1, String s2) {
    // dp[i][j] = LCS between 1st i letters of s1, j letters of s2
    int[][] dp = new int[s1.length()+1][s2.length() + 1];
    for (int i = 0; i < s1.length(); i++) {
      for (int j = 0; j < s2.length(); j++) {
        // letter match => add one to max length
        if (s1.charAt(i) == s2.charAt(j)) {
          dp[i+1][j+1] = 1 + dp[i][j];
        } else {
          // otherwise use a previous result
          dp[i+1][j+1] = Math.max(dp[i][j+1], dp[i+1][j]);
        }
      }
    }
    return dp[s1.length()][s2.length()];
  }
}
