package leetcode202011.medium;

import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.List;

public class LC474OnesAndZeroes extends BasicTemplate {
    public static void main(String[] args) {
        var LC = new LC474OnesAndZeroes();
        var s = LC.findMaxForm(new String[]{"00101011"},
                36,
                39);
    }

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
}
