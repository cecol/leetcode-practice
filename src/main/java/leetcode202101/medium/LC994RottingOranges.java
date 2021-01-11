package leetcode202101.medium;

import leetcode20200921to20201031.BasicTemplate;

import java.util.LinkedList;
import java.util.Queue;

public class LC994RottingOranges extends BasicTemplate {
  public static void main(String[] args) {
    var LC = new LC994RottingOranges();
    var s = LC.orangesRotting(new int[][]{{2, 1, 1}, {1, 1, 0}, {0, 1, 1}});
    var s1 = LC.orangesRotting(new int[][]{{2, 1, 1}, {0, 1, 1}, {1, 0, 1}});
    var s3 = LC.orangesRotting(new int[][]{{0, 2}});
  }

  public int orangesRotting(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;
    int res = 0;
    Queue<int[]> bfsVisited = new LinkedList<>();
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == 2) {
          grid[i][j] = 0;
          bfsVisited.offer(new int[]{i, j});
        } else if (grid[i][j] == 1) {
          grid[i][j] = Integer.MAX_VALUE;
        } else if (grid[i][j] == 0) {
          grid[i][j] = -1;
        }
      }
    }
    int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    while (!bfsVisited.isEmpty()) {
      int[] v = bfsVisited.poll();
      for (int[] move : dirs) {
        int mx = v[0] + move[0];
        int my = v[1] + move[1];
        if (mx < 0 || my < 0 || mx == m || my == n || grid[mx][my] == 0 || grid[mx][my] <= grid[v[0]][v[1]])
          continue;
        grid[mx][my] = grid[v[0]][v[1]] + 1;
        res = Math.max(res, grid[mx][my]);
        bfsVisited.offer(new int[]{mx, my});
      }
    }
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == Integer.MAX_VALUE) return -1;
      }
    }
    return res;
  }

  /**
   * 好一點解法, 原本自己寫的是可以過, 但有些coner case要特別處理
   * 這個用count_fresh解蠻特別的
   */
  public int orangesRotting2(int[][] grid) {
    if (grid == null || grid.length == 0) return 0;
    int rows = grid.length;
    int cols = grid[0].length;
    Queue<int[]> queue = new LinkedList<>();
    int count_fresh = 0;
    //Put the position of all rotten oranges in queue
    //count the number of fresh oranges
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (grid[i][j] == 2) {
          queue.offer(new int[]{i, j});
        } else if (grid[i][j] == 1) {
          count_fresh++;
        }
      }
    }
    //if count of fresh oranges is zero --> return 0
    if (count_fresh == 0) return 0;
    int count = 0;
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    //bfs starting from initially rotten oranges
    while (!queue.isEmpty()) {
      ++count;
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        int[] point = queue.poll();
        for (int[] dir : dirs) {
          int x = point[0] + dir[0];
          int y = point[1] + dir[1];
          //if x or y is out of bound
          //or the orange at (x , y) is already rotten
          //or the cell at (x , y) is empty
          //we do nothing
          if (x < 0 || y < 0 || x >= rows || y >= cols || grid[x][y] == 0 || grid[x][y] == 2) continue;
          //mark the orange at (x , y) as rotten
          grid[x][y] = 2;
          //put the new rotten orange at (x , y) in queue
          queue.offer(new int[]{x, y});
          //decrease the count of fresh oranges by 1
          count_fresh--;
        }
      }
    }
    return count_fresh == 0 ? count - 1 : -1;
  }
}
