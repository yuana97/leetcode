package sliding_window;

import java.util.*;

/*
Q: Given a string, find the length of the longest substring
with only unique characters.

Script:
Q: If the string is null, should we return 0, or throw an exception?
S: In that case, let's handle it by throwing an exception.

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

public class LongestSubstring {
  public int lengthOfLongestSubstring(String s) throws IllegalArgumentException {
    // null checking
    if (s == null) {
      throw new IllegalArgumentException("String must not be null");
    }
    // initialize our result
    int max = 0;
    // initialize our window
    int left = 0, right = 0;
    // set of characters in the window. We use a Set to check uniqueness
    Set<Character> window = new HashSet<Character>();
    // move the sliding window across the string
    int len = s.length();
    while (right < len) {
      // if we have a repeat, remove characters from the left
      // until we only have unique characters
      while (window.contains(s.charAt(right))) {
        window.remove(s.charAt(left));
        left++;
      }
      // expand the window
      right++;
      window.add(s.charAt(right));
      // update the max
      max = Math.max(max, right - left);
    }
    // return our result
    return max;
  }
}
