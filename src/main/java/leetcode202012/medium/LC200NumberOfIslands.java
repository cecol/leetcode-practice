package leetcode202012.medium;

import leetcode20200921to20201031.BasicTemplate;

public class LC200NumberOfIslands extends BasicTemplate {
  public static void main(String[] args) {
    var LC = new LC200NumberOfIslands();
    LC.numIslands(new char[][]{
        {'1', '1', '1', '1', '0'},
        {'1', '1', '0', '1', '0'},
        {'1', '1', '0', '0', '0'},
        {'0', '0', '0', '0', '0'}});

    LC.numIslands(new char[][]{
        {'1', '1', '0', '0', '0'},
        {'1', '1', '0', '0', '0'},
        {'0', '0', '1', '0', '0'},
        {'0', '0', '0', '1', '1'}});

  }

  public int numIslands(char[][] grid) {
    int m = grid.length;
    int n = grid[0].length;

    int c = 1;
    for (int x = 0; x < m; x++)
      for (int y = 0; y < n; y++) {
        if (grid[x][y] == '1') {
          c++;
          dfs(grid, x, y, c);
        }
      }
    log.debug("{}", c);
    return c - 1;
  }

  public void dfs(char[][] g, int x, int y, int c) {
    int m = g.length;
    int n = g[0].length;
    if (x < 0 || y < 0 || x >= m || y >= n) return;
    g[x][y] += c;
    if (x > 0 && g[x - 1][y] == '1') dfs(g, x - 1, y, c);
    if (x < m - 1 && g[x + 1][y] == '1') dfs(g, x + 1, y, c);
    if (y > 0 && g[x][y - 1] == '1') dfs(g, x, y - 1, c);
    if (y < n - 1 && g[x][y + 1] == '1') dfs(g, x, y + 1, c);
  }
}
