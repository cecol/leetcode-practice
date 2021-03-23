package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TreeSet;

public class LC529Minesweeper extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC529Minesweeper();
        LC.updateBoard(new char[][]{
                        {'E', 'E', 'E', 'E', 'E'},
                        {'E', 'E', 'M', 'E', 'E'},
                        {'E', 'E', 'E', 'E', 'E'},
                        {'E', 'E', 'E', 'E', 'E'}},
                new int[]{3, 0});
    }

    /**
     * 有解開, 花比較多時間理解踩地雷規則
     * faster than 100.00%, less than 37.31% of Java
     */
    public char[][] updateBoard(char[][] board, int[] click) {
        dfs(board, click[0], click[1]);
        return board;
    }

    int[][] adj = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {-1, 1}, {1, -1}, {-1, -1}};

    private void dfs(char[][] b, int x, int y) {
        int m = b.length, n = b[0].length;
        if (x < 0 || y < 0 || x >= m || y >= n) return;
        if (b[x][y] != 'E' && b[x][y] != 'M') return;
        if (b[x][y] == 'M') {
            b[x][y] = 'X';
            return;
        }
        int mc = 0;
        for (int[] a : adj) if (checkM(b, x + a[0], y + a[1])) mc++;
        if (mc > 0) {
            b[x][y] = (char) ('0' + mc);
        } else {
            b[x][y] = 'B';
            for (int[] a : adj) dfs(b, x + a[0], y + a[1]);
        }

    }

    private boolean checkM(char[][] b, int x, int y) {
        int m = b.length, n = b[0].length;
        if (x < 0 || y < 0 || x >= m || y >= n) return false;
        return b[x][y] == 'M' || b[x][y] == 'X';
    }
}
