package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class LC877StoneGame extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC877StoneGame();
    }

    /**
     * https://leetcode.com/problems/stone-game/solutions/154610/dp-or-just-return-true/
     * 這題太神了....
     * 因為 石頭堆是偶數, 每一堆都是奇數
     * 所以 alice 可以選擇 只選 even index or odd index 石頭堆
     * alice 只選 even index -> alice 先選 0, bob 只能選 1 or n-1 都是奇數, 下一次 alice 還是可以繼續選 偶數
     * alice 只選 odd index -> alice 先選 n-1, bob 只能選 0 or n-2 都是奇數, 下一次 alice 還是可以繼續選 奇數
     * <p>
     * 所以只要看 sum(odd) / sum(even) 哪個大, alice 就選那個就必勝
     * 只要 alice 永遠都贏 -> only return true
     * <p>
     * 但這是因為 有偶數 石頭堆的情境, 如果 是奇數石頭堆 那就只能 DP 去解
     * dp[i][j] 記載 i to j , alice 能拿到最大的
     * dp[i][j] = Math.max(
     * 1. p[i] + dfs(i + 1, j, p, dp),  假設 alice 拿 p[i], 剩下 i+1/j 去 dfs
     * 2. p[j] + dfs(i, j - 1, p, dp));,  假設 alice 拿 p[j], 剩下 i/j-1 去 dfs
     * 看 dfs(0, n - 1, piles, dp); 回來的值是否最大
     */
    public boolean stoneGame(int[] piles) {
        return true;
    }

    public boolean stoneGameDP(int[] piles) {
        int n = piles.length;
        int[][] dp = new int[501][501];
        int alice = dfs(0, n - 1, piles, dp);
        int sum = 0;
        for (int p : piles) sum += p;
        return alice > sum - alice;
    }


    int dfs(int i, int j, int[] p, int[][] dp) {
        if (i == j) return p[i];
        if (dp[i][j] > 0) return dp[i][j];
        dp[i][j] = Math.max(p[i] + dfs(i + 1, j, p, dp), p[j] + dfs(i, j - 1, p, dp));
        return dp[i][j];
    }

}
