class Solution
{

/*

Question:
Given an array of non-negative integers representing a height map (A[i] = height of 
a wall of width 1 at position i), output the volume of water the formation will trap
after rainfall (water will fill any gap between 2 walls up to a height equal to
the height of the shorter wall). https://leetcode.com/problems/trapping-rain-water/

Solution:
Key insight: each wall will hold water up to the shorter of the two tallest walls to
its left and right. We want to efficiently get access to the two tallest walls to the left and right
of a particular point. To do this, we initialize two pointers to the left and right of the array.
Each step we advance the shorter pointer and update maxheight on its side or water held.

Summary:
Two pointers, each step update maxheight of side or water held.

*/

#include <vector>

public:
  int trap(int A[], int n) {
    // initialize left and right pointers and result
    int left = 0; int right=n-1;
    int res = 0;
    // record tallest walls on left and right
    int maxleft = 0, maxright = 0;
    while(left <= right) {
      // pick the shorter side between left and right
      if(A[left] <= A[right]) {
        // if this wall is the tallest on its side, update the max height
        if (A[left] >= maxleft) maxleft=A[left];
        // otherwise it holds water: add the water held at left
        else res += maxleft - A[left];
        // move the left pointer
        left++;
      }
      // ditto above
      else {
        if(A[right] >= maxright) maxright = A[right];
        else res += maxright - A[right];
        right--;
      }
    }
    return res;
  }
};