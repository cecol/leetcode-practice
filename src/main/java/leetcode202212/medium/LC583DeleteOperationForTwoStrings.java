package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

public class LC583DeleteOperationForTwoStrings extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC583DeleteOperationForTwoStrings();
    }

    /**
     * https://github.com/wisdompeak/LeetCode/tree/master/Dynamic_Programming/583.Delete-Operation-for-Two-Strings
     * 跟 LC72EditDistance 很類似
     * 只是這題只有刪除來達成 2 字串相同
     * 因為只能刪除 所以當 w1(i) != w2(j) 時候
     * 我們選擇刪一個看看再配上前面的 case 成本, 擇一最小來疊加
     * 比如說我們刪 w1(i) , 所以是 dp[i-1][j] case + 1
     * 比如說我們刪 w2(j) , 所以是 dp[i][j-1] case + 1
     * 兩者擇一就好
     * 其實也是可以
     * dp[i][j] = Math.min(dp[i - 1][j - 1] + 2, Math.min(dp[i][j - 1], dp[i - 1][j]) + 1);
     * 就是乾脆 i/j 都刪除 配上 dp[i-1][j-1] + 2 (2 是刪除 i/j 的成本)
     * 再去跟 dp[i][j - 1], dp[i - 1][j] case 比較
     * 這樣也是對的, 只是比較多餘, 多比較一次 但語意上是對的
     */
    public int minDistance(String w1, String w2) {
        int m = w1.length(), n = w2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) dp[i][0] = dp[i - 1][0] + 1;
        for (int j = 1; j <= n; j++) dp[0][j] = dp[0][j - 1] + 1;
        for (int i = 1; i <= m; i++)
            for (int j = 1; j <= n; j++) {
                if (w1.charAt(i - 1) == w2.charAt(j - 1)) dp[i][j] = dp[i - 1][j - 1];
                else dp[i][j] = Math.min(dp[i - 1][j - 1] + 2, Math.min(dp[i][j - 1], dp[i - 1][j]) + 1);
            }
        return dp[m][n];
    }
}
