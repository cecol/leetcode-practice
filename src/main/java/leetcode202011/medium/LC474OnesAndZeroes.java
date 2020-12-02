package leetcode202011.medium;

import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.List;

public class LC474OnesAndZeroes extends BasicTemplate {
    public static void main(String[] args) {
        var LC = new LC474OnesAndZeroes();
        var s = LC.findMaxForm2(new String[]{"10", "0001", "111001", "1", "0"},
                5,
                3);
    }

    /**
     * https://kingsfish.github.io/2017/07/23/Leetcode-474-Ones-and-Zeros/
     * dp[i][j][k]
     * i => size of strs
     * j => current number of 0, j max = m + 1
     * k => current  number of 1, k max = n + 1
     * if str[i]'s number of 0 < j and number of 1 < k
     * dp[i][j][k] = Math.max(
     * dp[i-1][j][k], //do not take str[i]
     * dp[i][j-count0][k - count1] // take str[i]
     * )
     * else
     * dp[i][j][k] = dp[i-1][j][k]; //unable to take str[i]
     *
     * https://github.com/azl397985856/leetcode/blob/master/problems/474.ones-and-zeros-en.md
     */
    public int findMaxForm(String[] strs, int m, int n) {
        int[][][] dp = new int[strs.length][m + 1][n + 1];
        int max = 0;
        int c0 = (int) strs[0].chars().filter(c -> c == '0').count();
        int c1 = (int) strs[0].chars().filter(c -> c == '1').count();
        if (c0 <= m && c1 <= n) {
            dp[0][c0][c1] = 1;
            max = 1;
        }
        for (int i = 1; i < dp.length; i++) {
            c0 = (int) strs[i].chars().filter(c -> c == '0').count();
            c1 = (int) strs[i].chars().filter(c -> c == '1').count();
            for (int j = 0; j < dp[i].length; j++)
                for (int k = 0; k < dp[i][j].length; k++) {
                    if (c0 <= j && c1 <= k) {
                        dp[i][j][k] = Math.max(dp[i - 1][j][k], dp[i - 1][j - c0][k - c1] + 1);
                    } else dp[i][j][k] = dp[i - 1][j][k];
                    max = Math.max(max, dp[i][j][k]);
                }
        }
        log.debug("dp: {}", dp);
        log.debug("max: {}", max);
        return max;
    }

    /**
     *  Because we only consider dp[i][j][k] and dp[i - 1][j][k]
     *      we can reduce dp[i] dimension
     *  WARN: for dp[][] case, you have to calculate j from m -> count0
     *                                               k from n -> count1
     *        otherwise you would unintentionally add duplicate size
     */
    public int findMaxForm2(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        int max = 0;
        int c0 = (int) strs[0].chars().filter(c -> c == '0').count();
        int c1 = (int) strs[0].chars().filter(c -> c == '1').count();
        if (c0 <= m && c1 <= n) {
            dp[c0][c1] = 1;
            max = 1;
        }
        for (int i = 1; i < strs.length; i++) {
            c0 = (int) strs[i].chars().filter(c -> c == '0').count();
            c1 = (int) strs[i].chars().filter(c -> c == '1').count();
            for (int j = m; j >= c0; j--)
                for (int k = n; k >= c1; k--) {
                    dp[j][k] = Math.max(dp[j][k], dp[j - c0][k - c1] + 1);
                    max = Math.max(max, dp[j][k]);
                }
        }
        log.debug("dp: {}", dp);
        log.debug("max: {}", max);
        return max;
    }
}
