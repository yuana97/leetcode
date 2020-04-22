package greedy;

/*

Slow Sum

Question:
Suppose we have a list of N numbers. Choose any two adjacent numbers and replace them with their sum. Call
the new value 'cost'. Repeat the operation until we only have a single number and add up all the 'costs' for
all the merges.

Return the maximum possible cost.

Solution:
Find the biggest pair and merge them. From then on the biggest pair is either to the left or the right
of the previous biggest pair. O(N).

*/

public class SlowSum {
  int getTotalTime(int[] arr) {
    // trivial case
    if (arr == null || arr.length == 0) return 0;
    // find the pair (l, r) with the highest sum
    int l = -1, r = -1;
    for (int i = 1; i < arr.length; i++) {
      if (l == -1 || arr[i-1]+arr[i] > arr[l] + arr[r]) {
        l = i-1;
        r = i;
      }
    }
    // merge the biggest pair and move the l, r pointers
    int currCost = arr[l] + arr[r];
    int totalCost = currCost;
    l--;
    r++;
    // while we still have numbers to merge in
    while (l >= 0 || r < arr.length) {
      // don't consider l/r if they are out of bounds, otherwise l = left number and r = right number
      int left = l < 0 ? Integer.MIN_VALUE : arr[l];
      int right = r >= arr.length ? Integer.MIN_VALUE : arr[r];
      // merge the bigger number
      if (left > right) {
        currCost += left;
        totalCost += currCost;
        l--;
      }
      else {
        currCost += right;
        totalCost += currCost;
        r++;
      }
    }
    return totalCost;
  }
}