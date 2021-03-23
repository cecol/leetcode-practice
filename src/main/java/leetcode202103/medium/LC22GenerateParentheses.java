package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LC22GenerateParentheses extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC22GenerateParentheses();
        LC.generateParenthesis(3);
        LC.generateParenthesis(1);
    }


    /**
     * https://leetcode.com/problems/generate-parentheses/discuss/10100/Easy-to-understand-Java-backtracking-solution
     * 我一開始沒想說用 兩個integer 下去backtrack, 來確保 () 合法性,
     * 一開始想歪後來有想到但也沒多花心思去繼續想
     * 中途是有想到是否需要兩個 integer去指示當前狀況
     * 看到答案才明白我想太多了
     * */
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        gen(res, "", 0, 0, n);
        log.debug("{}", res);
        return res;
    }

    private void gen(List<String> res, String sb, int open, int close, int n) {
        if (sb.length() == n * 2) {
            res.add(sb);
            return;
        }
        if (open < n) {
            gen(res, sb + "(", open + 1, close, n);
        }
        if (close < open) {
            gen(res, sb + ")", open, close + 1, n);
        }
    }
}
