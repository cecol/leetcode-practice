package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.Stack;

public class LC277BasicCalculatorII extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 沒過
    // 1. + - * / 計算機都是用 stack 解
    // 2. 要記載 preSign 決定 當前 Num 要如何進 sk
    // 3. 遇到數字就進入 NUM 計算, 遇到 Sign 就是計算進入 SK & num 歸0
    public int calculate(String s) {
        int len = s.length();
        if (len == 0) return 0;
        Stack<Integer> sk = new Stack<>();
        int num = 0;

        char preSign = '+';
        for (int i = 0; i < len; i++) {
            if (Character.isDigit(s.charAt(i))) {
                num = num * 10 + s.charAt(i) - '0';
            }
            if (!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ' || i == len - 1) {
                if (preSign == '-') sk.push(-num);
                else if (preSign == '+') sk.push(num);
                else if (preSign == '*') sk.push(sk.pop() * num);
                else sk.push(sk.pop() / num);
                preSign = s.charAt(i);
                num = 0;
            }
        }
        int res = 0;
        for (int i : sk) res += i;
        return res;
    }
}
