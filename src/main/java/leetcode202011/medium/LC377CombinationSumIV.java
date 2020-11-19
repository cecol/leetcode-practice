package leetcode202011.medium;

import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.List;

public class LC377CombinationSumIV extends BasicTemplate {
  public static void main(String[] args) {
    var LC = new LC377CombinationSumIV();
    var s = LC.combinationSum4(new int[]{1, 2, 3}, 4);
    var s2 = LC.combinationSum4(new int[]{3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25}, 10);
  }

  public int combinationSum4(int[] nums, int target) {
    if (target == 0) return 1;
    if (nums == null || nums.length == 0) return 0;
    int[] dp = new int[target + 1];
    dp[0] = 1;
    for (int i = 1; i < dp.length; i++) {
      log.debug("dp: {}", dp);
      for (int n : nums) {
        if (i >= n) dp[i] += dp[i - n];
      }
    }
    log.debug("dp: {}", dp);
    return dp[target];
  }
}
