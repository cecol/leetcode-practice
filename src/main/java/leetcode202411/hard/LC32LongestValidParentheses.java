package leetcode202411.hard;


import leetcode20200921to20201031.BasicTemplate;

import java.util.PriorityQueue;
import java.util.Stack;

public class LC32LongestValidParentheses extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 沒過
    // 1. sk 記載當前看到的 OFFSET
    // 2. ( -> push; ) -> 兌現 S or PUSH
    public int longestValidParentheses(String s) {
        Stack<Integer> sk = new Stack<>();
        int res = 0;
        sk.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ')' && sk.size() > 1 && s.charAt(sk.peek()) == '(') {
                sk.pop();
                res = Math.max(res, i - sk.peek());
            } else sk.push(i);
        }
        return res;
    }
}
