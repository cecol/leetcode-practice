package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.List;

public class LC8StringToInteger_atoi extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 太多細節處理沒記起來
    // 1. 重前面計算手段 - sum = sum * 10 + s.charAt(i) - '0';
    // 2. +/- 號可能多個, 所以 start 是累計
    // 3. 總值用 LONG 計算有想到
    public int myAtoi(String s) {
        if (s == null || s.isEmpty()) return 0;
        s = s.trim(); // 去除空白
        if (s.isEmpty()) return 0;
        long sum = 0L;
        char fc = s.charAt(0);
        int sign = 1, start = 0, len = s.length();
        if (fc == '+') {
            start++;
            sign = 1;
        }
        if (fc == '-') {
            start++;
            sign = -1;
        }
        for (int i = start; i < len; i++) {
            if (!Character.isDigit(s.charAt(i))) break;
            sum = sum * 10 + s.charAt(i) - '0';
            if(sign == 1 && sum > Integer.MAX_VALUE) return Integer.MAX_VALUE;
            if(sign == -1 && sign * sum < Integer.MIN_VALUE) return Integer.MIN_VALUE;
        }
        return (int) sum * sign;
    }
}
