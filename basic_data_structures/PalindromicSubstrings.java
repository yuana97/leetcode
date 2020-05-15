package basic_data_structures;

/**
 * Palindromic Substrings (Facebook Intern)
 * 
 * Question:
 * Given a string find how many palindromic substrings it has.
 * 
 * Solution:
 * For each possible palindrome center (a letter or gap between 2 letters) try
 * extending a palindrome around it.
 */

public class PalindromicSubstrings {
  public int countSubstrings(String s) {
    int count = 0;
    // get the number of palindromes for each possible center
    for (int i = 0; i < s.length(); i++) {
      count += extendPalindrome(s, i, i); // extend around the ith letter
      count += extendPalindrome(s, i, i+1); // extend around the gap between letters i and i+1
    }
    return count;
  }

  // return the number of palindromes with (left, right) as its center
  private int extendPalindrome(String s, int left, int right) {
    int count = 0;
    while (left >= 0 && right < s.length() && (s.charAt(left) == s.charAt(right))) {
      left--;
      right++;
      count++;
    }
    return count;
  }
}