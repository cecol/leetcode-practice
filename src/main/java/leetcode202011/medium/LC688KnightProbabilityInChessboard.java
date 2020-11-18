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

  public double knightProbability(int N, int K, int r, int c) {
    int[][] dp = new int[N][N];
    int s = 0;

    for (int[] i : dp)
      for (int j : i)
        s += j;
    return s / Math.pow(8, K);
  }

  /**
   * out of memory
   */
  public double knightProbabilityFail(int N, int K, int r, int c) {
    List<int[]> steps = new ArrayList<>();
    steps.add(new int[]{r, c});
    for (int i = 1; i <= K; i++) {
      List<int[]> next = new ArrayList<>(steps);
      steps.clear();
      for (int[] p : next) {
        if (p[0] + 1 < N && p[1] + 2 < N) steps.add(new int[]{p[0] + 1, p[1] + 2});
        if (p[0] + 1 < N && p[1] - 2 >= 0) steps.add(new int[]{p[0] + 1, p[1] - 2});
        if (p[0] - 1 >= 0 && p[1] + 2 < N) steps.add(new int[]{p[0] - 1, p[1] + 2});
        if (p[0] - 1 >= 0 && p[1] - 2 >= 0) steps.add(new int[]{p[0] - 1, p[1] - 2});
        if (p[0] + 2 < N && p[1] + 1 < N) steps.add(new int[]{p[0] + 2, p[1] + 1});
        if (p[0] + 2 < N && p[1] - 1 >= 0) steps.add(new int[]{p[0] + 2, p[1] - 1});
        if (p[0] - 2 >= 0 && p[1] + 1 < N) steps.add(new int[]{p[0] - 2, p[1] + 1});
        if (p[0] - 2 >= 0 && p[1] - 1 >= 0) steps.add(new int[]{p[0] - 2, p[1] - 1});
      }
    }
    double p = steps.size() / Math.pow(8, K);
    log.info("p: {}", p);
    for (int[] i : steps) log.info("steps: {}", i);
    return p;
  }
}
