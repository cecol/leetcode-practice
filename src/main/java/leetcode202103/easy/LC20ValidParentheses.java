package leetcode202103.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Deque;
import java.util.LinkedList;

public class LC20ValidParentheses extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC20ValidParentheses();
        LC.isValid("([)]");
    }

    public boolean isValid(String s) {
        Deque<Character> q = new LinkedList<>();
        for (char c : s.toCharArray()) {
            Character b = null;
            switch (c) {
                case ')':
                    b = q.pollLast();
                    if(b == null || !b.equals('(') ) return false;
                    break;
                case '}':
                    b = q.pollLast();
                    if(b == null || !b.equals('{') ) return false;
                    break;
                case ']':
                    b = q.pollLast();
                    if(b == null || !b.equals('[') ) return false;
                    break;
                default: q.add(c);
                    break;
            }
        }
        if(q.size()>0) return false;
        return true;
    }
}
