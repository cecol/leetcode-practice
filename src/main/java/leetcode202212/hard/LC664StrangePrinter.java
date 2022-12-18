package leetcode202212.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.PriorityQueue;

public class LC664StrangePrinter extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC664StrangePrinter();
    }

    /**
     * https://leetcode.com/problems/strange-printer/solutions/179642/java-beats-100-as-of-oct-2019/
     * 這種區間最佳化題目還是 top down + dfs 來解比較容易理解
     * 跟 LC546RemoveBoxes 一樣概念
     * 1. int[][] dp = new int[n][n]; dp[i][j] 就是 s[i to j] 最少 operations
     * 2. 進入 dfs
     * - 先算低消 如果 i to j 前面幾個都是同字元,
     * - i++ 直到不一樣 ->  for (; i + 1 <= j && s.charAt(i + 1) == s.charAt(i); i++) ;
     * -     res = 1 + dfs(s, i + 1, j, dp);
     * - 接著算其他可能性 m from i+1 to j, 如果 s.charAt(m) == s.charAt(i), 那麼就是
     * - i + 1 to m - 1 先另外處理, 然後 s(i) 跟 右邊 m to j 一併下去處理
     * - 所以會是 dfs(s, i + 1, m - 1, dp) + dfs(s, m, j, dp)
     * - 這邊會想說怎不是 1 + dfs(s, i + 1, m - 1, dp) + dfs(s, m, j, dp), 先把最左邊 i 消除了 剩下分左右？
     * - 其實這個做左邊的 s(i) 是給 dfs(s, m, j, dp) 一併處理了,
     * - 只是要加上先處理中間 dfs(s, i + 1, m - 1, dp) 這些跟 s(i) 不同的字元成本
     * - 所以概念是 i to m 先填上 s(i), m to j 再去優化 最後再補上 dfs(s, i + 1, m - 1, dp) 所需要的 operations
     * - Ex:  a[bcd]abc -> 切成 [bcd] 配上 [a...abc], 可以視為 [a...abc] 中間的 [a..a] 都先填上 [a'aaa'a] + [bc]
     * - 'aaa' 最後要補回 [bcd], 所以成本是
     * - dfs(s, i + 1, m - 1, dp) for [bcd]
     * -  dfs(s, m, j, dp) for [a...abc]
     * */
    public int strangePrinter(String s) {
        if (s == null || s.length() == 0) return 0;
        int n = s.length();
        int[][] dp = new int[n][n];
        return dfs(s, 0, n - 1, dp);
    }

    int dfs(String s, int i, int j, int[][] dp) {
        if (i > j) return 0;
        if (dp[i][j] > 0) return dp[i][j];
        for (; i + 1 <= j && s.charAt(i + 1) == s.charAt(i); i++) ;
        int res = 1 + dfs(s, i + 1, j, dp);
        for (int m = i + 1; m <= j; m++) {
            if (s.charAt(m) == s.charAt(i))
                res = Math.min(res, dfs(s, i + 1, m - 1, dp) + dfs(s, m, j, dp));
        }
        dp[i][j] = res;
        return res;
    }

}
