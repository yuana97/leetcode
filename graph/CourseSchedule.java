package graph;

/*

Question:
N courses with prerequisites expressed as a list of pairs [a, b] meaning b
is a prerequisite for a. Write a function which returns true if we can finish all
the courses and false otherwise.

Solution:
We can finish all the courses iff the course graph (courses are vertices, draw edges from 
prerequisites to courses) has no cycle. To detect if the course has a cycle or not, we run topological
sort and see if it uses all the vertices.

Refresher on topological sort:
Topological sort on a directed graph produces a list of vertices such that "there is a directed edge from
u to v in the graph" implies "u comes before v in the graph". The standard algorithm is to remove all indegree 0
(indegree = #edges going into a vertex, and indegree 0 in this context means its a 'start vertex') and put them in the
list. This process may produce more indegree 0 vertices, we repeat until there are no more indegree 0 vertices.

If the graph has a loop, the loops vertices will always have positive indegree so the topological sort doesn't
consume them. On the other hand if the graph has no loops (it is a tree) we will keep removing leaves until
the whole tree is gone and all the vertices are used up.

Hence if all vertices are used up we return true and we return false otherwise.

Summary of topological sort:
Remove indegree 0 vertices over and over.

*/

import java.util.*;

public class CourseSchedule {

  public boolean canFinish(int N, int[][] prerequisites) {
    // the adjacency matrix of the graph
    // matrix[i][j] means there is an edge from i to j
    int[][] matrix = new int[N][N];
    // indegree[i] = indegree of i
    int[] indegree = new int[N];
    // form the graph and indegree function
    for (int i = 0; i < prerequisites.length; i++) {
      int course = prerequisites[i][0];
      int pre = prerequisites[i][1];
      // draw an edge from prerequisite to the course
      if (matrix[pre][course] == 0)
        indegree[course]++;
      matrix[pre][course] = 1;
    }

    // perform topological sort
    // count = number of courses we can take
    int count = 0;
    // put indegree 0 vertices to a queue
    Queue<Integer> queue = new LinkedList();
    for (int i = 0; i < indegree.length; i++) {
      if (indegree[i] == 0) queue.offer(i);
    }
    // remove each 0 indegree vertex and its edges from the graph.
    // If this process creates new 0 indegree vertices, add them to the graph.
    while (!queue.isEmpty()) {
      // remove a takeable course from the queue and update count
      int course = queue.poll();
      count++;
      for (int i = 0; i < N; i++) {
        // remove all the edges of course
        if (matrix[course][i] != 0) {
          if (--indegree[i] == 0)
            queue.offer(i);
        }
      }
    }
    // return if we can take all the course or not.
    return count == N;
  }

}