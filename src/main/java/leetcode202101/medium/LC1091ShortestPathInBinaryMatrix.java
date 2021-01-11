package leetcode202101.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class LC1091ShortestPathInBinaryMatrix extends BasicTemplate {
  public static void main(String[] args) {
    Logger log = LoggerFactory.getLogger("Main");
    var LC = new LC1091ShortestPathInBinaryMatrix();
    var s = LC.shortestPathBinaryMatrix(new int[][]{{0, 1}, {1, 0}});
    log.debug("{}", s);
    var s1 = LC.shortestPathBinaryMatrix(new int[][]{{0, 0, 0}, {1, 1, 0}, {1, 1, 0}});
    log.debug("{}", s1);
  }

  /**
   * 第[0, 0] 就set成1
   * 然後開始BFS, 如果grid[i][j] == 0 才會繼續bfs往下走
   * 最後檢查 grid[n - 1][n - 1] == 0 -> 0 等於沒走到所以回傳 -1
   * */
  public int shortestPathBinaryMatrix(int[][] grid) {
    int n = grid.length;
    int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {-1, -1}, {-1, 1}, {1, -1}};
    if (grid[n - 1][n - 1] == 1) return -1;
    if (grid[0][0] == 1) return -1;
    Queue<int[]> bfsVisited = new LinkedList<>();
    bfsVisited.offer(new int[]{0, 0});
    grid[0][0] = 1;
    while (!bfsVisited.isEmpty()) {
      int[] v = bfsVisited.poll();
      for (int[] m : dirs) {
        int mx = v[0] + m[0];
        int my = v[1] + m[1];
        if (mx >= 0 && my >= 0 && mx < n && my < n && grid[mx][my] == 0) {
          grid[mx][my] = grid[v[0]][v[1]] + 1;
          bfsVisited.offer(new int[]{mx, my});
        }
      }
    }
    return grid[n - 1][n - 1] == 0 ? -1 : grid[n - 1][n - 1];
  }
}
