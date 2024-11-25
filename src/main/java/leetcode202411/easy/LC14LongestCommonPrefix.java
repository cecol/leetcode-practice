package leetcode202411.easy;


import leetcode20200921to20201031.BasicTemplate;

import java.util.HashMap;
import java.util.Map;

public class LC14LongestCommonPrefix extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 直觀 bj4
    public String longestCommonPrefix(String[] strs) {
        int n = strs.length;
        int off = 0;
        while (true) {
            boolean match = true;
            for (int i = 0; i < n; i++) {
                if (off >= strs[0].length() || off >= strs[i].length() || strs[0].charAt(off) != strs[i].charAt(off)) {
                    match = false;
                    break;
                }
            }
            if (!match) break;
            off++;
        }
        return strs[0].substring(0, off);
    }

}
