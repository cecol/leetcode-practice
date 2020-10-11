package leetcode202009.hard;

import leetcode202009.BasicTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC72EditDistance extends BasicTemplate {

  public static void main(String[] args) {
    var LC = new LC72EditDistance();
    var r = LC.minDistance("horse", "ros");
  }

  public int minDistance(String word1, String word2) {
    int[][] dp = new int[word1.length() + 1][word2.length() + 1];
    dp[0][0] = 0;
    for (int i = 1; i < dp.length; i++) dp[i][0] = dp[i - 1][0] + 1;
    for (int i = 1; i < dp[0].length; i++) dp[0][i] = dp[0][i - 1] + 1;
    for (int i = 1; i < dp.length; i++) {
      for (int j = 1; j < dp[i].length; j++) {
        if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
          dp[i][j] = dp[i - 1][j - 1];
        } else {
          dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1] + 1);
        }
      }
    }
    return dp[dp.length - 1][dp[0].length - 1];
  }
}
