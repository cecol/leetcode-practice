package leetcode202011.medium;

import leetcode20200921to20201031.BasicTemplate;

public class LC1130MinimumCostTreeFromLeafValues extends BasicTemplate {
    public static void main(String[] args) {
        var LC = new LC1130MinimumCostTreeFromLeafValues();
        var s = LC.mctFromLeafValues(new int[]{6, 2, 4});
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
}
