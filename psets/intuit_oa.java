package psets;

import java.util.*;

public class intuit_oa {
    // Q: Find word in a grid.
    // Given a char[][] input and a word, print the location of the word in the grid
    // as a list of 'row column' coordinates. The word can start anywhere in the grid
    // and consecutive letters can be either below or to the right of the previous letter.

    // S: DFS from every possible starting point.

    public void findWord(char[][] grid, String word) {
      List<int[]> path = findWordHelper(grid, word);
      for (int[] coord : path) {
        System.out.println("" + coord[0] + " " + coord[1]);
      }
    }

    private List<int[]> findWordHelper(char[][] grid, String word) {
      // init the list of coordinates
      List<int[]> result = new ArrayList<>();
      int n = grid.length, m = grid[0].length;
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
          if (grid[i][j] == word.charAt(0) && dfs(grid, word, result, i, j, new ArrayList<>())) {
            return result;
          }
        }
      }
      return null;
    }

    private boolean dfs(char[][] grid, String word, List<int[]> result, int i, int j, ArrayList<int[]> path) {
      // if in bounds and the letter matches, search
      if (i > 0 && i < grid.length && j > 0 && j < grid[0].length && grid[i][j] == word.charAt(path.size())) {
        path.add(new int[] {i, j});
        int size = path.size();
        if (size == word.length()) {
          result.clear();
          for (int k = 0; k < size; i++) {
            result.add(path.get(i));
          }
          return true;
        }
        boolean output = dfs(grid, word, result, i+1, j, path) || dfs(grid, word, result, i, j+1, path);
        path.remove(path.size()-1);
        return output;
      }
      return false;
    }

    // Q: Find the number
    // Given a string S and a number X, split S into blocks of length X.
    // Call a number valid if it is formed by choosing exactly one digit from
    // each block and placing them in order. Return the kth smallest unique
    // valid number as a string.
    // S: form an ordered set of digits for every block. Start by picking the
    // smallest number from each block. Then pick bigger numbers starting from the last
    // block going backwards until we hit the kth number.
    
    // S = string, X = block size, K = kth number
    public String validNumber(String S, int X, int K) {
      // represents the list of blocks in the string
      List<Set<Character>> sets = new ArrayList<>();
      for (int i = 0; i < S.length(); i++) {
        // if we're starting a new block ,add a set to the list
        if (i % X == 0) {
          sets.add(new TreeSet<Character>());
        }
        // add character to the set
        char c = S.charAt(i);
        // add it to the last set in the list
        // last set because we only need to add new elements to the newest set
        sets.get(sets.size()-1).add(c);
      }
      // by the end of this loop, we now have a list of tree sets containing the unique digits of each block in sorted order.
      // get this number so we can iterate from the right side of the list
      int blockCount = sets.size();
      // counter up to k so we know when we're done
      int counter = 0;
      String output = "";
      // iterate over the sets
      for (int i = blockCount - 1; i >= 0; i--) {
        // if we've already picked out the bigger numbers we need to
        // we can just pick out the smallest number
        if (counter == K) {
          output = sets.get(i).getFirst() + output;
        } else {
          // otherwise, go thru the set, increment until we hit k, then add the number to the output. If we hit the end of the set, also add that number to the output.
        for (int digit : sets.get(i)) {
          counter++;
          if (counter == K || digit == sets.get(i).getLast()) {
            output = digit + output;
            break;
          }
        }
        }
      }
      return output;
    }
    

    // Q: Text Justification
    // Given an array of words and a width maxWidth, return
    // an array of lines such that each line has exactly maxWidth
    // characters. You should pack as many words in a line as possible
    // and insert spaces when necessary. Extra spaces should be distributed
    // evenly. For the last line, it should be left justified w/o extra space
    // between words.
    // S: This is just a complicated question, we solve it by breaking it down
    // into smaller components. Let's have a helper 'findRight' to find the words we need to
    // add to the line. Let's get another helper 'justify' to return the justified string
    // using those words. Within the 'justify' function lets calculate the number of spaces
    // we need to pad with and the remaining extra spaces, then add words and pad them.

    public List<String> fullJustify(String[] words, int maxWidth) {
      int left = 0;
      List<String> result = new ArrayList<>();
      while (left < words.length) {
        // grab last index we need
        int right = findRight(left, words, maxWidth);
        // create the justified next line and add it
        result.add(justify(left, right, words, maxWidth));
        // reset left pointer
        left = right + 1;
      }
      return result;
    }

    private int findRight(int left, String[] words, int maxWidth) {
      // new word we're checking
      int right = left;
      // track the line length
      int sum = words[right++].length();

      // grab words until we exceed maximum line length
      while (right < words.length && (sum + 1 + words[right].length()) <= maxWidth) {
        sum += 1 + words[right++].length();
      }

      // grab the index before we exceed maximum line length
      return right - 1;
    }

    // create a justified line w/ the words between left and right
    private String justify(int left, int right, String[] words, int maxWidth) {
      // single word -> just skip, pad at the end
      if (right - left == 0) {
        return padResult(words[left], maxWidth);
      }

      // check if its the last line, we do things differently then
      boolean isLastLine = right == words.length - 1;
      // the number of space blocks between left and right
      int numSpaces = right - left;
      // total number of spaces we need
      int totalSpace = maxWidth - wordsLength(left, right, words);

      // block of spaces we need
      String space = isLastLine ? " " : blank(totalSpace / numSpaces);
      // nunmber of remaining spaces we need after adding 'space' between
      // each word
      int remainder = isLastLine ? 0 : totalSpace % numSpaces;

      StringBuilder result = new StringBuilder();
      for (int i = left; i <= right; i++) {
        // add word, add required spaces, add 1 more space if needed
        result.append(words[i])
          .append(space)
          .append(remainder-- > 0 ? " " : "");
      }

      // pad spaces at the end if necessary (this only does anything
      // for the last line)
      return padResult(result.toString().trim(), maxWidth);
    }

    // returns sum of word lengths between left and right
    private int wordsLength(int left, int right, String[] words) {
      int wordsLength = 0;
      for (int i = left; i <= right; i++) {
        wordsLength += words[i].length();
      }
      return wordsLength;
    }

    private String padResult(String result, int maxWidth) {
      return result + blank(maxWidth - result.length());
    }

    // returns 'length' spaces
    private String blank(int length) {
      return new String(new char[length]).replace('\0', ' ');
    }

    // Q: Length of longest common substring of 2 strings
    // S: DP on longest common suffix of prefixes
    public int LCS(String X, String Y) {
      int m = X.length(), n = Y.length();
      // dp[i][j] = longest common suffix of X[0..i-1], Y[0..j-1]
      int[][] dp = new int[m+1][n+1];

      int result = 0;
      for (int i = 0; i <= m; i++) {
        for (int j = 0; j <= n; j++) {
          // base case
          if (i == 0 || j == 0) {
            dp[i][j] = 0;
          } else if (X.charAt(i-1) == Y.charAt(j-1)) {
            // if character matches, increase length of suffix
            dp[i][j] = dp[i-1][j-1] + 1;
            result = Integer.max(result, dp[i][j]);
          } else {
            // no suffix match -> set it to 0
            dp[i][j] = 0;
          }
        }
      }
      return result;
    }
}
