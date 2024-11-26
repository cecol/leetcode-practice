package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.Deque;
import java.util.LinkedList;

public class LC198HouseRobber extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 2維 DP 秒解
    public int rob(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][2];
        dp[0][1] = nums[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            dp[i][1] = dp[i - 1][0]+ nums[i];
        }
        return Math.max(dp[n-1][0], dp[n-1][1]);
    }
}
