package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Stack;

public class LC376WiggleSubsequence extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC376WiggleSubsequence();
        LC.wiggleMaxLength(new int[]{3, 3, 3, 2, 5});
    }

    /**
     * 有解出來 但效率值很差 是 O(N^2)
     * 其實可以 O(N)
     * 根本不用回頭看, 只要繼承就好
     * https://leetcode.com/problems/wiggle-subsequence/solutions/84843/easy-understanding-dp-solution-with-o-n-java-version/
     */
    public int wiggleMaxLength(int[] nums) {
        int n = nums.length;
        if (n == 1) return 1;
        int[] up = new int[n];
        int[] down = new int[n];
        up[0] = 1;
        down[0] = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                up[i] = down[i - 1] + 1;
                down[i] = down[i - 1];
            } else if (nums[i] < nums[i - 1]) {
                up[i] = up[i - 1];
                down[i] = up[i - 1] + 1;
            } else {
                up[i] = up[i - 1];
                down[i] = down[i - 1];
            }
        }
        return Math.max(up[n - 1], down[n - 1]);
    }

    public int wiggleMaxLengthSlow(int[] nums) {
        int n = nums.length;
        if (n == 1) return 1;
        int[][] dp = new int[2][n];
        int res = 1;
        Arrays.fill(dp[0], 1);
        Arrays.fill(dp[1], 1);
        for (int i = 1; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    dp[0][i] = Math.max(dp[0][i], dp[1][j] + 1);
                }
            }
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] < nums[j]) {
                    dp[1][i] = Math.max(dp[1][i], dp[0][j] + 1);
                }
            }
            res = Math.max(res, Math.max(dp[0][i], dp[1][i]));
        }
        return res;
    }
}
