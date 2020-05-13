package recurrence;

/**
 * Word Break (Facebook phone screen)
 * 
 * Question:
 * Given a nonempty string s and a dictionary containing a list of nonempty words,
 * add spaces to s to construct a sentence of dictionary words. Return a list of all
 * possible sentences.
 * 
 * Solution:
 * Memoized dfs. The recurrence here is wordbreak(substring). We just cache the results
 * and build up the solution of the entire string.
 */

 import java.util.*;

public class WordBreak {
  // memoize wordbreak(substrings of s)
  Map<String,List<String>> map = new HashMap<String,List<String>>();
  // wordbreak s with respect to wordDict
  public List<String> wordBreak(String s, Set<String> wordDict) {
    // result
    List<String> res = new ArrayList<String>();
    // null case
    if (s == null || s.length() == 0) {
      return res;
    }
    // memoized case
    if (map.containsKey(s)) {
      return map.get(s);
    }
    // trivial case
    if (wordDict.contains(s)) {
      res.add(s);
    }
    // chop off a tail of s and if it's a word, wordbreak on the prefix
    for(int i = 1; i < s.length(); i++) {
      // chop the suffix and check if its a word
      String t = s.substring(i);
      if (wordDict.contains(t)) {
        // if it is, wordbreak on the prefix
        List<String> temp = wordBreak(s.substring(0, i), wordDict);
        // put the results into res
        if (temp.size() != 0) {
          for (int j = 0; j < temp.size(); j++) {
            res.add(temp.get(j) + " " + t);
          }
        }
      }
    }
    map.put(s, res);
    return res;
  }
}