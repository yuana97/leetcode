package psets.amzn;

import java.util.*;

public class spring_2021 {
  // Let's look at an application of binary search
  // Find Peak element
  // Given an array of ints find an integer which is bigger than its neighbors
  // S: Observe that a peak is locally the biggest number - to find a peak
  // we can just go in the direction of increase.
  // So we apply binary search maintaining the condition that we always 'go up',
  // similar to how regular binary search maintains the condition that we always
  // 'go towards x'.

  public int findPeak(int[] arr) {
    int lo = 0, hi = arr.length - 1;

    while (lo < hi) {
      // left-biased middle
      int mid1 = lo + (hi - lo) / 2;
      int mid2 = mid1+1;
      // go in the direction of increase: if positive slope
      // move the search window to the right, otherwise
      // move it left
      if (arr[mid1] < arr[mid2]) {
        lo = mid2;
      } else {
        hi = mid1;
      }
    }

    return lo;
  }

  // Another one
  // Square root an int
  // Binary search maintaining the condition that we always go in the direction
  // of sqrt(x) (decrease if n^2 > x, increase if n^2 < x)
  public int sqrt(int x) {
    int lo = 1, hi = x;
    while (lo < hi) {
      int mid = lo + (hi - lo) / 2; // get left-biased midpoint, avoiding overflow
      if (mid == x / mid) { // perfect square root => break early
        return mid;
      } else if (mid < x/mid) {
        lo = mid+1;
      } else {
        hi = mid - 1;
      }
    }
    return lo;
  }

  // Another BFS question, as I can almost guarantee you will encounter one
  // Treasure Island: You're given a matrix of 'S', 'O', 'D', 'X', where S's
  // represent ports your ship can sail from, 'O' represents open water, 'D' represents
  // a dangerous reef which will cause your ship to crash, and 'X' represents
  // a treasure island. Find the length of the shortest path from a port to a 
  // treasure island, avoiding reefs, where you're allowed to sail
  // north south east west. Return -1 if not possible.
  // Seems complicated, but it's just an application of BFS. You BFS from all the ports
  // at once, maintain a step counter, don't add reefs to your path, and return the
  // step count once your BFS encounters a treasure island.

  public int shortestPath(char[][] islands) throws IllegalArgumentException {
    if (islands == null || islands[0] == null) {
      throw new IllegalArgumentException();
    }
    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    // exit fast in empty case
    if (islands.length == 0 || islands[0].length == 0) {
      return -1;
    }
    int height = islands.length, width = islands[0].length;
    // 'level' queue of BFS
    Queue<int[]> q = new LinkedList<>();
    int steps = 1;
    // add roots of BFS
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        if (islands[i][j] == 'S') {
          q.add(new int[]{i, j});
        }
      }
    }

    while (!q.isEmpty()) {
      // we use this pattern when we need to keep track of levels
      int size = q.size();
      for (int i = 0; i < size; i++) {
        int[] coord = q.poll();
        for (int[] dir : dirs) {
          int x = coord[0] + dir[0], y = coord[1] + dir[1];
          // boundary check
          if (x < 0 || x >= height || y < 0 || y >= width || islands[x][y] == 'D') {
            continue;
          }
          if (islands[x][y] == 'X') {
            return steps;
          }
          q.add(new int[]{x, y});
        }
      }
      steps++;
    }
    return -1;
  }
}
