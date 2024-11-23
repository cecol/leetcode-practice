package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC416PartitionEqualSubsetSum extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 還是沒做成功
    // 有記得先加總 / 2 找到 target + dp
    // 但因為每個數字不能重複使用 -> 得做 2 維 dp, 才能找到答案
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int n : nums) sum += n;
        if (sum % 2 == 1) return false;
        int target = sum / 2;
        boolean[][] dp = new boolean[nums.length][target + 1];
        if (nums[0] <= target) dp[0][nums[0]] = true;
        for (int i = 0; i < nums.length; i++) dp[i][0] = true;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 1; j <= target; j++) {
                if (nums[i] > j) dp[i][j] = dp[i - 1][j];
                else {
                    dp[i][j] = dp[i - 1][j] | dp[i - 1][j - nums[i]];
                }
            }
        }

        return dp[nums.length-1][target];
    }


}
