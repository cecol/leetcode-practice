package leetcode202411.easy;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class LC20ValidParentheses {
    public static void main(String[] args) {
    }

    Stack<Character> sk = new Stack<>();
    public boolean isValid(String s) {
        for(char c : s.toCharArray()) {
            if(c == '(' || c == '[' || c == '{') {
                sk.push(c);
            } else {
                if(sk.isEmpty()) {
                    return false;
                }
                char back = sk.pop();
                if(c == ')' && back != '(') return false;
                if(c == ']' && back != '[') return false;
                if(c == '}' && back != '{') return false;
            }
        }
        return sk.isEmpty();
    }
}
