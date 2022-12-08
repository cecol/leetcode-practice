package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

public class LC959RegionsCutBySlashes extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC959RegionsCutBySlashes();
    }

    /**
     * https://leetcode.com/problems/regions-cut-by-slashes/discuss/205719/Mark-the-boundary-and-then-the-problem-become-Number-of-Islands-(dfs-or-bfs)
     * 想不到這題是把 / or \ 拆成像素來看聯通的地區 - 變成 number of islands
     * / 變成
     * 0 0 1
     * 0 1 0
     * 1 0 0
     * \ 變成
     * 1 0 0
     * 0 1 0
     * 0 0 1
     *
     * 用 1d, 2d 都會有問題, 3d, 4d... 可以
     * 轉換完變成這樣
     * 0 0 1 0 0 1
     * 0 1 0 0 1 0
     * 1 0 0 1 0 0
     * 0 0 1 0 0 0
     * 0 1 0 0 0 0
     * 1 0 0 0 0 0
     * 相連的 0 就是 相連區域
     * 把所有 0 填成 1 就是答案
     * */
    public int regionsBySlashes(String[] grid) {
        int n = grid.length;
        int res = 0;
        int[][] g = new int[3 * n][3 * n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                if (grid[i].charAt(j) == '/') {
                    g[i * 3][j * 3 + 2] = 1;
                    g[i * 3 + 1][j * 3 + 1] = 1;
                    g[i * 3 + 2][j * 3] = 1;
                } else if (grid[i].charAt(j) == '\\') {
                    g[i * 3][j * 3] = 1;
                    g[i * 3 + 1][j * 3 + 1] = 1;
                    g[i * 3 + 2][j * 3 + 2] = 1;
                }
            }

        for (int i = 0; i < 3 * n; i++)
            for (int j = 0; j < 3 * n; j++) {
                if (g[i][j] == 0) {
                    dfs(g, i, j);
                    res++;
                }
            }
        return res;
    }

    void dfs(int[][] g, int i, int j) {
        if (i < 0 || j < 0 || i >= g.length || j >= g.length || g[i][j] != 0) return;
        g[i][j] = 1;
        dfs(g, i + 1, j);
        dfs(g, i, j + 1);
        dfs(g, i - 1, j);
        dfs(g, i, j - 1);
    }
}
