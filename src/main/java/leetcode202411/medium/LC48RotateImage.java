package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.Arrays;

public class LC48RotateImage extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 沒過
    // 主因不會對稱轉 -> i = 0 to m, j = i to m, m[i][j] vs m[j][i] 互轉
    public void rotate(int[][] matrix) {
        int m = matrix.length;
        for (int i = 0; i < m; i++) {
            for (int j = i; j < m; j++) {
                int t = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = t;
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m / 2; j++) {
                int t = matrix[i][j];
                matrix[i][j] = matrix[i][m - 1 - j];
                matrix[i][m - 1 - j] = t;
            }
        }
    }
}
