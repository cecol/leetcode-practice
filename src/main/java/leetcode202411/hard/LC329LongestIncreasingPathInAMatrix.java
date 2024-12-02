package leetcode202411.hard;


import leetcode20200921to20201031.BasicTemplate;

import java.util.PriorityQueue;

public class LC329LongestIncreasingPathInAMatrix extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 沒過
    // 1. dfs, 帶上 cache + 當前 max, i = 0 to m, j = 0 to n 都下去找
    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] cache = new int[m][n];
        int max = 0;
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                int len = dfs(matrix, i, j, cache, -1);
                max = Math.max(max, len);
            }
        return max;
    }

    int dfs(int[][] matrix, int i, int j, int[][] cache, int max) {
        int m = matrix.length, n = matrix[0].length;
        if (i < 0 || j < 0 || i >= m || j >= n) return 0;
        if (matrix[i][j] <= max) return 0;
        if (cache[i][j] != 0) return cache[i][j];
        int m1 = dfs(matrix, i + 1, j, cache, matrix[i][j]);
        int m2 = dfs(matrix, i, j + 1, cache, matrix[i][j]);
        int m3 = dfs(matrix, i - 1, j, cache, matrix[i][j]);
        int m4 = dfs(matrix, i, j - 1, cache, matrix[i][j]);
        cache[i][j] = Math.max(Math.max(m1, m2), Math.max(m3, m4)) + 1;
        return cache[i][j];
    }
}
