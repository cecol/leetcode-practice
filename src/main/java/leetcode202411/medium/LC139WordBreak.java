package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.List;

public class LC139WordBreak extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 沒想起來
    // 1. 一定是 DP (跟其他 單字提搞混了)
    // 2. 確定能不能組關鍵在於 s.startsWith(w) 重頭找 & s.startsWith(w, i - w.length() + 1); (往下一個字找)
    // 3. 接著都是 DP 基本題了
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length()];
        for (String w : wordDict) {
            if (s.length() >= w.length() & s.startsWith(w)) dp[w.length() - 1] = true;
        }
        for (int i = 1; i < s.length(); i++) {
            for (String w : wordDict) {
                if (dp[i]) break;
                if (i - w.length() >= 0 && dp[i - w.length()]) {
                    dp[i] |= s.startsWith(w, i - w.length() + 1);
                }
            }
        }
        return dp[s.length() - 1];
    }
}
