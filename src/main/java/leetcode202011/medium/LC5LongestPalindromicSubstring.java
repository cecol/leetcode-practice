package leetcode202011.medium;

import leetcode20200921to20201031.BasicTemplate;

public class LC5LongestPalindromicSubstring extends BasicTemplate {
    public static void main(String[] args) {
        var LC = new LC5LongestPalindromicSubstring();
//        var s = LC.longestPalindrome("babad");
        var s2 = LC.longestPalindrome("ccc");
    }

    int s1 = 0;
    int s2 = 0;
    int max = 1;

    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return s;
        if (s.length() == 1) return s;
        for (int i = 0; i < s.length() - 1; i++) {
            checkPalindrome(s, i, i);
            checkPalindrome(s, i, i + 1);
        }
        var res = s.substring(s1, s2 + 1);
        return res;
    }

    public void checkPalindrome(String s, int i, int j) {
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            if (j - i + 1 > max) {
                s1 = i;
                s2 = j;
                max = j - i + 1;
            }
            i--;
            j++;
        }
    }
}
