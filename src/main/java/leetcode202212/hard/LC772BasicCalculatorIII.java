package leetcode202212.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class LC772BasicCalculatorIII extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC772BasicCalculatorIII();
    }

    /**
     * 這類題目遇到 () 比較好處理的解法是
     * 1. Queue 配上遞迴
     * https://leetcode.com/problems/basic-calculator-iii/discuss/1318373/Java-Using-stack-and-recursion
     * 2. 不用 Queue, 直接算 braces 階層來遞迴
     * https://leetcode.com/problems/basic-calculator-iii/discuss/344371/Java-Common-template-for-Basic-Calculator-I-II-and-III-using-Stack
     *
     * 我個人比較喜歡 Queue 版本
     * 1. 維持一個 num 記載當前累計數字, 一個 preOp = '+' 來看看目前累計 nums 要如何兌現
     * 2. char c = q.poll()
     * 3. 遇到數字就是 num = num * 10 + c - '0';
     * 4. 遇到 ( => 遞迴 => num = helper(q)
     * 5. 遇到 ) => 代表當前是遞迴 break 出去兌現回傳
     * 6. 遇到新的 operator => 兌現 preOp, 把結果放入 stack,
     * -> +,-,*,/ 有兩個重點
     * -> *,/ 都是得當下兌現 sk.push(num * sk.pop()); or sk.push(sk.pop() / num);
     * -> +,- 都是配上 preOp 放入 stack
     * 7. 最後迴圈結束要再把當前 num 兌現放入 stack -> compute(preOp, num, sk);
     * 8. 然後把 stack 都叫出來 全兌現
     *
     * 以這題為專門解法 就可以拿去解
     * LC224BasicCalculator 有遞迴但沒有 *,/
     * LC227BasicCalculatorII 沒有遞迴因為沒有 (,)
     **/
    public int calculateQueue(String s) {
        if (s == null || s.length() == 0) return 0;
        Queue<Character> q = new LinkedList<>();
        for (char c : s.toCharArray()) q.offer(c);
        q.offer('+');
        return helper(q);
    }

    int helper(Queue<Character> q) {
        char preOp = '+';
        int num = 0;
        Stack<Integer> sk = new Stack<>();
        while (q.size() > 0) {
            char c = q.poll();
            if (c == ' ') ;
            else if (Character.isDigit(c)) num = num * 10 + c - '0';
            else if (c == '(') num = helper(q);
            else if (c == ')') break;
            else {
                compute(preOp, num, sk);
                num = 0;
                preOp = c;
            }
        }
        compute(preOp, num, sk);
        int sum = 0;
        for (Integer a : sk) sum += a;
        return sum;
    }

    void compute(char preOp, int num, Stack<Integer> sk) {
        if (preOp == '+') sk.push(num);
        else if (preOp == '-') sk.push(-num);
        else if (preOp == '*') sk.push(num * sk.pop());
        else sk.push(sk.pop() / num);
    }
}
