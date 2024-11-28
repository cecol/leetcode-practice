package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.PriorityQueue;

public class LC221MaximalSquare extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 直觀解 - dp
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] cc = new int[m][n];
        int res = 0;
        for (int i = 0; i < n; i++)
            if (matrix[0][i] == '1') {
                cc[0][i] = 1;
                res = 1;
            }
        for (int i = 0; i < m; i++)
            if (matrix[i][0] == '1') {
                res = 1;
                cc[i][0] = 1;
            }
        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == '0') continue;
                int min = cc[i - 1][j];
                min = Math.min(cc[i][j - 1], min);
                min = Math.min(cc[i - 1][j - 1], min);
                cc[i][j] = min + 1;
                res = Math.max(res, cc[i][j]);
            }

        return res * res;
    }
}
