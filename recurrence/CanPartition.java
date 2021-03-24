package recurrence;

public class CanPartition {
  public boolean canPartitionKSubsets(int[] nums, int k) {
    int sum = 0;
    for (int i = 0; i < nums.length; i++) {
      sum += nums[i];
    }
    return k != 0 && sum % k == 0 && canPartition(0, nums, new boolean[nums.length], k, 0, sum/k);
  }

  //
  boolean canPartition(int start, int[] a, boolean[] seen, int k, int sum, int target) {
    if (k == 1) return true;
    if (sum == target) {
      return canPartition(0, a, seen, k-1, 0, target);
    }
    for (int i = start; i < a.length; i++)
  }
}
