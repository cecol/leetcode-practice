package leetcode202103.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class LC1335MinimumDifficultyOfAJobSchedule extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1335MinimumDifficultyOfAJobSchedule();
    }

    /**
     * https://leetcode.com/problems/minimum-difficulty-of-a-job-schedule/discuss/963963/Java-Top-down-and-bottom-up-DP-monotonic-stack-time-O(nd)-space-O(nd)-with-detailed-explanation
     * 這題真難 沒有想出dp pattern
     * n 個 job, d天做完, 1天至少做一個, d > n 就沒有辦法滿足問題
     * 所以簡化來看就是 jobDifficulty array 切成 d 份的 sub-arrays
     * 比如說 jobDifficulty = [1,2,3,4], d = 2 => 就可以排成
     * [1] + [2,3,4] or [1,2] + [3,4] or [1,2,3] + [4] 這三種組合, 然後找出 minimum jobDifficulty 組合
     * bottom up的方式我對於initial case沒有看很懂
     * 後來看top down比較理解
     * dp[i][j] 就是第i天切到j-th job的最佳狀態
     * 所以top down 就是由 dp[d day][n jobs](也就是最終答案) 下去分解
     * dp[i][j] 就是由 dp[i-1][ k從j到i-2 ] 之間挑出min值
     * -> int maxJobDiffFromK2J = jobDifficulty[j]; 就是j-th job往前看, k持續--, 但只保留 max job difficult -> 代表當前i天的最大difficult
     * -> 所以可以知道 dp[i][j] 就是由 dp[i-1][到某一k-th job] + (k to j max job 因為第i天做 k to j job, 只是只記下max job)
     * -> 因此 dp[i][j] = Math.min(dp[i][j], maxJobDiffFromK2J + topDown(dp, maxSoFar, jobDifficulty, i - 1, k));
     * else if (i == 1) return maxSoFar[j]; -> maxSoFar 就是記載到 jth 以來最大的 job difficulty
     * 很直觀 -> 因為 i == 1 代表第一天, 第一天做到底幾個job, 第一天的最大difficult 自然就是 maxSoFar[j]
     * 細節解釋
     * 1. if (i > j + 1) return -1; -> 第d天就至少還要確保 j jobs 可以至少每天分到1個job
     * -> 因為topDown(dp, maxSoFar, jobDifficulty, d, n - 1); 帶下來的是d, 跟 n-1 -> n個job 但因為array offset所以-1, 所以j是offset,要+1才是真正要分的jobs數目,
     * 2. else if (i == 1) return maxSoFar[j]; 第一天做到j-th jobs 自然就是maxSoFar[j]; 到目前為止最大的 difficulty -> 因為沒有第0天讓第1天來各種組合累加
     * 3. int maxJobDiffFromK2J = jobDifficulty[j]; 與 for (int k = j - 1; k >= i - 2; k--) {
     * -> 因為topDown是第i天, 做到j-th job -> 所以子問題 i-1 day的可能性就是有 j-1 開始往後倒推, 也因此倒推過程是 k--, 每k-- 就算一下目前 k 到 j之間的 maxJobDiffFromK2J
     * -> k >= i - 2 , 第i-1天前面是i-2天, i-2天每天也至少要有一個工作, 所以k倒推的過程也只能倒推至 k >= i-2
     * -> 其實第i天能倒推的jobs數應該是第i個, 所以 i-1 day也只能倒推倒 k to i-1 th job, 但因為帶下來的 i 是day, 不是offset, k 是jobDifficulty 的offset, 所以
     * -> i-1 是 第i-1個job, 但是實際上要多-1 才可以轉換成 是jobDifficulty offset
     * -> 關鍵在於 topDown(Integer[][] dp, int[] maxSoFar, int[] jobDifficulty, int i, int j) 中 int i 是天數, int j 是 jobDifficulty offset
     * -> 所以k 要帶下去的時候要多-1 才會是 offset, 畢竟k就是用來算 i-1 day的 job offset -> 所以 k to i-1 th job 會變成 k >= i - 2
     * 4. maxJobDiffFromK2J = Math.max(maxJobDiffFromK2J, jobDifficulty[k + 1]); -> 因為k是屬於i-1 day的範圍, k+1才是i day範圍
     * -> 而maxJobDiffFromK2J是記載i day的 max, 不是 i-1 day
     */
    public int minDifficulty(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        if (n < d) return -1;
        Integer[][] dp = new Integer[d + 1][n];
        int[] maxSoFar = new int[n];
        maxSoFar[0] = jobDifficulty[0];
        for (int i = 1; i < n; i++) maxSoFar[i] = Math.max(maxSoFar[i - 1], jobDifficulty[i]);
        return topDown(dp, maxSoFar, jobDifficulty, d, n - 1);
    }

    private int topDown(Integer[][] dp, int[] maxSoFar, int[] jobDifficulty, int i, int j) {
        if (i > j + 1) return -1;
        else if (i == 1) return maxSoFar[j];
        else if (dp[i][j] != null) return dp[i][j];
        dp[i][j] = Integer.MAX_VALUE;
        int maxJobDiffFromK2J = jobDifficulty[j];
        for (int k = j - 1; k >= i - 2; k--) {
            maxJobDiffFromK2J = Math.max(maxJobDiffFromK2J, jobDifficulty[k + 1]);
            dp[i][j] = Math.min(dp[i][j], maxJobDiffFromK2J + topDown(dp, maxSoFar, jobDifficulty, i - 1, k));
        }
        return dp[i][j];
    }
}
