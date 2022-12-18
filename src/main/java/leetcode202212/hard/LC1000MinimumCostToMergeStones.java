package leetcode202212.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.PriorityQueue;

public class LC1000MinimumCostToMergeStones extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1000MinimumCostToMergeStones();
    }

    /**
     * https://leetcode.com/problems/minimum-cost-to-merge-stones/solutions/1753152/java-recursive-solution/
     * 我原本以為是找出區間 merge 最小然後剩下k 個再去 merge 來找出答案
     * 但這是 greedy
     * https://leetcode.com/problems/minimum-cost-to-merge-stones/solutions/247567/java-c-python-dp/comments/247086
     * 這邊有解釋當出現重複時候這是錯誤答案
     *
     * 這邊有個訣竅是 先算 prefixSum 屆時要算區間總和會快很多
     *
     * Why mid jump at step K - 1
     * - A: We can merge K piles into one pile,
     * - we can't merge K + 1 piles into one pile.
     * - We can merge K + K - 1 piles into on pile,
     * - We can merge K + (K - 1) * steps piles into one pile.
     *
     * 所以針對 i/j 區間, 都是跳 k-1 去分左右下去 merge看看結果
     * */
    int[][] dp;
    int[] prefixSum;

    public int mergeStones(int[] stones, int k) {
        int n = stones.length;
        if ((n - 1) % (k - 1) != 0) return -1;
        dp = new int[n][n];
        for (int i = 0; i < n; i++) Arrays.fill(dp[i], -1);
        prefixSum = new int[n + 1];
        for (int i = 1; i <= n; i++) prefixSum[i] = prefixSum[i - 1] + stones[i - 1];
        return dfs(0, n - 1, k);
    }

    int dfs(int i, int j, int k) {
        if (dp[i][j] > 0) return dp[i][j];
        if (j - i + 1 < k) {
            dp[i][j] = 0;
            return 0;
        }
        if (j - i + 1 == k) {
            dp[i][j] = prefixSum[j + 1] - prefixSum[i];
            return dp[i][j];
        }
        int res = Integer.MAX_VALUE;
        for (int m = 0; m + i + 1 <= j; m += k - 1) {
            res = Math.min(res, dfs(i, i + m, k) + dfs(i + m + 1, j, k));
        }
        if ((j - i) % (k - 1) == 0) res += prefixSum[j + 1] - prefixSum[i];
        dp[i][j] = res;
        return res;
    }
}
