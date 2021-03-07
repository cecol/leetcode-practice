package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC79WordSearch extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC79WordSearch();
    }

    /**
     * 有解開, 但速度很慢R -> 62 ms, faster than 9.63% of Java
     * 但我看其他解答也沒比較快, 頂多沒有用到 boolean[][] v = new boolean[m][n];
     * 而是用 xor char[][] board, 但好像沒什麼特別, 省一點空間罷了
     * */
    int m = 0, n = 0;
    public boolean exist(char[][] board, String word) {
        m = board.length;
        n = board[0].length;
        boolean[][] v = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (go(i, j, v, board, 0, word)) return true;
            }
        }
        return false;
    }

    private boolean go(int i, int j, boolean[][] v, char[][] b, int idx, String w) {
        boolean r = false;
        if (i < 0 || j < 0 || i == m || j == n) return r;
        if (v[i][j]) return r;
        if (b[i][j] == w.charAt(idx)) {
            if (idx == w.length() - 1) r = true;
            else {
                v[i][j] = true;
                r = go(i + 1, j, v, b, idx + 1, w) ||
                        go(i - 1, j, v, b, idx + 1, w) ||
                        go(i, j + 1, v, b, idx + 1, w) ||
                        go(i, j - 1, v, b, idx + 1, w);
                v[i][j] = false;
            }
        }
        return r;
    }
}
