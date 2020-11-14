package leetcode202011.medium;

import leetcode20200921to20201031.BasicTemplate;

import java.util.Arrays;
import java.util.stream.IntStream;

public class LC1049LastStoneWeightII extends BasicTemplate {
    public static void main(String[] args) {
        var LC = new LC1049LastStoneWeightII();
        var s = LC.lastStoneWeightII(new int[]{2, 7, 4, 1, 8, 1});
    }

    /**
     * https://leetcode.jp/leetcode-1049-last-stone-weight-ii-%E8%A7%A3%E9%A2%98%E6%80%9D%E8%B7%AF%E5%88%86%E6%9E%90/
     */
    public int lastStoneWeightII(int[] stones) {
        int sum = IntStream.of(stones).sum();
        int t = sum / 2;
        boolean[] dp = new boolean[t + 1];
        dp[0] = true;
        int max = 0;
        for (int s : stones) {
            for (int i = t; i >= s; i--) {
                dp[i] = dp[i] || dp[i - s];
                if (dp[i]) {
                    if (i == t) return sum - t - t;
                    max = Math.max(max, i);
                }
            }
        }
        return sum - max - max;
    }
}
