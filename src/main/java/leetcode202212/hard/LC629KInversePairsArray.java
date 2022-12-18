package leetcode202212.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.PriorityQueue;

public class LC629KInversePairsArray extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC629KInversePairsArray();
    }

    /**
     * https://leetcode.com/problems/k-inverse-pairs-array/solutions/104815/java-dp-o-nk-solution/
     * 這題看起來代碼很短  但背後數學也是很深
     * 1. dp[n][k] 1 to n 排列組合 有k個 inverse pairs
     * 先想想 1 to n 排列組合, 最多有幾組 inverse pairs? -> n * (n - 1) / 2
     * 因為從大排到小 倒序, 3,2,1 -> 就有 3*2/2 = 3 組, (3/2, 3/1, 2/1)
     * 所以當 k > n * (n - 1) / 2 一定找不到
     * k == n * (n - 1) / 2 一定只有一種可能
     *
     * dp[n][k]
     * 假設把 n 放到 nth 個位置, 這個 n 貢獻現不了 inverse pairs, 所以只會有 dp[n-1][k] 來貢獻 dp[n][k]
     * 假設把 n 放到 (n-1)th 個位置, 這個 n 只貢獻一個 inverse pairs, 所以會有 dp[n-1][k-1] 來貢獻 dp[n][k]
     * 假設把 n 放到 first 個位置, 這個 n 貢獻 (n-1) 個 inverse pairs, 所以會有 dp[n-1][k-n+1] 來貢獻 dp[n][k]
     * .. 所以 dp[n][k] = dp[n-1][k]+dp[n-1][k-1]+dp[n-1][k-2]+...+dp[n-1][k+1-n+1]+dp[n-1][k-n+1]
     * https://github.com/wisdompeak/LeetCode/tree/master/Dynamic_Programming/629.K-Inverse-Pairs-Array
     * dp[i][j] = dp[i-1][j]+ { dp[i-1][j-1]+dp[i-1][j-2]+...+dp[i-1][j-i+1] }
     * 考慮 dp[i][j-1] = { dp[i-1][j-1]+dp[i-1][j-2]+... } + dp[i-1][j-i]
     * - 将dp[i][j-1]带入dp[i][j]，可以得到
     * -     dp[i][j] = dp[i-1][j]+dp[i][j-1]-dp[i-1][j-i]
     *
     * */
    public int kInversePairs(int n, int k) {
        int mod = (int) 1e9 + 7;
        if (k > n * (n - 1) / 2 || k < 0) return 0;
        if (k == 0 || k == n * (n - 1) / 2) return 1;
        long[][] dp = new long[n + 1][k + 1];
        dp[2][0] = 1;
        dp[2][1] = 1;
        for (int i = 3; i <= n; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= Math.min(k, i * (i - 1) / 2); j++) {
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
                if (j >= i) dp[i][j] -= dp[i - 1][j - i];
                dp[i][j] = (dp[i][j] + mod) % mod;
            }
        }
        return (int) dp[n][k];
    }
}
