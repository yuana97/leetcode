package basic_data_structures;

public class NumberOfPairsSumGreaterThanTarget {
  // Question: Given a sorted array of numbers, return the number of pairs of elements in that list
  // with sum greater than or equal to a target number.

  // Script:
  // To clarify, the input array is an array of integers, is that correct?
  // Are we guaranteed that this array is not null?
  // If it is null, let's handle the error by throwing an exception.
  // Since if we return, for example, 0, this error code can't be distinguished
  // from having a legitimate array and just finding no matches.

  // Now, let me give a quick overview of the problem, and propose a solution.
  // Because the array is sorted, let's start with two pointers at the first and last elements of the array.
  // Let's observe that as increase the left pointer, the sum always increases.
  // Vice versa, as we decrease the right pointer, the sum always decreases.
  // We can use this structure to efficiently count the number of pairs.
  // What I mean by this is that, if the sum is too small, we'll increase the left pointer.
  // If the sum is large enough, we can add the whole block between the left and right pointers,
  // because we can match the right number with all the numbers between the left and right numbers.
  // And then we decrease the right pointer to look for more matches.
  // This should give us an O(N) solution to the problem.
  // If you agree with this assessment, I can start coding it up.

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
