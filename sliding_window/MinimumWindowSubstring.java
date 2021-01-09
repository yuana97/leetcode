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
S: Let me draw an example string and show you what I mean:
D: asdffdsaasdf
S: We can start by including the first character 'a'. We expand
the string as far as we can while maintaining a window of unique
characters. So we expand the substring up to 'asdf'. Now, once the
constraint is violated with 'asdff', we shrink the substring from the
left until we get just 'f', and we repeat the process over again.
S: By updating the max every time we change the window, we can obtain
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
    int min = 0;
    // initialize indexes of min left/right
    int minLeft = 0, minRight = 0;
    // flag: found a substring which works
    boolean flag = false;
    // initialize sliding window
    int left = 0, right = 0;
    // frequency map representing characters of t
    HashMap<Character,Integer> map = new HashMap<Character,Integer>();
    // number of characters remaining to match (once this hits 0, we've found a working string)
    int count = t.length();
    // fill the map
    for (char c : t.toCharArray()) {
      // increment the frequency of each character (memorize this pattern)
      map.put(c, map.getOrDefault(c, 0) + 1);
    }
    // fill the map
    // return our result
    return flag ? s.substring(minLeft, minRight+1) : "";
  }
}
