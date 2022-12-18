package leetcode202212.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.PriorityQueue;

public class LC805SplitArrayWithSameAverage extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC805SplitArrayWithSameAverage();
    }

    /**
     * https://github.com/wisdompeak/LeetCode/tree/master/Dynamic_Programming/805.Split-Array-With-Same-Average
     * 分群問題都是 NP, DFS 下去找只能優化剪枝看能不能通過 test case
     * 這題有個數學意義 就是分群的 avg 要一樣
     * total = 全部總和, n = 全部個數
     * sum = 找到的合法子集總和, num = 找到的合法子集個數
     * 因為分群 avg 一樣  所以如果存在合法子集的話
     * total/n == sum/num -> total * num == sum * n
     * 所以我們目標是要找到 符合這個公式的 子集
     * DP 要是要找這個 total * num == sum * n 存在
     * 所以 boolean[num][sum] 就是在找這個 true 且剛好 total * num == sum * n
     * <p>
     * 所以針對當前 nums[i] i = 0 to nums.length
     * 累加到 curSum, 當前子集總和
     * 走過 num = totalSize / 2 + 1 to 1, 看 sum from nums[i] to curSum 區間內
     * if (dp[num - 1][sum - nums[i]]) { -> 還沒拿 nums[i] 是 true
     * - 那拿了 nums[i] 也會是 true -> dp[num][sum] = true
     * - 看這個是不是我們要找的條件式 - if (num != totalSize && total * num == sum * totalSize ) return true;
     */
    public boolean splitArraySameAverage(int[] nums) {
        int total = 0;
        for (int n : nums) total += n;
        int totalSize = nums.length;
        if (totalSize == 1) return false;
        boolean[][] dp = new boolean[nums.length + 1][total + 1];
        dp[0][0] = true;

        int curSum = 0;

        for (int i = 0; i < nums.length; i++) {
            curSum += nums[i];
            for (int num = totalSize / 2 + 1; num >= 1; num--) {
                for (int sum = curSum; sum >= nums[i]; sum--) {
                    if (dp[num - 1][sum - nums[i]]) {
                        dp[num][sum] = true;
                        if (num != totalSize && total * num == sum * totalSize) return true;
                    }
                }
            }

        }
        return false;
    }
}
