package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.PriorityQueue;

public class LC91DecodeWays extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 沒過
    // 1. 先看 dp[0] == '0'
    // 2. 看 s.substring(i, i+1) 是否在 1 - 9, dp[i] += dp[i-1]
    // 3. 在看 s.substring(i-1, i+1) 是否在 11 - 26, dp[i] += i >= 2 ? dp[i-2] : 1
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) return 0;
        int[] dp = new int[s.length()];
        dp[0] = s.charAt(0) == '0' ? 0 : 1;
        for (int i = 1; i < s.length(); i++) {
            int c1 = Integer.parseInt(s.substring(i, i + 1));
            int c2 = Integer.parseInt(s.substring(i - 1, i + 1));
            if (c1 >= 1 && c1 <= 9) dp[i] += dp[i - 1];
            if (c2 > 10 && c2 <= 26) dp[i] += i >= 2 ? dp[i - 2] : 1;
        }
        return dp[s.length() - 1];
    }
}
