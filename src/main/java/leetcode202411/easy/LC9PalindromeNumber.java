package leetcode202411.easy;


import leetcode20200921to20201031.BasicTemplate;

import java.util.HashMap;
import java.util.Map;

public class LC9PalindromeNumber extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 秒解 bj4
    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        String s = String.valueOf(x);
        int i = 0, j = s.length()-1;
        while (i<j) {
            if(s.charAt(i) != s.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }
}
