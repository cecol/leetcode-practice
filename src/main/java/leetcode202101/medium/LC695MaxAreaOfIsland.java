package leetcode202101.medium;

import leetcode20200921to20201031.BasicTemplate;

public class LC695MaxAreaOfIsland extends BasicTemplate {
    public static void main(String[] args) {
        var LC = new LC695MaxAreaOfIsland();
        var s = LC.maxAreaOfIsland(new int[][]{});
    }

    public int maxAreaOfIsland(int[][] grid) {
        int max = 0;
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    max = Math.max(max, dfs(grid, i, j));
                }
            }
        return max;
    }

    private int dfs(int[][] g, int i, int j) {
        if (i >= 0 && i < g.length && j >= 0 && j < g[0].length && g[i][j] == 1) {
            g[i][j] = 0;
            return 1 + dfs(g, i - 1, j) + dfs(g, i + 1, j) + dfs(g, i, j - 1) + dfs(g, i, j + 1);
        } else return 0;
    }
}
