package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

public class LC377CombinationSumIV extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 沒過
    // 這是可重複計算案例, 1,2,1 & 1,1,2 & 2,1,1 都計算
    // 所以是一維 dp 可解, 不段累加 dp[i] += dp[i-n] 即可
    public int combinationSum4(int[] nums, int target) {
        if (target == 0) return 1;
        if (nums == null || nums.length == 0) return 0;
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int n : nums) if (i >= n) dp[i] += dp[i - n];
        }
        return dp[target];
    }
}
