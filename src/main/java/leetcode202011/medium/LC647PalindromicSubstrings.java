package leetcode202011.medium;

import leetcode20200921to20201031.BasicTemplate;

public class LC647PalindromicSubstrings extends BasicTemplate {
    public static void main(String[] args) {
        var LC = new LC647PalindromicSubstrings();
        var s = LC.countSubstrings("abc");
    }

    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) return 0;
        if (s.length() == 1) return 1;
        int n = s.length();
        int[][] dp = new int[n][n];
        int res = topDown(dp, 0, n - 1);
        log.debug("res: {}", res);
        return res;
    }

    public int topDown(int[][] dp, int b, int e) {
        if (b > e) return 0;
        if (b == e) return 1;
        if (dp[b][e] > 0) return dp[b][e];
        int res = 0;
        for (int i = b; i < e; i++) {
            res += (dp[b][i] + dp[i + 1][e]);
        }
        dp[b][e] = res;
        return res;
    }
}
