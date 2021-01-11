package leetcode202101.medium;

import leetcode20200921to20201031.BasicTemplate;

public class LC516LongestPalindromicSubsequence extends BasicTemplate {
    public static void main(String[] args) {
        var LC = new LC516LongestPalindromicSubsequence();
        var s = LC.longestPalindromeSubseq("");
    }

    /**
     * https://leetcode.com/problems/longest-palindromic-subsequence/discuss/99101/Straight-forward-Java-DP-solution
     * dp[i][j]: subsequence's length of substring(i, j)
     * Initialization: dp[i][i] = 1, 自己就成為長度為1的Palindrome
     * why dp[i+1][j-1]? ex: 1 to 10區間的longest
     * 1 to 10 -> if s.charAt(1) == s.charAt(10) 那麼就是1 to 10的子問題longest再加上2字元(來自1&10)
     * 子問題就是 2 to 9, 2 to 9 就是 1+1與10-1衍生出來的, 所以才有 dp[i+1][j-1] => 就是指向該區間的子問題substring!!
     * 如果s.charAt(1) != s.charAt(10) 那麼最長的一定部會出現 1 to 10,
     * 只會出現在
     * 2 to 10 => i+1 to j
     * or
     * 1 to 9 => i to j-1
     * => Max of dp[i+1][j] or dp[i][j-1]
     * <p>
     * 我用top down 來解
     */
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        return lp(s, dp, 0, n - 1);
    }

    private int lp(String s, int[][] dp, int i, int j) {
        if (dp[i][j] != 0) return dp[i][j];
        if (i > j) return 0;
        if (i == j) {
            dp[i][j] = 1;
            return 1;
        }
        if (s.charAt(i) == s.charAt(j)) dp[i][j] = lp(s, dp, i + 1, j - 1) + 2;
        else dp[i][j] = Math.max(lp(s, dp, i + 1, j), lp(s, dp, i, j - 1));
        return dp[i][j];
    }
}
