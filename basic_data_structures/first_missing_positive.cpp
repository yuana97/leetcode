class Solution
{

#include <algorithm>

public:
  int firstMissingPositive(vector<int>& nums) {
    int n = nums.size();
    // put the positive numbers in the right place [1, 2, 3, ...]
    for (int i = 0; i < n; i++)
      // nums[i] > 0 && nums[i] <= n means while number is positive in the correct range
      // nums[nums[i] - 1] != nums[i] means number is in the wrong place
      while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i])
        // put nums[i] in the right place
        swap(nums[i], nums[nums[i] - 1]);
    // find first number in the wrong place and return it
    for (int i = 0; i < n; i++)
      // nums[i] != i + 1 => i+1 is missing
      if (nums[i] != i + 1)
        return i + 1;
    // nothing missing in 1,...,n => return n+1.
    return n + 1;
  }
  
};