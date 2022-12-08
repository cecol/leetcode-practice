package leetcode202212.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC301RemoveInvalidParentheses extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC301RemoveInvalidParentheses();
    }

    /**
     * https://leetcode.com/problems/remove-invalid-parentheses/discuss/75034/Easiest-9ms-Java-Solution
     * 看到要 remove the minimum number of invalid parentheses 就矇了
     * 還要配上其他字元的各種組合
     * 看到別人解釋才發現有幾個重點
     * 1. 先計算好總共能夠 remove ( -> rmL , remove ) -> rmR
     * 2. 用一個 Set<String> 下去記載已知的合法答案, Set 可以去重
     * 3. String s 一個一個字元下去 dfs
     * - if rmL < 0 || rmR < 0 || open(在cur 裏面的 '(' 個數 ) < 0 -> 一定非法, 直接 return
     * - if pos == s.length(), 代表經找完
     * -    if rmL == 0 && rmR == 0 && open == 0, 這是合法 ->  可以加入答案
     * - 接著就是根據當前字元是 (, ) or others 下去遞迴
     * - ( or ) 就是有分取跟不取的 case, 配合的 rmL, rmR and open 都要對應 +/-
     * - Ex: c == '(' -> dfs(s, i + 1, res, cur, rmL - 1, rmR, open); -> 代表不取當前 c == (', 所以 rmL--
     * - Ex: c == '(' -> dfs(s, i + 1, res, cur, rmL, rmR, open + 1); -> 代表取當前 c == (', 所以 open++
     * 反之亦然
     * */
    public List<String> removeInvalidParentheses(String s) {
        int rmL = 0, rmR = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') rmL++;
            else if (c == ')') {
                if (rmL > 0) rmL--;
                else rmR++;
            }
        }

        Set<String> res = new HashSet<>();
        dfs(s, 0, res, new StringBuilder(), rmL, rmR, 0);
        return new ArrayList<>(res);
    }

    void dfs(String s, int i, Set<String> res, StringBuilder cur, int rmL, int rmR, int open) {
        if (open < 0 || rmR < 0 || rmL < 0) return;
        if (i == s.length()) {
            if (open == 0 && rmL == 0 && rmR == 0) res.add(cur.toString());
            return;
        }

        char c = s.charAt(i);
        int len = cur.length();

        if (c == '(') {
            dfs(s, i + 1, res, cur, rmL - 1, rmR, open);
            dfs(s, i + 1, res, cur.append(c), rmL, rmR, open + 1);
        } else if (c == ')') {
            dfs(s, i + 1, res, cur, rmL, rmR - 1, open);
            dfs(s, i + 1, res, cur.append(c), rmL, rmR, open - 1);
        } else dfs(s, i + 1, res, cur.append(c), rmL, rmR, open);

        cur.setLength(len);
    }
}
