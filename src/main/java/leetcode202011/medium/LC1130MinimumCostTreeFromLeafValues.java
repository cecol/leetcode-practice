package leetcode202011.medium;

import leetcode20200921to20201031.BasicTemplate;

public class LC1130MinimumCostTreeFromLeafValues extends BasicTemplate {
    public static void main(String[] args) {
        var LC = new LC1130MinimumCostTreeFromLeafValues();
        var s = LC.mctFromLeafValues2(new int[]{6, 2, 4});
    }

    /**
     * https://leetcode.com/problems/minimum-cost-tree-from-leaf-values/discuss/474188/I-think-I-able-to-explain-it-to-myself-and-to-you...(Java-DP)-.-Complexity-is-in-the-question
     */
    public int mctFromLeafValues(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int[][] dp = new int[arr.length][arr.length];
        return minSumOfNonLEafNodes(arr, 0, arr.length - 1, dp);
    }

    private int minSumOfNonLEafNodes(int[] arr, int startIndex, int endIndex, int[][] memo) {
        if (startIndex >= endIndex) return 0;
        if (memo[startIndex][endIndex] != 0) return memo[startIndex][endIndex];
        int res = Integer.MAX_VALUE;
        for (int i = startIndex; i < endIndex; i++) {
            int left = minSumOfNonLEafNodes(arr, startIndex, i, memo);
            int right = minSumOfNonLEafNodes(arr, i + 1, endIndex, memo);
            int maxLeft = 0;
            int maxRight = 0;

            for (int j = startIndex; j <= i; j++) maxLeft = Math.max(maxLeft, arr[j]);
            for (int j = i + 1; j <= endIndex; j++) maxRight = Math.max(maxRight, arr[j]);
            int valueOfTheNonLeafNode = maxLeft * maxRight;
            res = Math.min(res, valueOfTheNonLeafNode + left + right);
        }
        memo[startIndex][endIndex] = res;

        return res;
    }

    public int mctFromLeafValues2(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int n = arr.length;
        int[][] dp = new int[arr.length][arr.length];
        int[] maxRoot = new int[arr.length];
        for (int interval = 1; interval < n; interval++)
            for (int startIndex = 0; startIndex < n - interval; startIndex++) {
                int endIndex = startIndex + interval;
                dp[startIndex][endIndex] = Integer.MAX_VALUE;
                for (int middleIndex = startIndex; middleIndex < endIndex; middleIndex++) {
                    if (maxRoot[middleIndex] == 0) {
                        int maxLeft = 0;
                        for (int i = 0; i <= middleIndex; i++) maxLeft = Math.max(maxLeft, arr[i]);
                        int maxRight = 0;
                        for (int i = middleIndex + 1; i < n; i++) maxRight = Math.max(maxRight, arr[i]);
                        log.debug("middleIndex:{}, maxLeft:{}, maxRight:{}", middleIndex, maxLeft, maxRight);
                        maxRoot[middleIndex] = maxLeft * maxRight;
                    }
                    dp[startIndex][endIndex] = Math.min(dp[startIndex][endIndex], dp[startIndex][middleIndex] + dp[middleIndex + 1][endIndex] + maxRoot[middleIndex]);
                }
            }
        logIntArray(dp);
        log.debug("maxRoot:{}", maxRoot);
        return dp[0][arr.length - 1];
    }
}
