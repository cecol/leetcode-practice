package leetcode202104.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class LC289GameOfLife extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC289GameOfLife();
    }

    /**
     * 蠻直觀的一題
     * space O(n) 是無腦解
     * in-place的話就是用 -1=00,-2=01,-3=10,-4=11 encoded
     * first bit = old, second = new
     * 第一輪檢查周遭 如果是負數代該數被檢查過 得decode 回原本的
     * 第二輪全部decode
     * https://leetcode.com/problems/game-of-life/discuss/73223/Easiest-JAVA-solution-with-explanation
     * */
    public void gameOfLife(int[][] board) {
        int m = board.length, n = board[0].length;
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}, {1, 1}, {-1, 1}, {1, -1}, {-1, -1}};
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int count = 0;
                for (int[] d : dirs) {
                    int mx = i + d[0];
                    int my = j + d[1];
                    if (mx >= 0 && mx < m && my >= 0 && my < n) {
                        if (board[mx][my] == 1 || Math.abs(board[mx][my]) > 2) count++;
                    }
                }
                if (board[i][j] == 1) {
                    if (count < 2 || count > 3) board[i][j] = -3;
                    else board[i][j] = -4;
                } else {
                    if (count == 3) board[i][j] = -2;
                    else board[i][j] = -1;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == -1 || board[i][j] == -3) board[i][j] = 0;
                else board[i][j] = 1;
            }
        }
    }
}
