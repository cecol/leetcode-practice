package leetcode202212.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.PriorityQueue;

public class LC132PalindromePartitioningII extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC132PalindromePartitioningII();
        LC.minCut("aab");
    }

    /**
     * https://github.com/wisdompeak/LeetCode/tree/master/Dynamic_Programming/132.Palindrome-Partitioning-II
     * 這題其實要拆成兩個 DP,
     * 1. String s(i to j) 是否為 Palindrome ?
     * boolean[][] isPalDP, isPalDP[i][j] 只看 i <= j 的 case (這樣區間才有 substring)
     * if s(i) == s(j), isPalDP[i][j] = isPalDP[i+1][j-1]
     * 如果 i,j 字元相同, 就是繼承 dp[i+1][j-1] 結果(如果同就同)
     * if (i == j || i + 1 == j) -> i/j 同字元 或者 i/j 相鄰
     *
     * 建立好 i - j Palindrome
     * 就是下去看 dp[i = 1 to n]
     * dp[0] = 1, 不是 0, 因為至少切出 0
     * int j from 0 to i, 看 j 到 i 區間,
     * 如果 isPalDP[j][i] == true
     * - 如果 j == 0 整個需間都是 1
     * -   (不是 0, 不是 j to i 就完全不用切, 至少切一刀切出 [j to i] 這一段保證 Palindrome)
     * - else -> dp[i] = Math.min(dp[i], dp[j - 1] + 1)
     * - dp[j - 1] + 1 就是 dp[0 to j] 的結果 再加上 j to i 這一刀
     * */
    public int minCut(String s) {
        int n = s.length();
        boolean[][] isPalDP = new boolean[n][n];
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                if (s.charAt(i) == s.charAt(j)) {
                    if (i == j || i + 1 == j) isPalDP[i][j] = true;
                    else isPalDP[i][j] = isPalDP[i + 1][j - 1];
                }
            }
        }

        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE / 2);
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (isPalDP[j][i]) {
                    if (j == 0) dp[i] = 1;
                    else dp[i] = Math.min(dp[i], dp[j - 1] + 1);
                }
            }
        }
        return dp[n - 1] - 1;
    }
}
