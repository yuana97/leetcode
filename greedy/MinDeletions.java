package greedy;

import java.util.*;

/*
Q: A string is called 'good' if there are no two different characters in s
that have the same frequency. Given a string of lowercase characters s, return the minimum number
of characters you need to delete to make s 'good'.

S:

- sort frequency of characters
- starting from the greatest frequency, set each frequency to the biggest possible
number (while keeping frequencies unique and not increasing any)
*/

public class MinDeletions {
  public int minDeletions(String s) {
    int res = 0;
    // obtain the sorted list of frequencies
    int[] freq = new int[26];
    for (char c : s.toCharArray()) {
      freq[c-'a']++;
    }
    Arrays.sort(freq);
    // expected value for the next frequency: one minus previous frequency
    int exp = freq[25];
    // iterating from the greatest frequencies
    for (int i = 25; i >= 0; i--) {
      // if we're done deleting characters, break
      if (freq[i] == 0) {
        break;
      }
      // if we have repeated frequencies, delete necessary number of characters
      if (freq[i] > exp) {
        // add the number of deleted characters to the answer
        res += freq[i] - exp;
      } else {
        // if we don't have repeated frequencies, reset the expected value
        exp = freq[i];
      }
      // update the expected value
      if (exp > 0) {
        exp--;
      }
    }
    return res;
  }
}
