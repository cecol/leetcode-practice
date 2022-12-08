package leetcode202212.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC282ExpressionAddOperators extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC282ExpressionAddOperators();
        LC.addOperators("123", 6);
    }

    /**
     * https://leetcode.com/problems/expression-add-operators/discuss/71895/Java-Standard-Backtrace-AC-Solutoin-short-and-clear
     * 這題其實有兩個做法
     * 1. 就是直觀的直接 DFS 每兩兩數字插入 +/-/* 然後最後總結驗算 - 做一個 calculator
     * 2. 邊兩兩數字插入 +/-/* 邊總結
     * - 這個有幾個細節重點, 因為 * 是高優先權, 但我們當前遇到 +/- 都會直接總結帶下去
     * - 所以得把前面如果是累計的[最後連續 *] 帶下去, 然後遇到到 * case 時候
     * - evaled - multed + cur * multed,
     * - evaled - multed 就是把前面已結清的 * 先減回來, 然後再加上現在最新的累計 * 結果
     * - 解釋 https://github.com/wisdompeak/LeetCode/tree/master/DFS/282.Expression-Add-Operators
     * - 然後 累計 * 在帶下去 multed * cur
     * -    過程數值運算都用 long, 一定會 overflow int
     * -    遇到 0 得特別處理 - if (i != start && num.charAt(start) == '0') break;
     * -        就是如果當前遇到的 substring 已經是 0 開頭了, 其實你只能切出 0,
     * -        不可能再切出 01, 02, 03..., 所以當要切第二個數字時得 break
     * */
    List<String> res = new ArrayList<>();

    public List<String> addOperators(String num, int target) {
        dfs("", num, target, 0, 0, 0);
        return res;
    }

    void dfs(String curStr, String num, int t, int start, long evaled, long multed) {
        if (start == num.length()) {
            if (t == evaled) res.add(curStr);
            return;
        }
        for (int i = start; i < num.length(); i++) {
            if (i != start && num.charAt(start) == '0') break;
            long cur = Long.parseLong(num.substring(start, i + 1));
            if (start == 0) {
                dfs(curStr + cur, num, t, i + 1, cur, cur);
            } else {
                dfs(curStr + "+" + cur, num, t, i + 1, evaled + cur, cur);
                dfs(curStr + "-" + cur, num, t, i + 1, evaled - cur, -cur);
                dfs(curStr + "*" + cur, num, t, i + 1, evaled - multed + cur * multed, multed * cur);
            }
        }
    }
}
