package leetcode202011.medium;

import leetcode20200921to20201031.BasicTemplate;

public class LC96UniqueBinarySearchTrees extends BasicTemplate {
    public static void main(String[] args) {
        var LC = new LC96UniqueBinarySearchTrees();
//    var s = LC.numTrees(3);
        var s2 = LC.numTrees(3);
    }

    public int numTrees(int n) {
        if(n == 1) return 1;
        int[][] dp = new int[n][n];
        return allB(dp,0,n-1);
    }

    private int allB(int[][] dp, int l, int r) {
        if(l>=r) return 1;
        if(dp[l][r] != 0) return dp[l][r];
        int all = 0;
        for(int i = l; i<=r;i++) {
            int lb = allB(dp,l,i-1);
            int rb = allB(dp,i+1,r);
            all += lb*rb;
        }
        dp[l][r] = all;
        return all;
    }
}
