package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Deque;
import java.util.LinkedList;

public class LC1190ReverseSubstringsBetweenEachPairOfParentheses extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1190ReverseSubstringsBetweenEachPairOfParentheses();
        LC.reverseParentheses("(abcd)");
        LC.reverseParentheses("(ed(et(oc))el)");
    }

    /**
     * 基本stack解法來解是可以過, 但是 O(N^2),
     * 後來看到有 O(N) 覺得有意思
     * https://leetcode.com/problems/reverse-substrings-between-each-pair-of-parentheses/discuss/383670/JavaC%2B%2BPython-Tenet-O(N)-Solution
     */
    public String reverseParentheses(String s) {
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
