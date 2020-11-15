package leetcode202011.medium;

import leetcode20200921to20201031.BasicTemplate;

public class LC221MaximalSquare extends BasicTemplate {
    public static void main(String[] args) {
        var LC = new LC221MaximalSquare();
        var s = LC.maximalSquare(null);
    }

    /**
     * https://zxi.mytechroad.com/blog/dynamic-programming/leetcode-221-maximal-square/
     */
    public int maximalSquare(char[][] matrix) {
        int max = 0;
        int[][] dp = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) dp[i][0] = matrix[i][0] == '1' ? 1 : 0;
        for (int i = 0; i < matrix[0].length; i++) dp[0][i] = matrix[0][i] == '1' ? 1 : 0;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
                max = Math.max(max,dp[i][j]);
            }
        }
        return max;
    }
}
