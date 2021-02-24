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
      List<int[]> path = new ArrayList<>();
      int n = grid.length, m = grid[0].length;
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
          if (grid[i][j] == word.charAt(0) && dfs(grid, word, path, i, j)) {
            return path;
          }
        }
      }
      return null;
    }

    private boolean dfs(char[][] grid, String word, List<int[]> path, int i, int j) {
      int[][] dirs = {{0, 1}, {1, 0}};
      // if in bounds and the letter matches, search
      if (i > 0 && i < grid.length && j > 0 && j < grid[0].length && grid[i][j] == word.charAt(path.size() - 1)) {
        path.add(new int[] {i, j});
        if (path.size() == word.length()) {
          return true;
        }
        return dfs(grid, word, path, i+1, j) || dfs(grid, word, path, i, j+1);
      }
      return false;
    }
}
