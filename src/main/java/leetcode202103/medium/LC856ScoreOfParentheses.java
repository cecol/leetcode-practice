package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Deque;
import java.util.LinkedList;
import java.util.TreeSet;

public class LC856ScoreOfParentheses extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC856ScoreOfParentheses();
//        LC.scoreOfParentheses("(())");
        LC.scoreOfParentheses("()()");
    }

    public int scoreOfParentheses(String S) {
        Deque<Object> q = new LinkedList<>();
        for (char c : S.toCharArray()) {
            if (c == '(') q.add(Character.valueOf(c));
            else {
                int s = 0;
                Object next = q.pollLast();
                while (!(next instanceof Character)) {
                    s += (Integer) next;
                    next = q.pollLast();
                }
                if (s == 0) q.add(1);
                else q.add(2 * s);
            }
        }
        int r = 0;
        while (q.size() > 0) r += (Integer) q.poll();
        return r;
    }
}
