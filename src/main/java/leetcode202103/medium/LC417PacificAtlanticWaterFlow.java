package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC417PacificAtlanticWaterFlow extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC417PacificAtlanticWaterFlow();
        LC.pacificAtlantic(new int[][]{
                {1, 2, 2, 3, 5},
                {3, 2, 3, 4, 4},
                {2, 4, 5, 3, 1},
                {6, 7, 1, 4, 5},
                {5, 1, 1, 2, 4}
        });
    }

    /**
     * 我有想到要用兩個 boolean[][] 來個別記載 pacific atlantic 能走到的可能性, 然後看兩著都 true就是要的座標
     * 但花太多時間序想DFS細節也沒想好, 只好直接參考別人比較精簡的答案
     * https://leetcode.com/problems/pacific-atlantic-water-flow/discuss/90733/Java-BFS-and-DFS-from-Ocean
     * 基本上重點就是
     * 1.
     * boolean[][] p = new boolean[m][n];
     * boolean[][] a = new boolean[m][n];
     * 2.
     * dfs(int[][] matrix, boolean[][] v, int h, int r, int c)
     * 主要是透過 傳入的 boolean[][] 與 int h
     * 如果當前的 matrix[r][c] < h 也就不用看了 or v[r][c] == true 也不用看了
     * initial case dfs(matrix, p, Integer.MIN_VALUE, i, 0); 邊境要比較的 int h 直接設定成 MIN_VALUE
     */
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return res;
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] p = new boolean[m][n];
        boolean[][] a = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            dfs(matrix, p, Integer.MIN_VALUE, i, 0);
            dfs(matrix, a, Integer.MIN_VALUE, i, n - 1);
        }
        for (int i = 0; i < n; i++) {
            dfs(matrix, p, Integer.MIN_VALUE, 0, i);
            dfs(matrix, a, Integer.MIN_VALUE, m - 1, i);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (p[i][j] && a[i][j]) res.add(List.of(i, j));
            }
        }
        return res;
    }

    private void dfs(int[][] matrix, boolean[][] v, int h, int r, int c) {
        int m = matrix.length;
        int n = matrix[0].length;
        if (r < 0 || c < 0 || r >= m || c >= n || v[r][c] || matrix[r][c] < h) return;
        v[r][c] = true;
        dfs(matrix, v, matrix[r][c], r + 1, c);
        dfs(matrix, v, matrix[r][c], r - 1, c);
        dfs(matrix, v, matrix[r][c], r, c + 1);
        dfs(matrix, v, matrix[r][c], r, c - 1);
    }
}
