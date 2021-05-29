package heap;

import java.util.*;

/*
Question: Find the kth largest element in an array of integers
Script:
- First I'm going to ask some clarifying questions.
- Is the input array a primitive array of integers?
- Is our input array guaranteed to be non-null?
- In that case, lets handle it by throwing an exception.

- Let me give a quick overview of the problem.
- Let's start by iterating through the array.
- In order to keep track of the kth largest element at any given point of our iteration
we can use a min heap of size k.
- At each iteration we add an element to the heap. If the heap is size > k we evict the smallest element.
- At the end of our iteration we can return the top element of the heap, this will be our desired element.

- This gives us an O(NlogK) solution to the problem.
- If you agree with my analysis, I can start coding it up.

*/

public class KthLargest {
  
  public int findKthLargest(int[] nums, int k) {
    // min oriented priority queue
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    // iterate
    for(int val : nums) {
        // add elements to heap
        pq.offer(val);

        // maintain heap size of k
        if(pq.size() > k) {
            pq.poll();
        }
    }
    return pq.peek();
  }
}