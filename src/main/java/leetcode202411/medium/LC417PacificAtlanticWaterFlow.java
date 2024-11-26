package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC417PacificAtlanticWaterFlow extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 沒過, 這題最大的障眼法 -> 不是山脈往海 dfs, 是海 With Integer.MIN_VALUE dfs 下去
    // 準備兩個 visit of pacific & atlantic 兩個 visit 從各自兩邊海岸開始走 有走到都算
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> res = new ArrayList<>();
        if (heights == null || heights.length == 0 || heights[0].length == 0) return res;
        int m = heights.length, n = heights[0].length;
        boolean[][] p = new boolean[m][n];
        boolean[][] a = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            dfs(heights, p, Integer.MIN_VALUE, i, 0);
            dfs(heights, a, Integer.MIN_VALUE, i, n - 1);
        }
        for (int i = 0; i < n; i++) {
            dfs(heights, p, Integer.MIN_VALUE, 0, i);
            dfs(heights, a, Integer.MIN_VALUE, m - 1, i);
        }
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (p[i][j] && a[i][j]) res.add(List.of(i, j));

        return res;
    }

    void dfs(int[][] heights, boolean[][] v, int h, int r, int c) {
        int m = heights.length, n = heights[0].length;
        if (r < 0 || c < 0 || r == m || c == n || v[r][c] || heights[r][c] < h) return;
        v[r][c] = true;
        dfs(heights, v, heights[r][c], r + 1, c);
        dfs(heights, v, heights[r][c], r - 1, c);
        dfs(heights, v, heights[r][c], r, c + 1);
        dfs(heights, v, heights[r][c], r, c - 1);
    }
}
