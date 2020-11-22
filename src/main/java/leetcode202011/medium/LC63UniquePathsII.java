package leetcode202011.medium;

import leetcode20200921to20201031.BasicTemplate;

import java.util.Arrays;
import java.util.stream.IntStream;

public class LC63UniquePathsII extends BasicTemplate {
  public static void main(String[] args) {
    var LC = new LC63UniquePathsII();
    var s = LC.uniquePathsWithObstacles(new int[][]{{0, 1}, {0, 0}});
    var s2 = LC.uniquePathsWithObstacles(new int[][]{{0, 0}, {1, 1}, {0, 0}});
  }

  public int uniquePathsWithObstacles(int[][] obstacleGrid) {
    int m = obstacleGrid.length;
    int n = obstacleGrid[0].length;
    if (obstacleGrid[m - 1][n - 1] == 1) return 0;
    if (obstacleGrid[0][0] == 1) return 0;
    obstacleGrid[0][0] = 1;
    for (int i = 1; i < n; i++) {
      if (obstacleGrid[0][i] == 1) {
        obstacleGrid[0][i] = -1;
      } else {
        if (obstacleGrid[0][i - 1] != -1) obstacleGrid[0][i] = obstacleGrid[0][i - 1];
      }
    }
    for (int i = 1; i < m; i++) {
      if (obstacleGrid[i][0] == 1) {
        obstacleGrid[i][0] = -1;
      } else {
        if (obstacleGrid[i - 1][0] != -1) obstacleGrid[i][0] = obstacleGrid[i - 1][0];
      }
    }
    for (int i = 1; i < m; i++)
      for (int j = 1; j < n; j++) {
        if (obstacleGrid[i][j] == 1) {
          obstacleGrid[i][j] = -1;
        } else {
          if (obstacleGrid[i][j - 1] != -1) obstacleGrid[i][j] += obstacleGrid[i][j - 1];
          if (obstacleGrid[i - 1][j] != -1) obstacleGrid[i][j] += obstacleGrid[i - 1][j];
        }
      }
    log.debug("result");
    logIntArray(obstacleGrid);
    return obstacleGrid[m - 1][n - 1];
  }
}
