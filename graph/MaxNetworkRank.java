package graph;

/*

Microsoft 2019: Max Network Rank

Question:
N cities 1, 2, ... , N. M bidirectional roads between them. For each pair of cities connected by a road,
define their network rank as the total number of roads connected to either of the two cities.
Find the max network rank.

Signature:
int maxNetworkRank(int[] A, int[] B, int N) where N = # of cities and A, B are arrays of size M where
A[i], B[i] = cities at the ends of road i.

Solution:
Form a graph where the cities are the vertices and roads are the edges. Calculate the indegree function and
adjacency matrix, then find the max value of indegree(A) + indegree(B) - #roads-between-a-and-b.

*/

import java.util.*;

public class MaxNetworkRank {
  public int maxNetworkRank(int[] A, int[] B, int N) {
    int M = A.length;
    // indegree. we use N+1 on these arrays to make indexing easier
    int[] indegree = new int[N+1];
    // adjacency matrix. adj[i][j] = # of edges between i and j
    int[][] adj = new int[N+1][N+1];
    // calculate indegrees and adjacency matrix and result
    int max = 0;
    for(int i = 0; i < M; i++) {
      indegree[A[i]]++;
      indegree[B[i]]++;
      adj[A[i]][B[i]]++;
      adj[B[i]][A[i]]++;
      System.out.println(arrToString(indegree));
      System.out.println(arr2ToString(adj));
    }
    for(int i = 0; i < M; i++) {
      int networkRank = indegree[A[i]] + indegree[B[i]] - adj[A[i]][B[i]];
      max = networkRank > max ? networkRank : max;
    }
    return max;
  }

  private String arrToString(int[] arr) {
    if (arr == null || arr.length == 0) return "";
    String str = "" + arr[0]; 
    for (int i = 1; i < arr.length; i++) {
      str += " " + arr[i];
    }
    return str;
  }

  private String arr2ToString(int[][] arr2) {
    String str = "\n";
    for (int[] arr : arr2) {
      str += arrToString(arr) + "\n";
    }
    return str;
  }

  public static void main(String[] args) {
    int[] A = {1, 2, 3, 3};
    int[] B = {2, 3, 1, 4};
    int N = 4;
    MaxNetworkRank solution = new MaxNetworkRank();
    System.out.println(solution.maxNetworkRank(A, B, N));
  }
}