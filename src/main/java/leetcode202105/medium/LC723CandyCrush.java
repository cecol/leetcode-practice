package leetcode202105.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC723CandyCrush extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC723CandyCrush();
    }

    /**
     * https://leetcode.com/problems/candy-crush/discuss/178366/Another-Java-Solution
     * 優化解法, code很好懂, 幾個重點:
     * 1. 分開檢查 vertical && horizontal, 只要檢查到 ex:
     * -> horizontal: int j = 0; j < n - 2 -> 往後面檢查2格就好
     * 2. 一致的都改成 negative, 後續繼續用Math.abs比對
     * 3. drop 用比較有效率的方式, 一批往前移動, 剩下補0
     * 4. 遞迴, 如果還沒跑完就在跑一次
     */
    public int[][] candyCrush(int[][] board) {
        int m = board.length, n = board[0].length;
        boolean tryRun = false;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n - 2; j++) {
                int v = Math.abs(board[i][j]);
                if (v > 0 && Math.abs(board[i][j + 1]) == v && Math.abs(board[i][j + 2]) == v) {
                    board[i][j] = board[i][j + 1] = board[i][j + 2] = -v;
                    tryRun = true;
                }
            }
        }

        for (int i = 0; i < m - 2; i++) {
            for (int j = 0; j < n; j++) {
                int v = Math.abs(board[i][j]);
                if (v > 0 && Math.abs(board[i + 1][j]) == v && Math.abs(board[i + 2][j]) == v) {
                    board[i][j] = board[i + 1][j] = board[i + 2][j] = -v;
                    tryRun = true;
                }
            }
        }

        for (int j = 0; j < n; j++) {
            int r = m - 1;
            for (int i = m - 1; i >= 0; i--) {
                if (board[i][j] >= 0) {
                    board[r--][j] = board[i][j];
                }
            }
            for (int i = r; i >= 0; i--) board[i][j] = 0;
        }

        return tryRun ? candyCrush(board) : board;
    }

    /**
     * Mock test 暴力解出來 Runtime: 56 ms, faster than 5.02%
     * 很直觀的先找出 joined的數字, 然後一一清理, 然後drop, 然後看有無找到過 joined, 來判斷是否再跑一圈看看
     * 應該有很多細節可以優化
     */
    public int[][] candyCrushSlow(int[][] board) {
        int m = board.length, n = board[0].length;
        boolean tryRun = true;
        while (tryRun) {
            tryRun = false;
            Set<String> vjoined = new HashSet<>();
            Set<String> hjoined = new HashSet<>();
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == 0) continue;
                    String k = String.valueOf(i) + "," + j;
                    if (!hjoined.contains(k)) {
                        List<String> horj = new ArrayList<>();
                        horj.add(k);
                        for (int h = j - 1; h >= 0; h--)
                            if (board[i][h] == board[i][h + 1]) horj.add(String.valueOf(i) + "," + h);
                            else break;
                        for (int h = j + 1; h < n; h++)
                            if (board[i][h] == board[i][h - 1]) horj.add(String.valueOf(i) + "," + h);
                            else break;
                        if (horj.size() >= 3) for (String h : horj) hjoined.add(h);
                    }
                    if (!vjoined.contains(k)) {
                        List<String> verj = new ArrayList<>();
                        verj.add(k);
                        for (int v = i - 1; v >= 0; v--)
                            if (board[v][j] == board[v + 1][j]) verj.add(String.valueOf(v) + "," + j);
                            else break;
                        for (int v = i + 1; v < m; v++)
                            if (board[v][j] == board[v - 1][j]) verj.add(String.valueOf(v) + "," + j);
                            else break;
                        if (verj.size() >= 3) for (String v : verj) vjoined.add(v);
                    }
                }
            }

            if (vjoined.size() > 0) {
                for (String v : vjoined) {
                    String[] vij = v.split(",");
                    board[Integer.parseInt(vij[0])][Integer.parseInt(vij[1])] = 0;
                }
            }

            if (hjoined.size() > 0) {
                for (String h : hjoined) {
                    String[] hij = h.split(",");
                    board[Integer.parseInt(hij[0])][Integer.parseInt(hij[1])] = 0;
                }
            }
            if (vjoined.size() > 0 || hjoined.size() > 0) drop(board);

            tryRun = vjoined.size() > 0 || hjoined.size() > 0;
        }
        return board;
    }

    private void drop(int[][] board) {
        int m = board.length, n = board[0].length;
        for (int j = 0; j < n; j++) {
            for (int i = m - 1; i >= 0; i--) {
                if (board[i][j] == 0) {
                    int idx = i - 1;
                    for (; idx >= 0; idx--) if (board[idx][j] > 0) break;
                    if (idx >= 0 && board[idx][j] > 0) {
                        board[i][j] = board[idx][j];
                        board[idx][j] = 0;
                    }

                }
            }
        }
    }
}
