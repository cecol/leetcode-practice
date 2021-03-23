package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class LC150EvaluateReversePolishNotation extends BasicTemplate {

    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC150EvaluateReversePolishNotation();
    }

    public int evalRPN(String[] tokens) {
        if (tokens.length == 0) return 0;
        Deque<Integer> q = new LinkedList<>();
        for (String t : tokens) {
            if (t.equals("+")) {
                Integer a = q.pop();
                Integer b = q.pop();
                q.push(a + b);
            } else if (t.equals("-")) {
                Integer a = q.pop();
                Integer b = q.pop();
                q.push(b - a);
            } else if (t.equals("*")) {
                Integer a = q.pop();
                Integer b = q.pop();
                q.push(a * b);
            } else if (t.equals("/")) {
                Integer a = q.pop();
                Integer b = q.pop();
                q.push(b / a);
            } else {
                q.push(Integer.parseInt(t));
            }
        }
        return q.pop();
    }
}
