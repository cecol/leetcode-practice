package leetcode20200921to20201031.medium;

import leetcode20200921to20201031.BasicTemplate;

import java.util.stream.IntStream;

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

    /**
     * https://leetcode.com/problems/partition-equal-subset-sum/discuss/671810/Java-with-picture
     */
    public boolean canPartition2(int[] nums) {
        int s = IntStream.of(nums).sum();
        int h = s >> 1;
        if (s % 2 != 0) return false;

        boolean dp[] = new boolean[h + 1];
        dp[0] = true;
        for (int n : nums)
            for (int i = h; i >= n; i--) {
                dp[i] |= dp[i-n];
                if(dp[i] && i == h) return true;
            }
        return false;
    }

}
