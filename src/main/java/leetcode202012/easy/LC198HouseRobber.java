package leetcode202012.easy;

import leetcode20200921to20201031.BasicTemplate;

public class LC198HouseRobber extends BasicTemplate {
  public static void main(String[] args) {
    var LC = new LC198HouseRobber();
    var s = LC.rob(new int[]{});
  }


  public int rob(int[] nums) {
    if(nums.length == 0) return 0;
    int n = nums.length;
    int[][] dp = new int[2][n];
    dp[1][0] = nums[0];
    for(int i = 1; i<n;i++) {
      for(int j = 0; j<1;j++){
        dp[0][i] = Math.max(dp[0][i-1],dp[1][i-1]);
        dp[1][i] = dp[0][i-1] + nums[i];
      }
    }
    return Math.max(dp[0][n-1],dp[1][n-1]);
  }
}
