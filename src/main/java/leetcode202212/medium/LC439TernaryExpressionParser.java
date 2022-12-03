package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

public class LC439TernaryExpressionParser extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC439TernaryExpressionParser();
    }

    /**
     * https://leetcode.com/problems/ternary-expression-parser/discuss/92166/Very-easy-1-pass-Stack-Solution-in-JAVA-(NO-STRING-CONCAT)
     * 這題一開始看有個直覺是從尾巴開始看
     * (其實從頭也可以, 只是處理方式比較複雜)
     * 1. 看到什麼都 push
     * 2. 只有看到 sk.peek() == '?'
     * - 代表當前 char 是個確定值, 且可以 evaluate
     * - sk.pop(); 這時候前面一定是 ?
     * - sk.pop(); 這時候前面一定是 value1
     * - sk.pop(); 這時候前面一定是 :
     * - sk.pop(); 這時候前面一定是 value2
     * - 根據當前 T 來決定選 value1 or value2, 然後放回 stack
     * 3. stack.top 就是最後結果
     * */
    public String parseTernary(String e) {
        Stack<Character> sk = new Stack<>();
        for (int i = e.length(); i >= 0; i--) {
            char c = e.charAt(i);
            if (sk.size() > 0 && sk.peek() == '?') {
                sk.pop();
                char c1 = sk.pop();
                sk.pop();
                char c2 = sk.pop();
                if (c == 'T') sk.push(c1);
                else sk.push(c2);
            } else sk.push(c);
        }
        return String.valueOf(sk.pop());
    }
}
