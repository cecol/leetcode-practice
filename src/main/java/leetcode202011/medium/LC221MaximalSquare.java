package leetcode202011.medium;

import leetcode20200921to20201031.BasicTemplate;

public class LC221MaximalSquare extends BasicTemplate {
    public static void main(String[] args) {
        var LC = new LC221MaximalSquare();
//        LC.maximalSquare1(new char[][]{
//                {'1', '0', '1', '0', '0'},
//                {'1', '0', '1', '1', '1'},
//                {'1', '1', '1', '1', '1'},
//                {'1', '0', '0', '1', '0'}
//        });
        LC.maximalSquare2(new char[][]{
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        });
    }

    /**
     * https://zxi.mytechroad.com/blog/dynamic-programming/leetcode-221-maximal-square/
     * O(N^3)
     */
    public int maximalSquare1(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] sum = new int[m][n];
        sum[0][0] = matrix[0][0] - '0';
        for (int i = 1; i < m; i++) sum[i][0] = matrix[i][0] - '0' + sum[i - 1][0];
        for (int i = 1; i < n; i++) sum[0][i] = matrix[0][i] - '0' + sum[0][i - 1];
        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++)
                sum[i][j] = matrix[i][j] - '0' + sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1];
        int max = 0;
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                for (int k = Math.min((m - 1) - i, (n - 1) - j); k >= 0; k--) {
                    int all = sum[i + k][j + k];
                    int left = j > 0 ? sum[i + k][j - 1] : 0;
                    int right = i > 0 ? sum[i - 1][j + k] : 0;
                    int intercept = i > 0 && j > 0 ? sum[i - 1][j - 1] : 0;

                    int s = all - left - right + intercept;
                    if (s == (k + 1) * (k + 1)) {
                        max = Math.max(max, s);
                    }
                }
        return max;
    }

    /**
     * https://zxi.mytechroad.com/blog/dynamic-programming/leetcode-221-maximal-square/
     * https://blog.csdn.net/fuxuemingzhu/article/details/82992233
     * O(N^2)
     */
    public int maximalSquare2(char[][] matrix) {
        int max = 0;
        int[][] dp = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == '1') {
                dp[i][0] = 1;
                max = 1;
            }
        }
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i] == '1') {
                dp[0][i] = 1;
                max = 1;
            }
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
                max = Math.max(max, dp[i][j]);
            }
        }
        return max * max;
    }
}
