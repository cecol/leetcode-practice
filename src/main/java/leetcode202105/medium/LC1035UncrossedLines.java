package leetcode202105.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

public class LC1035UncrossedLines extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1035UncrossedLines();
    }

    /**
     * https://leetcode.com/problems/uncrossed-lines/discuss/282842/JavaC%2B%2BPython-DP-The-Longest-Common-Subsequence
     * 這題是經典的 The Longest Common Subsequence
     * 我其實很接近答案了, 只是在 dp[i][j] = dp[i - 1][j - 1] + 1;
     * 寫成 dp[i][j] = dp[i - 1][j] + 1;
     * 我忘記既然 A[i - 1] == B[j - 1] 要拿來計算, 那麼要用 j-1 的情況, 因為當前j 配給當前 i
     * */
    public int maxUncrossedLines(int[] A, int[] B) {
        int m = A.length, n = B.length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (A[i - 1] == B[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }
}
