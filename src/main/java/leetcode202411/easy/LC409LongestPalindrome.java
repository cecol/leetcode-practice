package leetcode202411.easy;


import leetcode20200921to20201031.BasicTemplate;

import java.util.HashMap;
import java.util.Map;

public class LC409LongestPalindrome extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 很直觀, 第一次就解開
    public int longestPalindrome(String s) {
        int[] cc = new int[256];
        for (char c : s.toCharArray()) {
            cc[c]++;
        }
        boolean odd = false;
        int res = 0;
        for (int i : cc) {
            res+=i/2*2;
            if(i % 2 == 1) {
                odd = true;
            }
        }
        if (odd) res++;
        return res;
    }
}
