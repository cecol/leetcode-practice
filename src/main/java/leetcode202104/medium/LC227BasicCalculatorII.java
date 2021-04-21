package leetcode202104.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC227BasicCalculatorII extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC227BasicCalculatorII();
    }

    /**
     * 原本想用Queue 遞迴來解, 但後來不太順, 直接參考人家解法
     * Stack解應該是很直觀
     * 不過字元判斷
     * if (Character.isDigit(s.charAt(i))) 數字處理
     * if (!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ' || i == len - 1) {
     * -> 這個跟前一個不能用 else if, 因為i == len - 1 代表就算最後一個是數字, 但因為是最後一個所以要放入stack
     */
    public int calculate(String s) {
        int len = s.length();
        if (len == 0) return 0;
        Stack<Integer> sk = new Stack<>();
        int num = 0;

        char preSign = '+';
        for (int i = 0; i < len; i++) {
            if (Character.isDigit(s.charAt(i))) {
                num = num * 10 + s.charAt(i) - '0';
            }
            if (!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ' || i == len - 1) {
                if (preSign == '-') sk.push(-num);
                else if (preSign == '+') sk.push(num);
                else if (preSign == '*') sk.push(sk.pop() * num);
                else sk.push(sk.pop() / num);
                preSign = s.charAt(i);
                num = 0;
            }
        }
        int res = 0;
        for (int i : sk) res += i;
        return res;
    }

}
