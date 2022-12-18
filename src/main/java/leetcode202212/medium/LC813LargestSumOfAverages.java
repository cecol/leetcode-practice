package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

public class LC813LargestSumOfAverages extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC813LargestSumOfAverages();
        double r1 = LC.largestSumOfAverages(new int[]{1, 2, 3, 4, 5, 6, 7}, 4);
        log.debug("{}", r1);
    }

    /**
     * 初見殺, 很直觀的解了
     * 就先寫好 dfs, dfs(int[] nums, int j, int k)
     * j 就是 nums[j] 開始繼續切, 還有 k 可以切, 回傳最大 score
     * dfs 內就是
     * - int i = j to n - 1,
     * - j to i 切成一段, 算score 加上剩下的 dfs(i+1, k-1)
     * - 然後找出其中最大值回傳
     *
     * 跑一跑證實想法對的, 就配上 double[][] dp = new double[n][k + 1];
     * 搭配就可以過了
     * */
    public double largestSumOfAverages(int[] nums, int k) {
        int n = nums.length;
        double[][] dp = new double[n][k + 1];
        return dfs(nums, 0, k, dp);
    }

    double dfs(int[] nums, int j, int k, double[][] dp) {
        int n = nums.length;
        if(dp[j][k] > 0) return dp[j][k];
        if (k == 1) {
            double sum = 0, c = 0;
            for (; j < n; j++) {
                sum += nums[j];
                c++;
            }
            return sum / c;
        }

        double curSum = 0;
        double res = 0D;
        for (int i = j; i < n; i++) {
            curSum += nums[i];
            double score = curSum / (i - j + 1);
            if (i < n - 1) score += dfs(nums, i + 1, k - 1, dp);
            res = Math.max(res, score);
        }
        dp[j][k] = res;
        return res;
    }
}
