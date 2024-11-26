package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.*;

public class LC394DecodeString extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 直觀一次過, deque 處理 [ & ] CASE
    public String decodeString(String s) {
        Deque<Character> dq = new LinkedList<>();
        for (char c : s.toCharArray()) dq.add(c);
        return de(dq);
    }

    String de(Deque<Character> dq) {
        StringBuilder sb = new StringBuilder();
        int sum = 0;
        while (dq.size() > 0) {
            char c = dq.pollFirst();
            if (Character.isDigit(c)) {
                sum = sum * 10 + c - '0';
            } else if (c == '[') {
                String sub = de(dq);
                for (int i = 0; i < sum; i++) sb.append(sub);
                sum = 0;
            } else if (c == ']') {
                break;
            } else sb.append(c);
        }
        return sb.toString();
    }
}
