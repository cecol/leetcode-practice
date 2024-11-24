package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.List;

public class LC17LetterCombinationsOfAPhoneNumber extends BasicTemplate {
    public static void main(String[] args) {
    }

    // dfs 直觀秒解
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) return res;
        dfs(res, new StringBuilder(), digits, 0);
        return res;
    }

    char[][] dd = new char[][]{
            {},
            {},
            {'a', 'b', 'c'},
            {'d', 'e', 'f'},
            {'g', 'h', 'i'},
            {'j', 'k', 'l'},
            {'m', 'n', 'o'},
            {'p', 'q', 'r', 's'},
            {'t', 'u', 'v'},
            {'w', 'x', 'y', 'z'},
    };

    void dfs(List<String> res, StringBuilder sb, String digits, int idx) {
        if (idx == digits.length()) {
            res.add(sb.toString());
        } else {
            int v = digits.charAt(idx) - '0';
            for (char c : dd[v]) {
                sb.append(c);
                dfs(res, sb, digits, idx + 1);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
}
