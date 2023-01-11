package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

public class LC678ValidParenthesisString extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC678ValidParenthesisString();
    }

    /**
     * 可以很直觀的準備 ( 跟 * 的 Stack 記載他們的 position
     * 遇到 '(' 就放進 stack
     * 遇到 '*' 就放進 stack
     * 遇到 ')' 就 兌現 '(' or '*'
     * 直到最後看還有沒有剩下的 '(' 看能復透過 '*' 來兌現
     * */
    public boolean checkValidString(String s) {
        Stack<Integer> l = new Stack<>();
        Stack<Integer> a = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') l.push(i);
            else if (s.charAt(i) == ')') {
                if (l.size() > 0) l.pop();
                else if (a.size() > 0) a.pop();
                else return false;
            } else a.push(i);
        }
        while (l.size() > 0 && a.size() > 0 && a.peek() > l.peek()) {
            l.pop();
            a.pop();
        }
        return l.size() == 0;
    }
}
