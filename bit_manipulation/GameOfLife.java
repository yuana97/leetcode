package bit_manipulation;

/*
Question:
The Game of Life is a cellular automaton devise by the British mathematician John Conway in 1970. You start with
an array of 0's (dead) and 1's (alive). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal)
according to the following rules:

1. Any live cell with < 2 live neighbors dies as if by underpopulation
2. Any live cell with 2 or 3 live neighbors lives
3. Any live cell with more than 3 live neighbors dies as if by overpopulation
4. Any dead cell with exactly 3 live neighbors becomes alive as if by reproduction

Write a function to calculate the next state from the current state using O(1) memory.

Solution:
Encode dead -> dead as 00 (0), live -> dead as 01 (1), dead -> live as 10 (2), live -> live as 11 (3).
In the beginning every cell is 00 or 01. Calculate the state of each cell and set its second bit encoding 
its dead/alive state on the next turn. In the end right shift each cell by 1 (>>1) to get back to 00 or 01.
*/

public class GameOfLife {
  public void gameOfLife(int[][] board) {
    if (board == null || board.length == 0) return;
    int m = board.length, n = board[0].length;

    for (int i = 0; i < m; i++) {
      // make state changes if necessary
      for (int j = 0; j < n; j++) {
        // get the number of live neighbors
        int lives = liveNeighbors(board, m, n, i, j);
        // encode live -> live as 11
        if (board[i][j] == 1 && lives >=2 && lives <= 3) {
          board[i][j] = 3;
        }
        // encode dead -> live as 10
        if (board[i][j] == 0 && lives == 3) {
          board[i][j] = 2;
        }
      }
    }
    // rightshift by 1 to get the new state
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        board[i][j] >>= 1;
      }
    }
  }

  public int liveNeighbors(int[][] board, int m, int n, int i, int j) {
    int lives = 0;
    // this odd-looking for loop just iterates over all neighbors of (i, j)
    for (int x = Math.max(i-1, 0); x <= Math.min(i+1, m-1); x++) {
      for (int y = Math.max(j-1, 0); y <= Math.min(j+1, n-1); y++) {
        // peel off the first bit with x&1 to check if the cell is alive
        lives += board[x][y] & 1;
      }
    }
    return lives;
  }
}