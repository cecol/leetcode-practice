package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.List;

public class LC79WordSearch extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 沒過, 基本思路都沒想錯, dfs + boolean[][] v 拜訪過的記憶下去看
    // 但少在在 v 沒有重複利用, 原本以為 每次 new 1 個新的就好 -> 應該是有案例會出差錯
    // 要沿用同一個, 只是dfs 遞迴回來後 要記得 set 回 false 即可
    public boolean exist(char[][] board, String word) {
        int m = board.length, n = board[0].length;
        boolean[][] v = new boolean[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                if (dfs(board, word, i, j, 0, v)) return true;
            }
        return false;
    }

    boolean dfs(char[][] b, String w, int i, int j, int idx, boolean[][] v) {
        int m = b.length, n = b[0].length;
        if (i < 0 || j < 0 || i >= m || j >= n || v[i][j] || b[i][j] != w.charAt(idx)) return false;
        v[i][j] = true;
        if(idx == w.length()-1) return true;
        boolean v1 = dfs(b, w, i + 1, j, idx + 1, v);
        boolean v2 = dfs(b, w, i - 1, j, idx + 1, v);
        boolean v3 = dfs(b, w, i, j + 1, idx + 1, v);
        boolean v4 = dfs(b, w, i, j - 1, idx + 1, v);
        v[i][j] = false;
        return v1 || v2 || v3 || v4;
    }
}
