package leetcode202011.medium;

import leetcode20200921to20201031.BasicTemplate;

public class LC1130MinimumCostTreeFromLeafValues extends BasicTemplate {
    public static void main(String[] args) {
        var LC = new LC1130MinimumCostTreeFromLeafValues();
        var s = LC.mctFromLeafValues(new int[]{6, 2, 4});
    }

    public int mctFromLeafValues(int[] arr) {
        int[][] dp = new int[arr.length][arr.length];
        int[][] maxDp = new int[arr.length][arr.length];
        for (int i = 0; i < arr.length; i++)
            for (int j = i; j < arr.length; j++) {
                if (i == j) maxDp[i][j] = arr[i];
                else dp[i][j] = Math.max(dp[i][j - 1], arr[j]);
            }
        for (int i = 0; i < arr.length; i++)
            for (int j = i + 1; j < arr.length; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + (maxDp[i][k] * maxDp[k + 1][j]));
                }
            }
        log.debug("dp: ");
        logIntArray(dp);
        return dp[0][dp[0].length - 1];
    }
}
