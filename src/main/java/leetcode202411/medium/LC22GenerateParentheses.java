package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.List;

public class LC22GenerateParentheses extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 直觀 dfs 秒解 - 主要需要帶 i & j 下去 DFS 來表達現在有幾個 ( or )
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        dfs(res, 0, 0, n, new StringBuilder());
        return res;
    }

    void dfs(List<String> res, int i, int j, int n, StringBuilder sb) {
        if (i == j && i == n) res.add(sb.toString());
        else {
            if (i > j) {
                sb.append(')');
                dfs(res, i, j + 1, n, sb);
                sb.deleteCharAt(sb.length() - 1);
            }
            if (i < n) {
                sb.append('(');
                dfs(res, i + 1, j, n, sb);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }


}
