package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LC994RottingOranges extends BasicTemplate {
    public static void main(String[] args) {
    }


    // 完全解不出來, 只知道是 bfs, 但要求最低 res, 一定要
    // 1. grid == 1 轉成 Integer.MAX_VALUE -> 還在 bfs 比較出最低回合數
    // 2. grid == 0 轉成 -1 -> 到達不了
    // 3. grid == 2 轉成 0 -> 初始回合數, 基底
    // 在 bfs 過程中是拿 grid[x][y] <= grid[v[0]][v[1]] 來當作找到對低影響的回合數
    // res 則是在找到最低影響回合數 res = Math.max(res, grid[x][y]); 往上漲上去
    public int orangesRotting(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int res = 0;
        LinkedList<int[]> bfs = new LinkedList<>();
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    bfs.add(new int[]{i, j});
                    grid[i][j] = 0;
                } else if (grid[i][j] == 1) {
                    grid[i][j] = Integer.MAX_VALUE;
                } else grid[i][j] = -1;
            }
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (bfs.size() > 0) {
            int[] v = bfs.poll();
            for (int[] d : dirs) {
                int x = d[0] + v[0];
                int y = d[1] + v[1];
                if (x < 0 || y < 0 || x >= m || y >= n || grid[x][y] == 0 ||
                        grid[x][y] <= grid[v[0]][v[1]]
                ) continue;

                grid[x][y] = grid[v[0]][v[1]]+1;
                res = Math.max(res, grid[x][y]);
                bfs.add(new int[]{x, y});

            }
        }
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                if(grid[i][j] == Integer.MAX_VALUE) return -1;
            }
        return res;
    }
}
