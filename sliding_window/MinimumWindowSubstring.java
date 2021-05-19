package sliding_window;

import java.util.*;

/*
Q: Given two strings s and t, return the minimum substring of s
which contains all the characters of t. If it's not possible, return "".

Script:
Q: If s or t are null, should we return empty string, or throw an exception?
S: In that case, let's handle it by throwing an exception.
S: In the case that t is empty, s is empty, or t is bigger than s,
let's immediately return "".

S: Now, let me give a quick overview of the problem.
S: We can solve this problem by maintaining a sliding window
of unique characters.
S: Let me draw an example input and show you what I mean:
D: String s: qwertyasdfqwertya, t: fdsa
S: We can start by including the first character 'q'. We expand
the string until we have all the characters of t: 'qwertyasdf'. Then
we shrink our window until we no longer have all the characters of t:
'sdf'. Then we expand again, etc. until we reach the end of the string.
S: By updating the min every time we change the window, we can obtain
an O(N) solution to this problem.
S: If you agree with my analysis, I can start coding it up.

*/

public class MinimumWindowSubstring {
  public String minWindow(String s, String t) throws IllegalArgumentException {
    // null checking
    if (s == null || t == null) {
      throw new IllegalArgumentException("Strings must not be null");
    }
    // edge cases
    if (s.length() == 0 || t.length() == 0 || t.length() > s.length()) {
      return "";
    }
    // initialize the min to compare to 
    int min = Integer.MAX_VALUE;
    // initialize indexes of min left/right, representing our answer
    int minLeft = 0, minRight = 0;
    // flag: found a substring which works. Tells us whether to return
    // a substring or empty string.
    boolean flag = false;
    // initialize sliding window
    int left = 0, right = 0;
    // map of characters to frequency needed in the window
    HashMap<Character,Integer> window = new HashMap<Character,Integer>();
    // number of characters remaining to match (once this hits 0, we've found a working string)
    int count = t.length();
    // fill the map
    for (char c : t.toCharArray()) {
      // increment the frequency of each character (memorize this pattern)
      window.put(c, window.getOrDefault(c, 0) + 1);
    }

    // At each step, include the right character and shrink the left
    // until the string is no longer
    while (right < s.length()) {
      // include the right character
      char rightChar = s.charAt(right);
      // if we encounter a character of t, update work remaining
      if (window.containsKey(rightChar)) {
        // update frequency
        window.put(rightChar, window.get(rightChar) - 1);
        // if we needed rightChar, decrement count
        if (window.get(rightChar) >= 0) {
          count--;
        }
      }

      // while the window is valid, shrink it from the left
      while (count == 0) {
        // update flag
        flag = true;
        // update min
        int len = right - left + 1;
        if (len <= min) {
          minLeft = left;
          minRight = right;
          min = len;
        }

        // shrink from left
        char leftChar = s.charAt(left);
        // if we're omitting a character of t, update window/count
        if (window.containsKey(leftChar)) {
          window.put(leftChar, window.get(leftChar) + 1);
          // if we need to find another leftChar, increment count
          if (window.get(leftChar) >= 1) {
            count++;
          }
        }
        // increment left
        left++;
      }
      // increment right
      right++;
    }

    // return our result
    return flag ? s.substring(minLeft, minRight + 1) : "";
  }
}
