package leetcode202011.medium;

import leetcode20200921to20201031.BasicTemplate;

public class LC494TargetSum extends BasicTemplate {
    public static void main(String[] args) {
        var LC = new LC494TargetSum();
        var s1 = LC.findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3);
        var s2 = LC.findTargetSumWays(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 1}, 1);
    }

    /**
     * http://www.noteanddata.com/leetcode-494-Target-Sum-java-solution-note.html
     */
    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for (int n : nums) sum += n;
        if (S > sum || S < -sum) return 0;
        int size = 2 * sum + 1;
        int[][] dp = new int[nums.length][size];
        dp[0][sum + nums[0]] += 1;
        dp[0][sum - nums[0]] += 1;
        for (int i = 1; i < nums.length; i++)
            for (int j = 0; j < dp[0].length; j++) {
                if (dp[i - 1][j] > 0) {
                    if (j + nums[i] < dp[i].length) dp[i][j + nums[i]] += dp[i - 1][j];
                    if (j - nums[i] >= 0) dp[i][j - nums[i]] += dp[i - 1][j];
                }
            }
        logIntArray(dp);
        return dp[nums.length - 1][sum + S];
    }
}
