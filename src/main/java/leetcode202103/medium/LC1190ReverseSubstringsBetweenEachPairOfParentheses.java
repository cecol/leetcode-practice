package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class LC1190ReverseSubstringsBetweenEachPairOfParentheses extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1190ReverseSubstringsBetweenEachPairOfParentheses();
        LC.reverseParentheses2("(abcd)");
        LC.reverseParentheses2("(ed(et(oc))el)");
    }

    /**
     * 2022/11/30 再解
     * 發現這類題目有個 pattern
     * 就是字串內有 (/) 來表達要操作的優先權特性
     * 然後用 Stack 暫存狀態, ( => push, ) => pop 兌現成當前最新狀態
     * 類似題目有
     * LC1190ReverseSubstringsBetweenEachPairOfParentheses
     * LC856ScoreOfParentheses
     * LC224BasicCalculator
     * pattern 筆記 https://docs.google.com/document/d/1eieS66zQiWObyYMOp8nDws_53Q9V77FudMNq7uQrzGc/edit?usp=sharing
     * */
    public String reverseParentheses2(String s) {
        Stack<String> sk = new Stack<>();
        StringBuilder cur = new StringBuilder();
        for(int i = 0;i<s.length();i++) {
            if(s.charAt(i) == '(') {
                sk.push(cur.toString());
                cur.delete(0, cur.length());
            } else if(s.charAt(i) == ')') {
                String pre = sk.pop();
                cur = new StringBuilder(pre + cur.reverse().toString());
            } else {
                cur.append(s.charAt(i));
            }
        }
        return cur.toString();
    }

    /**
     * 基本stack解法來解是可以過, 但是 O(N^2),
     * 後來看到有 O(N) 覺得有意思
     * https://leetcode.com/problems/reverse-substrings-between-each-pair-of-parentheses/discuss/383670/JavaC%2B%2BPython-Tenet-O(N)-Solution
     */
    public String reverseParentheses1(String s) {
        Deque<Character> q = new LinkedList<>();
        for (char c : s.toCharArray()) {
            if (c != ')') q.add(c);
            else {
                StringBuilder ss = new StringBuilder("");
                while (q.getLast() != '(') ss.append(q.pollLast());
                q.pollLast();
                for (char cc : ss.toString().toCharArray()) q.add(cc);
            }
        }
        StringBuilder sb = new StringBuilder("");
        while (q.size() > 0) sb.append(q.pollFirst());
        return sb.toString();
    }

    /**
     * 基本stack解法來解是可以過, 但是 O(N^2),
     * 後來看到有 O(N) solution
     * https://leetcode.com/problems/reverse-substrings-between-each-pair-of-parentheses/discuss/383670/JavaC%2B%2BPython-Tenet-O(N)-Solution
     * 類似蟲洞的概念, 先記載記載每一對()的互相位置
     * 然後開始 i = 0 to n 但中間會因為踩到 ( or ) 而改變位置方向 d = 1 or -1, i+=d
     * 然後 i=pair[i] 來轉換位置進行跳躍
     */
    public String reverseParentheses_O_N_(String s) {
        int n = s.length();
        Deque<Integer> q = new LinkedList<>();
        int[] pair = new int[n];
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') q.add(i);
            if (s.charAt(i) == ')') {
                int j = q.pollLast();
                pair[i] = j;
                pair[j] = i;
            }
        }
        StringBuilder sb = new StringBuilder("");
        for (int i = 0, d = 1; i < n; i += d) {
            if (s.charAt(i) == ')' || s.charAt(i) == '(') {
                i = pair[i];
                d = -d;
            } else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

}
