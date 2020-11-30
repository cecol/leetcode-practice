package leetcode202011.medium;

import leetcode20200921to20201031.BasicTemplate;

public class LC647PalindromicSubstrings extends BasicTemplate {
    public static void main(String[] args) {
        var LC = new LC647PalindromicSubstrings();
        var s = LC.countSubstrings("abc");
    }

    int count = 1;
    /**
     * https://leetcode.com/problems/palindromic-substrings/discuss/105688/Very-Simple-Java-Solution-with-Detail-Explanation
     * */
    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) return 0;
        if (s.length() == 1) return 1;
        for (int i = 0; i < s.length() - 1; i++) {
            checkPalindrome(s, i, i);
            checkPalindrome(s, i, i + 1);
        }
        log.debug("count: {}", count);
        return count;
    }

    public void checkPalindrome(String s, int b, int e) {
        while (b >= 0 && e < s.length() && s.charAt(b) == s.charAt(e)) {
            count += 1;
            b--;
            e++;
        }
    }
}
