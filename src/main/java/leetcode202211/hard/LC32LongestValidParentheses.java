package leetcode202211.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class LC32LongestValidParentheses extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC32LongestValidParentheses();
    }

    /**
     * https://leetcode.com/problems/longest-valid-parentheses/discuss/1140004/JS-Python-Java-C%2B%2B-or-Easy-Stack-Solution-w-Explanation
     * 原本以為是 two pointer, i , j 下去找, 結果根本不是, 只要一個stack
     * 1. Stack 記載有看到的 offset, 因為是算長度, sk 會記載 ( & ) 的 offset
     *    如果 ( 無條件 push
     *    如果 ) 要看能否兌現, 不能兌現 代表當前 ) offset 之前都是 invalid, 所以 ) offset 放入, 供後面遇到有兌現時候計算長得的邊界
     * 2. 當我們找到一對, 就把這對丟掉, stack top 就是當前 invalid position
     * 3. 會一開始放 -1 在 sk 是因為前面都沒有 invalid, 到目前為止都是正確的, 所以是目前的 offset i - (-1) 就是合法長度
     */
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
