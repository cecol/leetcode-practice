package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class LC150EvaluateReversePolishNotation extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 初見殺
    public int evalRPN(String[] tokens) {
        Stack<Integer> sk = new Stack<>();

        for (String t : tokens) {
            if (t.equals("+")) {
                sk.push(sk.pop() + sk.pop());
            } else if (t.equals("-")) {
                int i1 = sk.pop();
                int i2 = sk.pop();
                sk.push(i2 - i1);
            } else if (t.equals("*")) {
                sk.push(sk.pop() * sk.pop());
            } else if (t.equals("/")) {
                int i1 = sk.pop();
                int i2 = sk.pop();
                sk.push(i2 / i1);
            } else sk.push(Integer.valueOf(t));
        }
        return sk.pop();
    }
}
