package leetcode202103.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class LC224BasicCalculator extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC224BasicCalculator();
    }

    /**
     * 雖然知道邏輯, 但自己寫的很雜亂, 還是參考人家整理好的邏輯為主
     * https://leetcode.com/problems/basic-calculator/discuss/62362/JAVA-Easy-Version-To-Understand!!!!!
     * 幾個重點
     * 1. 遇到數字先greedy 把它全部讀完直到讀不到
     * 2. 一個 global result 去記錄當前都加總, 最後直接回傳他, -> 所以遇到數字先greedy 把它全部讀完 -> 然後加入 result
     * 3. stack 只處理 '(' & ')', 等於說如果沒有 '(' & ')', stack 用不到, result 直接算完
     * -> '(' 就塞入目前res into stack + sign into stack
     * -> stack 先放當前 result, 再放 sign, sign 是給下一層用的, result已經在計算時候 apply sign了
     * -> ')' 就讀出 result * stack.pop() + stack.pop()
     * -> 此時 stack top是屬於當前計算 result 的 sign(就是 '(' 前的+-值), 下一個是之前算的 result
     * 4. '+' , '-' 只處理 sign, 真正執行的 加減,  都在 Character.isDigit(s.charAt(i)) 這邊算完了 result += sum * sign;
     */
    public int calculate(String s) {
        int len = s.length(), sign = 1, result = 0;
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < len; i++) {
            if (Character.isDigit(s.charAt(i))) {
                int sum = s.charAt(i) - '0';
                while (i + 1 < len && Character.isDigit(s.charAt(i + 1))) {
                    sum = sum * 10 + s.charAt(i + 1) - '0';
                    i++;
                }
                result += sum * sign;
            } else if (s.charAt(i) == '+')
                sign = 1;
            else if (s.charAt(i) == '-')
                sign = -1;
            else if (s.charAt(i) == '(') {
                stack.push(result);
                stack.push(sign);
                result = 0;
                sign = 1;
            } else if (s.charAt(i) == ')') {
                result = result * stack.pop() + stack.pop();
            }

        }
        return result;
    }
}
