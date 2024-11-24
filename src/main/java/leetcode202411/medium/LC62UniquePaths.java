package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC62UniquePaths extends BasicTemplate {
    public static void main(String[] args) {
    }


    // DP 基本題 秒解
    public int uniquePaths(int m, int n) {
        int[][] g = new int[m][n];
        Arrays.fill(g[0], 1);
        for (int i = 0; i < m; i++) g[i][0] = 1;
        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++) {
                g[i][j] += g[i][j-1];
                g[i][j] += g[i-1][j];
            }

        return g[m-1][n-1];
    }
}
