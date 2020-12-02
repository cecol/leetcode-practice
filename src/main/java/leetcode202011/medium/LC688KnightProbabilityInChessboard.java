package leetcode202011.medium;

import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.List;

public class LC688KnightProbabilityInChessboard extends BasicTemplate {
  public static void main(String[] args) {
    var LC = new LC688KnightProbabilityInChessboard();
    var s2 = LC.knightProbability(3, 2, 0, 0);
    var s = LC.knightProbability(8, 30, 6, 4);
  }

  /**
   * https://leetcode.com/problems/knight-probability-in-chessboard/discuss/162722/Simple-Java-DP-solution-with-explanation
   * int[k][i][j]
   */
  public double knightProbability(int N, int K, int r, int c) {
    int[][] directions = new int[][]{{1, 2}, {1, -2}, {-1, 2}, {-1, -2}, {2, 1}, {2, -1}, {-2, 1}, {-2, -1}};
    double[][][] dp = new double[K + 1][N][N];
    dp[0][r][c] = 1d;
    double s = 0;
    for (int p = 1; p <= K; p++)
      for (int i = 0; i < N; i++)
        for (int j = 0; j < N; j++) {
          for (int[] dir : directions) {
            int m1 = i - dir[0];
            int m2 = j - dir[1];
            if (m1 >= 0 && m1 < N && m2 >= 0 && m2 < N) {
              dp[p][i][j] += (dp[p - 1][m1][m2] / 8d);
            }
          }
          if (i == K) s += dp[i][i][j];
        }
    log.debug("s: {}", s);
    return s;
  }
}
