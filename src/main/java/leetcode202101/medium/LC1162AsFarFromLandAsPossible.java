package leetcode202101.medium;

import leetcode20200921to20201031.BasicTemplate;

import java.util.LinkedList;
import java.util.Queue;

public class LC1162AsFarFromLandAsPossible extends BasicTemplate {
    public static void main(String[] args) {
        var LC = new LC1162AsFarFromLandAsPossible();
        var s = LC.maxDistance(new int[][]{{0, 0, 0}, {0, 1, 0}, {1, 1, 1}});
    }

    public int maxDistance(int[][] grid) {
        int n = grid.length;
        Queue<int[]> bfsVisited = new LinkedList<>();
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    grid[i][j] = 0;
                    bfsVisited.offer(new int[]{i, j});
                } else grid[i][j] = Integer.MAX_VALUE;
            }
        }
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
        while (!bfsVisited.isEmpty()) {
            int[] v = bfsVisited.poll();
            for (int[] m : dirs) {
                int mx = v[0] + m[0];
                int my = v[1] + m[1];
                if (mx < 0 || my < 0 || mx == n || my == n || grid[mx][my] <= grid[v[0]][v[1]]) continue;
                bfsVisited.add(new int[]{mx, my});
                grid[mx][my] = grid[v[0]][v[1]] + 1;
                max = Math.max(max, grid[mx][my]);
            }
        }
        return max;
    }
}
