package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class LC542_01Matrix extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 直觀用 DFS 解 但都失敗. 看到以前寫法才想到 BFS 才是秒解
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        Queue<int[]> bfs = new LinkedList<>();
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) bfs.add(new int[]{i, j});
                else mat[i][j] = Integer.MAX_VALUE;
            }

        int[][] dirs = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        while (bfs.size() > 0) {
            int[] b = bfs.poll();
            for (int[] d : dirs) {
                int x = d[0] + b[0], y = d[1] + b[1];
                if (x < 0 || y < 0 || x >= m || y >= n || mat[x][y] <= mat[b[0]][b[1]] + 1) continue;
                bfs.add(new int[]{x, y});
                mat[x][y] = mat[b[0]][b[1]] + 1;
            }
        }
        return mat;
    }
}
