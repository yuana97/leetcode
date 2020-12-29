package basic_data_structures;

public class NumberOfPairsSumGreaterThanTarget {
  /*
  Question: Given a sorted array of numbers, return the number of pairs of elements in that list
  with sum greater than or equal to a target number.

  Script:
  - To clarify, the input array is an array of integers, is that correct?
  - Are we guaranteed that this array is not null?
  - If it is null, let's handle the error by throwing an exception.

  - Now, I'll give a quick overview of the problem.
  - Let's start with two pointers at the first and last elements of the array.
  - Because the array is sorted, as we move the left pointer to the right, 
  the sum always increases.
  - In the same way, as we move the right pointer to the left, the sum always decreases.
  - Therefore, if the sum is too small, we'll move the left pointer to the right.
  - If, the sum is large enough, we can count all the numbers between the left and right pointers
  - (Because the array is sorted, all the numbers between the left and right, form a match
  with the right number, since they're bigger than the left number)
  - And then we decrease the right pointer to look for more matches.
  - Once, the two pointers meet, we're done searching the array.
  - And this should give an O(N) solution to the problem.
  - If you agree with this analysis, I can start coding it up.
  */

  public int countPairs(int[] arr, int target) throws IllegalArgumentException {
    // first lets do a null check, and throw an error code
    if (arr == null) {
      throw new IllegalArgumentException("");
    }
    // let's initialize the final result
    int count = 0;
    // initialize two pointers, and end our iteration once they meet
    for (int lo = 0, hi = arr.length - 1; lo < hi;) {
      // if we find a match, add the whole block between hi and lo
      if (arr[lo] + arr[hi] >= target) {
        count += hi - lo;
        // then we yry a smaller hi
        hi--;
      }
      // no match, try a bigger lo
      else {
        lo++;
      }
    }
    // return our result
    return count;
  }
}
