package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class LC792NumberOfMatchingSubsequences extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC792NumberOfMatchingSubsequences();
    }

    /**
     * 跟 LC524LongestWordInDictionaryThroughDeleting 一模一樣
     * https://leetcode.com/problems/number-of-matching-subsequences/solutions/117600/java-o-n-solution/
     * 這題如果直觀的每個 word 帶入 String s 下去找, 會 TLE
     * 要特別解法, state machine
     * 基本概念就是 建立一個 int[][] next
     * next[i][j], i 是指 s(i) char, j = 0 - 26, 就是說在
     * s(i) 位置, 後面再出現下一個 a-z 字母的 位置
     * <p>
     * 然後 每個 word 就可以根據這個 next 反覆去找
     * - int j = 0;  每個 word 從 s(0) 開始往後找 當前看到的 word(i) char
     * - boolean match = true; 最終展示有沒有找完
     * - for (char c : w.toCharArray()) {
     * - j = next[j][c - 'a']; 如果有找到 j != -1, 就從當前 j 往下一圈繼續找, 如果 j == -1 沒找到就可以跳離
     * -     if (j == -1) match = false && break;
     * -
     * https://github.com/wisdompeak/LeetCode/blob/master/Greedy/792.Number-of-Matching-Subsequences/792.Number-of-Matching-Subsequences_v2.cpp
     * 我還是 wisdompeak 解法比較看得懂
     * 如何建立 int[][] next = new int[n + 1][26];
     * - s = '#' + s; 先把 s 延長, 不影響結果
     * - Arrays.fill(next[n], -1); n 後面都沒字元  自然都是 -1
     * - for (int i = n; i >= 1; i--) {  都是從尾巴往前建立
     * -  for (int k = 0; k < 26; k++) {
     * -     next[i-1][k] = next[i][k]; next[i-1] 繼承自前面
     * -  }
     * -  next[i-1][s.charAt(i) - 'a'] = i; s(i-1) 看到 s(i) char 位置就是 i
     * - }
     */
    public int numMatchingSubseq(String s, String[] words) {
        int n = s.length();
        int[][] next = new int[n + 1][26];
        s = '#' + s;
        Arrays.fill(next[n], -1);
        for (int i = n; i >= 1; i--) {
            for (int k = 0; k < 26; k++) {
                next[i - 1][k] = next[i][k];
            }
            next[i - 1][s.charAt(i) - 'a'] = i;
        }

        int res = 0;
        for (String w : words) {
            int j = 0;
            boolean match = true;
            for (char c : w.toCharArray()) {
                j = next[j][c - 'a'];
                if (j == -1) {
                    match = false;
                    break;
                }
            }
            if (match) res++;
        }
        return res;
    }
}
