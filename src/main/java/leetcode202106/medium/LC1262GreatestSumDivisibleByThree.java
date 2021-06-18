package leetcode202106.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC1262GreatestSumDivisibleByThree extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1262GreatestSumDivisibleByThree();
        LC.maxSumDivThreeDetail(new int[]{3, 6, 5, 1, 8});
    }

    /**
     * 用基本的DFS思路來遞迴當前元素要or不要下去組出各種可能找出最大值
     * 這個有想出來, 但是TLE
     * 沒辦法好好想出可以 memoization 的 DP structure
     * https://leetcode.com/problems/greatest-sum-divisible-by-three/discuss/431077/JavaC%2B%2BPython-One-Pass-O(1)-space
     * 完全沒想到可以這樣想
     * 真的是太神奇
     * 1. int[] dp 只要是 int[3] -> 因為我麼只要知到 % 3的餘數的各種最大值
     * 2. iterate nums 的每一個元素 -> 針對前一次的 dp[] 裡面記錄到該餘數的最大值去加上去看能不能更大
     * 3. 最後回傳 dp[0] -> 因為這是 %3 == 0 的最佳值
     * 4. 這可以 apply 到 % k == 0 的情況
     * 5. 細節 for(int i: Arrays.copyOf(dp, dp.length))
     * ->    dp[(n+i) % 3] = Math.max(dp[(n+i) % 3], n + i);
     * ->    會要copy array dp 是因為當前dp 會被更新, 所以先copy當前一份的來計算加上當前值 n 的結果
     * ->    其實應該也可以寫成 dp[n][3]
     * ->    當前的 dp[i][j] = Math.max(dp[i][j], dp[i-1][j]) 與 當前
     * ->          dp[i][(pre + cur) % 3] = Math.max(dp[i][(pre + cur) % 3], pre + cur);
     */
    public int maxSumDivThreeOpt(int[] nums) {
        int[] dp = new int[3];
        for (int n : nums) {
            for (int i : Arrays.copyOf(dp, dp.length))
                dp[(n + i) % 3] = Math.max(dp[(n + i) % 3], n + i);
        }
        return dp[0];
    }

    public int maxSumDivThreeDetail(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][3];
        for (int i = 0; i < n; i++) {
            int cur = nums[i];
            for (int j = 0; j < 3; j++) {
                if (i == 0) {
                    dp[i][cur % 3] = cur;
                } else {
                    int pre = dp[i - 1][j];
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                    dp[i][(pre + cur) % 3] = Math.max(dp[i][(pre + cur) % 3], pre + cur);
                }
            }
        }
        return dp[n - 1][0];
    }
}
