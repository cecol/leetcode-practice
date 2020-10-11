package leetcode202009.medium;

import leetcode202009.BasicTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class LC416PartitionEqualSubsetSum extends BasicTemplate {

    public static void main(String[] args) {
        var LC = new LC416PartitionEqualSubsetSum();
        var r = LC.canPartition(new int[]{1, 2, 3});
    }

    public boolean canPartition(int[] nums) {
        int s = 0;
        for (int i = 0; i < nums.length; i++) s += nums[i];
        if (s % 2 == 1) return false;
        int target = s / 2;
        boolean[][] dp = new boolean[nums.length][target + 1];
        if (nums[0] <= target) dp[0][nums[0]] = true;
        for (int i = 0; i < nums.length; i++) dp[i][0] = true;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (j < nums[i]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
                }
            }
        }
        return dp[dp.length - 1][dp[0].length - 1];
    }

}
