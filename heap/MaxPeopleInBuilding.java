package heap;

import java.util.*;

public class MaxPeopleInBuilding {
  public int maxPeopleInBuilding(int[][] intervals) {
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
