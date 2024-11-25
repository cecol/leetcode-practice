package leetcode202411.easy;


import leetcode20200921to20201031.BasicTemplate;

import java.util.HashMap;
import java.util.Map;

public class LC13RomanToInteger extends BasicTemplate {
    public static void main(String[] args) {
    }

    // bj4
    public int romanToInt(String s) {
        if (s == null || s.length() == 0) return 0;
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == 'I') {
                res += 1;
            } else if (c == 'V') {
                res += 5;
            } else if (c == 'X') {
                res += 10;
            } else if (c == 'L') {
                res += 50;
            } else if (c == 'C') {
                res += 100;
            } else if (c == 'D') {
                res += 500;
            } else if (c == 'M') {
                res += 1000;
            }
            if (i > 0) {
                if ((c == 'V' || c == 'X') && s.charAt(i-1) == 'I') res -= 2;
                if ((c == 'L' || c == 'C') && s.charAt(i-1) == 'X') res -= 20;
                if ((c == 'D' || c == 'M') && s.charAt(i-1) == 'C') res -= 200;
            }
        }
        return res;
    }
}
