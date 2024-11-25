package leetcode202411.hard;


import leetcode20200921to20201031.BasicTemplate;

import java.util.*;

public class LC224BasicCalculator extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 一定要回去看, 才會知道細節
    // 只有 + -, stack 處置 ( )
    // 有結果就算出來到 res
    // 遇到數字就一路讀到底
    public int calculate(String s) {
        int len = s.length(), sign = 1, res = 0;
        Stack<Integer> sk = new Stack<>();
        for (int i = 0; i < len; i++) {
            if (Character.isDigit(s.charAt(i))) {
                int sum = s.charAt(i) - '0';
                while (i + 1 < len && Character.isDigit(s.charAt(i + 1))) {
                    sum = sum * 10 + s.charAt(i + 1) - '0';
                    i++;
                }
                res += sum * sign;
            } else if (s.charAt(i) == '+') {
                sign = 1;
            } else if (s.charAt(i) == '-') {
                sign = -1;
            } else if (s.charAt(i) == '(') {
                sk.push(res);
                sk.push(sign);
                res = 0;
                sign = 1;
            } else if (s.charAt(i) == ')') {
                res = res * sk.pop() + sk.pop();
            }
        }
        return res;
    }
}
