package leetcode202011.medium;

import leetcode20200921to20201031.BasicTemplate;

public class LC935KnightDialer extends BasicTemplate {
  public static void main(String[] args) {
    var LC = new LC935KnightDialer();
    var s1 = LC.knightDialer(2);
    var s2 = LC.knightDialer(3);
    var s3 = LC.knightDialer(4);
    var s4 = LC.knightDialer(3131);
  }

  public int knightDialer(int n) {
    if (n == 0) return 0;
    if (n == 1) return 10;
    int mod = (int) Math.pow(10, 9) + 7;
    int s = 0;
    int[][] directions = new int[][]{{1, 2}, {1, -2}, {-1, 2}, {-1, -2}, {2, 1}, {2, -1}, {-2, 1}, {-2, -1}};
    int[][] allInit = new int[][]{{0, 0}, {0, 1}, {0, 2}, {1, 0}, {1, 1}, {1, 2}, {2, 0}, {2, 1}, {2, 2}, {3, 1}};
    for (int[] init : allInit) {
      int[][][] kd = new int[n][4][3];
      kd[0][init[0]][init[1]] = 1;
      for (int i = 1; i < n; i++) {
        for (int mx = 0; mx < 4; mx++)
          for (int my = 0; my < 3; my++) {
            if (mx == 3 && my == 0) continue;
            if (mx == 3 && my == 2) continue;
            for (int[] dir : directions) {
              int preX = mx - dir[0];
              int preY = my - dir[1];
              if (preX >= 0 && preY >= 0 && preX < 4 && preY < 3) {
                kd[i][mx][my] += kd[i - 1][preX][preY];
                if (kd[i][mx][my] > mod) kd[i][mx][my] %= mod;
              }
            }
          }
      }
      for (int[] i2 : kd[n - 1])
        for (int i1 : i2) {
          s += i1;
          if (s > mod) s %= mod;
        }
    }
    log.debug("s: {}", s);
    return s;
  }
}
