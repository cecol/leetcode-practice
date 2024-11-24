package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.List;

public class LC5LongestPalindromicSubstring extends BasicTemplate {
    public static void main(String[] args) {
    }

    int s1 = 0, s2 = 0, max = 1;

    // 忘記細節了, 以為跟 2 pointer 有關, 但其實沒有
    // 以為下去 substring 找Palindrome 會超時, 其實也不會
    // 關鍵就是 每個字元 & 該字元 + 1 下去找 找 Palindrome
    // 用 global variable 下去找最大 Palindrome 區間
    public String longestPalindrome(String s) {
        if (s == null || s.isEmpty()) return s;
        if (s.length() == 1) return s;
        for (int i = 0; i < s.length() - 1; i++) {
            check(s, i, i);
            check(s, i, i + 1);
        }
        String res = s.substring(s1, s2 + 1);
        return res;
    }

    void check(String s, int i, int j) {
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            if (j - i + 1 > max) {
                max = j - i + 1;
                s1 = i;
                s2 = j;
            }
            i--;
            j++;
        }
    }

}
