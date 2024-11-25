package leetcode202411.easy;


import leetcode20200921to20201031.BasicTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class LC844BackspaceStringCompare extends BasicTemplate {
    public static void main(String[] args) {
        String s = "y#fo##f";
        String t = "y#f#o##f";
        LC844BackspaceStringCompare lc = new LC844BackspaceStringCompare();
        lc.log.debug(bk(s));
        lc.log.debug(bk(t));
    }

    public boolean backspaceCompare(String s, String t) {
        return bk(s).equals(bk(t));
    }

    // 花了點時間DEBUG, BJ4
    static String bk(String s) {
        Stack<Character> sk = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '#') {
                if (!sk.isEmpty()) sk.pop();
            } else sk.push(c);
        }
        StringBuilder sb = new StringBuilder();
        while (!sk.isEmpty()) sb.append(sk.pop());
        return sb.toString();
    }

}
