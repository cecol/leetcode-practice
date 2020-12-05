package leetcode202011.medium;

import leetcode20200921to20201031.BasicTemplate;

public class LC1155NumberOfDiceRollsWithTargetSum extends BasicTemplate {
  public static void main(String[] args) {
    var LC = new LC1155NumberOfDiceRollsWithTargetSum();
    var s = LC.numRollsToTarget(1, 6, 3);
  }

  /**
   * https://zxi.mytechroad.com/blog/dynamic-programming/leetcode-1155-number-of-dice-rolls-with-target-sum/
   */
  public int numRollsToTarget(int d, int f, int target) {
    if (target > d * f) return 0;
    int[][] dp = new int[d][target + 1];
    int mod = (int) Math.pow(10, 9) + 7;
    for (int i = 1; i <= f && i <= target; i++) dp[0][i] = 1;
    for (int i = 1; i < d; i++)
      for (int j = 1; j <= f; j++)
        for (int k = j; k <= target; k++) {
          dp[i][k] = (dp[i][k] + dp[i - 1][k - j]) % mod;
        }
    return dp[d - 1][target];
  }
}
