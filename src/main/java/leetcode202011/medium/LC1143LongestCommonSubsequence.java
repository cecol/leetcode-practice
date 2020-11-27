package leetcode202011.medium;

import leetcode20200921to20201031.BasicTemplate;

import java.util.Arrays;

public class LC1143LongestCommonSubsequence extends BasicTemplate {
  public static void main(String[] args) {
    var LC = new LC1143LongestCommonSubsequence();
    var s = LC.longestCommonSubsequence("abcde", "ace");
    var s1 = LC.longestCommonSubsequence("bl", "yby");
    var s3 = LC.longestCommonSubsequence("bsbininm", "jmjkbkjkv");
    var s4 = LC.longestCommonSubsequence("psnw", "vozsh");
  }

  public int longestCommonSubsequence(String text1, String text2) {
    if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) return 0;
    int m = text1.length();
    int n = text2.length();
    int[][] dp = new int[m][n];
    for (int i = 0; i < dp.length; i++)
      for (int j = 0; j < dp[0].length; j++) {
        boolean s = text1.charAt(i) == text2.charAt(j);
        if (i == 0) {
          if (s) dp[i][j] = 1;
          else {
            if (j == 0) dp[i][j] = 0;
            else dp[i][j] = dp[i][j - 1];
          }
        } else {
          if (j == 0) {
            if (s) dp[i][j] = 1;
            else dp[i][j] = dp[i - 1][j];
          } else {
            if (s) dp[i][j] = dp[i][j - 1] + 1;
            else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
          }
        }
      }
    log.debug("dp result");
    logIntArray(dp);
    return dp[m - 1][n - 1];
  }
}
