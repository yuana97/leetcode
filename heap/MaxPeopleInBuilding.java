package heap;

import java.util.*;

/*
Script:
- Ok, first I'm going to ask some clarifying questions.
- So my first question is, our input is guaranteed to be an integer double array, is that correct?
- And each element of the input is guaranteed to be a two-element array representing a time interval, is that correct?
- Ok, are we guaranteed that the input is not null?
- In that case, lets handle it by throwing an exception.

- Ok, let me get to solving the problem.
- Let's start by sorting the intervals by their start time.
- We want to iterate over the sorted list and update the number of people in the building for every new start time
which we encounter.
- In order to efficiently update the number of people, we can also maintain a heap (priority queue) of intervals
representing the number of people currently in the building, sorted by their ending time.
- That way, for every new interval we encounter, we can remove all the old intervals (end time < start time) from
the top of the heap, and update our max.
- This should give an O(NlogN) solution to the problem.
- If you agree with my analysis, I can start coding it up.
*/

public class MaxPeopleInBuilding {
  public int maxPeopleInBuilding(int[][] intervals) throws IllegalArgumentException {
    if (intervals == null) {
      throw new IllegalArgumentException("Input must not be null");
    }
    int max = 0;
    // sort intervals by start time
    Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
    // sorted by end times
    // pq contains the people in the building at the moment
    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
    for (int i = 0; i < intervals.length; i++) {
      int[] interval = intervals[i];
      while (pq.peek()[1] < interval[0]) {
        pq.remove();
      }
      pq.add(interval);
      max = Math.max(pq.size(), max);
    }
    return max;
  }
}
