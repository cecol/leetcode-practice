package leetcode202411.easy;


import leetcode20200921to20201031.BasicTemplate;

public class LC125ValidPalindrome extends BasicTemplate {
    public static void main(String[] args) {
        LC125ValidPalindrome lc = new LC125ValidPalindrome();
        lc.isPalindrome("A man, a plan, a canal: Panama");
    }

    public boolean isPalindrome(String s) {
        if (s == null || s.isEmpty()) return true;
        s = s.replaceAll("[^0-9a-zA-Z]", "").toLowerCase();
        int i = 0, j = s.length() - 1;
        while (i < j) if (s.charAt(i++) != s.charAt(j--)) return false;
        return true;
    }
}
