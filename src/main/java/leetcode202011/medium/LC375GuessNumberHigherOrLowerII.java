package leetcode202011.medium;

import leetcode20200921to20201031.BasicTemplate;

public class LC375GuessNumberHigherOrLowerII extends BasicTemplate {
  public static void main(String[] args) {
    var LC = new LC375GuessNumberHigherOrLowerII();
    var s = LC.getMoneyAmount(10);
  }

  public int getMoneyAmount(int n) {
    if (n == 2) return 1;
    if (n == 1) return 0;
    int[][] dp = new int[n + 1][n + 1];
    int res = minAmount(dp, 1, n);
    log.debug("res: {}", res);
    return res;
  }

  public int minAmount(int[][] dp, int b, int e) {
    if (b > e) return 0;
    if (dp[b][e] > 0) return dp[b][e];
    if (e - b + 1 == 2) return b;
    if (e - b + 1 == 3) return (e + b) / 2;
    int res = Integer.MAX_VALUE;
    for (int i = b; i <= e; i++) {
      res = Math.min(res,
          Math.max(
              minAmount(dp, b, i - 1),
              minAmount(dp, i + 1, e)
          ) + i
      );
    }
    dp[b][e] = res;
    return res;
  }
}
