package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;
import java.util.TreeSet;

public class LC856ScoreOfParentheses extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC856ScoreOfParentheses();
        LC.scoreOfParentheses("(())");
        //LC.scoreOfParentheses("()()");
    }

    /**
     * https://leetcode.com/problems/score-of-parentheses/discuss/1856519/JavaC%2B%2B-Visually-Explained!!
     * 原本有解出來  但是用 Deque 不是用 Stack,
     * 改找 Stack 作法來學習 Stack
     * 1. 有個 global score 記載當前收到多少 score
     * 2. 遇到 ( 就是把 記載當前 score 放入 stack, score 歸 0
     * 3. 遇到 ) 就是把 stack 前面的 score pop 跟 2 * (正在累積的 score) 結算
     * - score = sk.pop() + Math.max(2 * score, 1); 算完 assign 給 score, 下次繼續用
     * - 要嘛遇到 (, 又放入 stack, score = 0
     * - 要嘛遇到 ), score 繼續放大
     * -
     * - 這樣想好了, '(' case 很直觀, 就是 push(score) & score = 0
     * - ')' 第一件直觀就是一定兌現 最近一個 '(' push, 就是 stack.pop
     * - x2 並沒有 兌現 最近一個 '(' push
     * - x2 兌現在 連續 '))'
     * - 如果 ')' 後遇到 '(', 當前結果又會被 push 暫存 等下個 ')' 拿出來
     * - 直到真的有連續 '))' 來 x2
     * Ex: (()())
     * 第一個 ')' pop 會拿到 0, 結算 score = 1, 又會再 '(' 又放入 sk
     * 第二個 ')' pop 會拿到 1, 這個 1 是上一個 '(' 放入的, 結算 score = 2
     * 第二個 ')' pop 會拿到 0, 這個 0 是上第一個 '(' 放入的, 結算 score = 4 (來自上一個 score 延續算下來)
     *
     * 所以每次 ')' 他會拿出 對應的 '(' 所存 score
     * 並看是否有延續之前 score*2, 有可能之前 score 是 0, 那 ')' 至少拿到 1
     * 所以是 Math.max(score*2, 1)
     * 絕對不會是 score = 2*sk.pop() 因為對應 '(' 是更之前的事了, 無法 x2
     * 一定只有自己累積 score 才可以 x2
     */
    public int scoreOfParentheses(String S) {
        Stack<Integer> sk = new Stack<>();
        int score = 0;
        for (char c : S.toCharArray()) {
            if (c == '(') {
                sk.push(score);
                score = 0;
            } else {
                score = sk.pop() + Math.max(2 * score, 1);
            }
        }
        return score;
    }

    public int scoreOfParenthesesDeque(String S) {
        Deque<Object> q = new LinkedList<>();
        for (char c : S.toCharArray()) {
            if (c == '(') q.add(Character.valueOf(c));
            else {
                int s = 0;
                Object next = q.pollLast();
                while (!(next instanceof Character)) {
                    s += (Integer) next;
                    next = q.pollLast();
                }
                if (s == 0) q.add(1);
                else q.add(2 * s);
            }
        }
        int r = 0;
        while (q.size() > 0) r += (Integer) q.poll();
        return r;
    }
}
