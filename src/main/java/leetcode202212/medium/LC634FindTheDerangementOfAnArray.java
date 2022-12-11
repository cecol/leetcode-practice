package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

public class LC634FindTheDerangementOfAnArray extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC634FindTheDerangementOfAnArray();
    }

    /**
     * https://leetcode.com/problems/find-the-derangement-of-an-array/solutions/104981/if-you-don-t-understand/
     * 這題完全不直觀啊, 看到答案跟解才發現很簡單 但真的可以這麼容易推倒出最簡潔的路徑嗎?
     *
     * dp[n] = (n-1) * (dp[n-1] + dp[n-2])
     * 這要解釋成
     * 第 nth num 不能放在 index n, 所以他有 n-1 個選擇
     * 假設 nth 跟 ith 對調, 這樣兩個數位置確定了, 剩下問題就是 dp[n-2] 來找位置
     * 加設 nth 還是選擇 ith 位置, 但 ith 並沒有選擇 nth 來放, 這樣就是剩下 dp[n-1] 個數來找位置
     * (其他數不能選自己, ith 不能選 nth)
     */
    public int findDerangement(int n) {
        if (n <= 1) return 0;
        long[] dp = new long[n + 1];
        int mod = (int) 1e9 + 7;
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            dp[i] = (i - 1) * (dp[i - 1] + dp[i - 2]) % mod;
        }
        return (int)dp[n];
    }
}
