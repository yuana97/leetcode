package greedy;

import java.util.*;

/*
Question: Given an integer k and a string str, rearrange str such that the same characters
are at least k apart.

Solution: Try forming a string using the most common characters first.
*/
public class RearrangeString {
  public String rearrangeString(String str, int k) {
    if (k == 0)
      return str;
    // calculate frequency of each character
    final HashMap<Character, Integer> map = new HashMap<Character, Integer>();
    for (int i = 0; i < str.length(); i++) {
      char c = str.charAt(i);
      if (map.containsKey(c)) {
        map.put(c, map.get(c) + 1);
      } else {
        map.put(c, 1);
      }
    }

    // form a priority queue which sorts characters by frequency
    PriorityQueue<Character> queue = new PriorityQueue<Character>(
      // lambda function which compares character by frequency. Breaks ties by
      // alphabetical order.
      new Comparator<Character>() {
        public int compare(Character c1, Character c2) {
          if (map.get(c2) != map.get(c1)) {
            return map.get(c2) - map.get(c1);
          } else {
            return c1.compareTo(c2);
          }
        }
      });
    // add all characters to the priority queue
    for(char c: map.keySet())
      queue.offer(c);
    // our output string
    StringBuilder sb = new StringBuilder();
    // characters remaining to add
    int len = str.length();
    // add characters to sb with a greedy algorithm
    while(!queue.isEmpty()) {
      // number of characters to add. We add characters in k-blocks until we hit the
      // end of the string.
      int cnt = Math.min(k, len);
      // hold the characters we used in this block
      ArrayList<Character> temp = new ArrayList<Character>();
      // add the next block of characters
      for (int i = 0; i < cnt; i++) {
        // empty => ran out of characters to put in this block => return "" as an error code
        if (queue.isEmpty())
          return "";
        // use the most frequent character in the string
        char c = queue.poll();
        sb.append(String.valueOf(c));
        len--;
        // reduce c's frequency by 1 and add it to temp if we need to use it again later
        map.put(c, map.get(c) - 1);
        if (map.get(c) > 0) {
          temp.add(c);
        }
      }
      // put used characters back into queue
      for (char c : temp)
        queue.offer(c);
    }

    return sb.toString();
  }
}