package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.Arrays;

public class LC73SetMatrixZeroes extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 直觀解,
    // 有想起 constant space, 記下邊界有 0 , 再去掃邊界
    public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean r0 = false, rn = false, c0 = false, cn = false;
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) c0 = true;
            if (matrix[i][n - 1] == 0) cn = true;
        }
        for (int i = 0; i < n; i++) {
            if (matrix[0][i] == 0) r0 = true;
            if (matrix[m - 1][i] == 0) rn = true;
        }

        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }

        for (int i = 1; i < n - 1; i++) {
            if (matrix[0][i] == 0) {
                for (int j = 1; j < m; j++) matrix[j][i] = 0;
            }
        }
        for (int i = 1; i < m - 1; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 1; j < n; j++) matrix[i][j] = 0;
            }
        }
        if (r0) Arrays.fill(matrix[0], 0);
        if (rn) Arrays.fill(matrix[m - 1], 0);
        if (c0) {
            for (int i = 0; i < m; i++) matrix[i][0] = 0;
        }
        if (cn) {
            for (int i = 0; i < m; i++) matrix[i][n - 1] = 0;
        }
    }
}
