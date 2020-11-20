package leetcode202011.medium;

import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.List;

public class LC1223DiceRollSimulation extends BasicTemplate {
  public static void main(String[] args) {
    var LC = new LC1223DiceRollSimulation();
    var s = LC.dieSimulator(1, new int[]{});
  }

  public int dieSimulator(int n, int[] rollMax) {
    int mod = ((int) Math.pow(10, 9)) + 7;
    int[][][] dp = new int[n][6][16];
    for (int i = 0; i < 6; i++) dp[0][i][1] = 1;
    for (int i = 1; i < n; i++) // i-th roll
      for (int currentNum = 0; currentNum < 6; currentNum++) // i-th roll number
        for (int preNum = 0; preNum < 6; preNum++) { // (i-1)th roll number
          if (currentNum == preNum) {
            for (int consecutiveNum = 2; consecutiveNum <= rollMax[currentNum]; consecutiveNum++)
              dp[i][currentNum][consecutiveNum] =
                  (dp[i][currentNum][consecutiveNum] + dp[i - 1][currentNum][consecutiveNum - 1]) % mod;
          } else {
            for (int consecutiveNum = 1; consecutiveNum <= rollMax[preNum]; consecutiveNum++)
              dp[i][currentNum][1] =
                  (dp[i][currentNum][1] + dp[i - 1][preNum][consecutiveNum]) % mod;
          }
        }

    int sum = 0;
    for (int rollNum = 0; rollNum < 6; rollNum++)
      for (int consecutiveNum = 1; consecutiveNum <= rollMax[rollNum]; consecutiveNum++)
        sum = (sum + dp[n - 1][rollNum][consecutiveNum]) % mod;
    return sum;
  }
}
