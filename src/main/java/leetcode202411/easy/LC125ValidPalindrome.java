package leetcode202411.easy;


import leetcode20200921to20201031.BasicTemplate;

public class LC125ValidPalindrome extends BasicTemplate {
    public static void main(String[] args) {
        LC125ValidPalindrome lc = new LC125ValidPalindrome();
        lc.isPalindrome("A man, a plan, a canal: Panama");
    }

    public boolean isPalindrome(String s) {
        if (s == null || s.isEmpty()) return true;
        int i = 0, j = s.length() - 1;
        while (i <= j) {
            while (i < s.length() && !Character.isAlphabetic(s.charAt(i))) i++;
            while (j >= 0 && Character.isAlphabetic(s.charAt(j))) j--;
            if(i <= j && Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) return false;
            i++;
            j--;
        }
        return true;
    }
}
