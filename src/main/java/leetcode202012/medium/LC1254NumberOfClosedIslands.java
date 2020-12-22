package leetcode202012.medium;

import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC1254NumberOfClosedIslands extends BasicTemplate {
  public static void main(String[] args) {
    var LC = new LC1254NumberOfClosedIslands();
    LC.closedIsland(new int[][]{
        {1, 1, 1, 1, 1, 1, 1, 0},
        {1, 0, 0, 0, 0, 1, 1, 0},
        {1, 0, 1, 0, 1, 1, 1, 0},
        {1, 0, 0, 0, 0, 1, 0, 1},
        {1, 1, 1, 1, 1, 1, 1, 0}});

  }

  public int closedIsland(int[][] grid) {
    if (grid.length < 3 || grid[0].length < 3) return 0;
    int m = grid.length;
    int n = grid[0].length;
    for (int i = 0; i < m; i++) {
      if (grid[i][0] == 0) dfsNoClosed(grid, i, 0);
      if (grid[i][n - 1] == 0) dfsNoClosed(grid, i, n - 1);
    }
    for (int i = 0; i < n; i++) {
      if (grid[0][i] == 0) dfsNoClosed(grid, 0, i);
      if (grid[m - 1][i] == 0) dfsNoClosed(grid, m - 1, i);
    }
    int max = 2;
    for (int x = 1; x < m; x++)
      for (int y = 1; y < n; y++) {
        if (grid[x][y] == 0) {
          dfsClosed(grid, x, y, max);
          max++;
        }
      }
    log.debug("{}", max);
    return max - 2;
  }

  public void dfsNoClosed(int[][] g, int x, int y) {
    int m = g.length;
    int n = g[0].length;
    if (x < 0 || x >= m || y < 0 || y >= n) return;
    g[x][y] = -1;
    if (x > 0 && g[x - 1][y] == 0) dfsNoClosed(g, x - 1, y);
    if (y > 0 && g[x][y - 1] == 0) dfsNoClosed(g, x, y - 1);
    if (x < m - 1 && g[x + 1][y] == 0) dfsNoClosed(g, x + 1, y);
    if (y < n - 1 && g[x][y + 1] == 0) dfsNoClosed(g, x, y + 1);
  }

  public void dfsClosed(int[][] g, int x, int y, int mark) {
    int m = g.length;
    int n = g[0].length;
    if (x < 0 || x >= m || y < 0 || y >= n) return;
    g[x][y] = mark;
    if (x > 0 && g[x - 1][y] == 0) dfsClosed(g, x - 1, y, mark);
    if (y > 0 && g[x][y - 1] == 0) dfsClosed(g, x, y - 1, mark);
    if (x < m - 1 && g[x + 1][y] == 0) dfsClosed(g, x + 1, y, mark);
    if (y < n - 1 && g[x][y + 1] == 0) dfsClosed(g, x, y + 1, mark);
  }
}
