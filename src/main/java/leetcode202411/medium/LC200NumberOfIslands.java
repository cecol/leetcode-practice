package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC200NumberOfIslands extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 初見殺, 看來這類 dfs 配上 boolean visited 是很有用的做法
    public int numIslands(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] v = new boolean[m][n];
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1' && !v[i][j]) {
                    dfs(grid, i, j, v);
                    res++;
                }
            }
        }
        return res;
    }

    void dfs(char[][] g, int i, int j, boolean[][] v) {
        int m = g.length, n = g[0].length;
        if (i < 0 || j < 0 || i >= m || j >= n || v[i][j] || g[i][j] == '0') return;

        v[i][j] = true;
        dfs(g, i + 1, j, v);
        dfs(g, i, j + 1, v);
        dfs(g, i, j - 1, v);
        dfs(g, i - 1, j, v);
    }
}
