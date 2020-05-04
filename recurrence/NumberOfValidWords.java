package recurrence;

/*
Number Of Valid Words For Each Puzzle (2020 Google Onsite)

Question:
A word is valid with respect to a puzzle string if both the following conditions are
satisfied:

- word contains the first letter of puzzle
- for each letter in word, that letter is in puzzle

Returns an array answer where answer[i] = number of words in a String[] words which
are valid with respect to puzzles[i].

Constraints:
- <= 10^5 words
- word length <= 50
- <= 10^4 puzzles
- puzzle length = 7 and each letter of a puzzle is unique
- words, puzzles are lowercase letters

Solution:
Encode unique characters of word and puzzle in bitmasks and check if the word letters
are a subset of the puzzle letters.
*/

import java.util.*;

public class NumberOfValidWords {
  public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
    Map<Integer, Integer> map = new HashMap<>();
    // map bitmask -> frequency
    for (String w : words) {
      // encode the unique characters of word with a bitmask
      int mask = 0;
      for (int i = 0; i < w.length(); i++) {
        mask |= 1 << (w.charAt(i) - 'a');
      }
      map.put(mask, map.getOrDefault(mask, 0) + 1);
    }

    List<Integer> res = new ArrayList<>();

    // for each puzzle iterate through all the subsets and see if they exist
    // in the map
    for(String p : puzzles) {
      // form a bitmask of p
      int mask = 0;
      for (int i = 0; i < p.length(); i++) {
        mask |= 1 << (p.charAt(i) - 'a');
      }
      int c = 0;
      int sub = mask;
      int first = 1 << (p.charAt(0) - 'a');
      // for each substring of the puzzle check if its in the map.
      while(true) {
        // if this substring includes the first letter and appears in the map
        // add to the answer
        if ((sub & first) == first && map.containsKey(sub)) {
          c += map.get(sub);
        }

        if (sub == 0) break;
        // get the next substring
        // this is bit trickery which encodes a recurrence. whats going on is this:
        // sub-1 => toggles all bits to the right of the rightmost 1, including the 1
        // & mask => get the subset of the resulting group of letters which is in the puzzle.
        // Why does this work?
        // First consider a puzzle on a 2 letter alphabet (it'll be 11, 10, or 01)
        // You can verify by hand that doing the below process will get every substring until it hits 0.
        // Now consider prepending a third letter to the puzzle...doing the below process
        // will get all the two-letter substrings and hit 00 on the last two bits as before, but now it toggles
        // the first bit and restarts the process. This extends recursively to N-letter substrings
        // (N=7 in our case)

        // you can summarize it this way: go thru all combinations of the right 1 letter (leaving the left six unchanged)
        // then go thru all combinations of the right 2 letters (leaving the left 5 unchanged)
        // etc. until you go thru all combinations total

        // you can also just memorize this, it's similar to Kernighan's algorithm for toggling the right bit
        sub = (sub - 1) & mask;
      }

      res.add(c);
    }
    return res;
  }
}