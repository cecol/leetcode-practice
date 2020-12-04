package leetcode202011.medium;

import leetcode20200921to20201031.BasicTemplate;

public class LC96UniqueBinarySearchTrees extends BasicTemplate {
    public static void main(String[] args) {
        var LC = new LC96UniqueBinarySearchTrees();
//    var s = LC.numTrees(3);
        var s2 = LC.numTrees(3);
    }

    public int numTrees(int n) {
        int[][] dp = new int[n][n];
        var res = ct("", 0, n - 1, dp);
        log.debug("{}", res);
        return res;
    }

    public int ct(String tab, int b, int e, int[][] dp) {
        log.debug("{}b:{},e:{}", tab, b, e);
        if (e - b == 1) return 2;
        if (e == b) return 1;
        if (dp[b][e] != 0) return dp[b][e];

        int res = 0;
        for (int i = b; i <= e; i++) {
            log.debug("{}i:{}", tab, i);
            if (b == i) res += ct(tab + "\t", i + 1, e, dp);
            else if (e == i) res += ct(tab + "\t", b, i - 1, dp);
            else res += ct(tab + "\t", b, i - 1, dp) * ct(tab + "\t", i + 1, e, dp);
        }
        dp[b][e] = res;
        return res;
    }
}
