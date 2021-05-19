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
    // init sliding window
    int left = 0, right = 0;
    Set<Character> window = new HashSet<Character>();

    // at each step, add character if OK, otherwise remove a character
    while (right < s.length()) {
      // no repeat -> add a character
      if (!window.contains(s.charAt(right))) {
        window.add(s.charAt(right));
        right++;
        // update the max after adding a character
        max = Math.max(max, window.size());
      } else {
        // repeat -> remove a character
        window.remove(s.charAt(left));
        left++;
      }
    }
    // return our result
    return max;
  }

  
}
