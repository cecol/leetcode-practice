package leetcode202009.medium;

import leetcode202009.BasicTemplate;

import java.util.Arrays;

public class LC518CoinChange2 extends BasicTemplate {

  public static void main(String[] args) {
    var LC = new LC518CoinChange2();
    var r = LC.change(5, new int[]{1, 2, 5});
  }

  public int change(int amount, int[] coins) {
    int[][] x = new int[coins.length][amount + 1];
    Arrays.sort(coins);
    log.debug("{}", coins);
    for (int i = 0; i < coins.length; i++) x[i][0] = 1;
    for (int i = 0; i < x.length; i++) {
      for (int j = 1; j < x[i].length; j++) {
        if (j >= coins[i]) {
          int uMax = i > 0 ? x[i - 1][j] : 0;
          int pMax = x[i][j - coins[i]];
          log.debug("{}, {}, {}, {}");
          x[i][j] = uMax + pMax;
        }
      }
    }
    for (int[] p : x) log.debug("{}", p);
    return x[coins.length - 1][amount];
  }
}
