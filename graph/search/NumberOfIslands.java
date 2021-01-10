package graph.search;

import java.util.*;

/*
Question: Given a binary array where 0s represent water and 1s represent land,
return the number of 'islands' where an island is formed by land which is connected
vertically or horizontally.

Solution: use a boolean array to mark where you've visited. When you encounter an unvisited
'1' in the input, DFS and mark the 'visited' array

Script:
- Are we guaranteed that the array is rectangular?
- Are we guaranteed the array is not null?
- In that case let's handle it by throwing an exception.
- By binary array do you mean the input is a boolean array?
- Are allowed to modify the input array?

- Let me give a quick overview of the problem.
- Essentially, what we want to do, is iterate through the array.
When we encounter a piece of land we have not visited before, we want to
increment our count, and search through the whole island and mark everything
as 'visited'.
- We can do this using a DFS, and it should give us an O(NM) solution, where
N,M are the side lengths of the array.
- If you agree with my analysis, I can start coding it up.
*/

public class NumberOfIslands {
  public int numIslands(boolean[][] grid) {
    int count = 0;
    int n = grid.length, m = grid[0].length;
    boolean[][] visited = new boolean[n][m];
    for (int i =0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] && !visited[i][j]) {
          dfs(grid,i,j,visited);
          count++;
        }
      }
    }
    return count;
  }

  private void dfs(boolean[][] grid, int i, int j, boolean[][] visited) {
    // if in bounds and land, mark visited and DFS
    if(i>=0 && j>=0 && i<grid.length && j<grid[0].length && grid[i][j]){
      visited[i][j] = true;
      dfs(grid, i+1, j, visited);
      dfs(grid, i-1, j, visited);
      dfs(grid, i, j+1, visited);
      dfs(grid, i, j-1, visited);
    }
  }
}
