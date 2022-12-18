package leetcode202212.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.PriorityQueue;

public class LC803BricksFallingWhenHit extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC803BricksFallingWhenHit();
    }

    /**
     * https://leetcode.com/problems/bricks-falling-when-hit/solutions/137465/java-union-find-beats-100/
     * 這題其實是 DFS 比較好解, union find來解反而更複雜
     * 這題要時光回溯來看
     * https://github.com/wisdompeak/LeetCode/tree/master/DFS/803.Bricks-Falling-When-Hit
     * 1. 先把 grid 所有 hits[x/y] 全部打掉. 標記 grid[hist[x/y]] = -1
     * 2. 做一個 dfs, 會把 grid[i][j] = 1 都標成 2, 然後回傳標了總數
     * - 所以針對 grid[0][j] , 都下去 dfs 標 2, 因為初始化這些都是連接 top
     * 3. 針對 hits, 從尾巴開始 時光倒流 補放回去 看能有多少再繼續走過 dfs 可以填成 2
     * - if (grid[x][y] == 0) 這邊本來就沒有 brick, 有沒有打掉都是 0
     * - else - grid[x][y] = 1;
     * -      if (!isConnectToRoof(grid, x, y)) continue; 先看看是不是 4 周真的有連接到 grid[x/y] == 2 代表真的補上去會補更多 brick 回來
     * -      res[i] = dfs(grid, x, y) - 1; 看補多少 2 回來
     *
     * 總結 grid[i][j] == -1, 被打掉的磚頭, grid[i][j] == 2 有接上 top, grid[i][j] == 0 本來就空的,
     * grid[i][j] == 1, 補回來的要去 DFS 看能不能補回更多 2
     *
     * */
    public int[] hitBricks(int[][] grid, int[][] hits) {
        int k = hits.length, m = grid.length, n = grid[0].length;
        for (int i = 0; i < k; i++) {
            int x = hits[i][0], y = hits[i][1];
            if (grid[x][y] == 1) grid[x][y] = -1;
        }

        for (int j = 0; j < n; j++) dfs(grid, 0, j);

        int[] res = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            int x = hits[i][0], y = hits[i][1];
            if (grid[x][y] == 0) res[i] = 0;
            else {
                grid[x][y] = 1;
                if (!isConnectToRoof(grid, x, y)) continue;
                res[i] = dfs(grid, x, y) - 1;
            }
        }
        return res;
    }

    int[][] dirs = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    int dfs(int[][] grid, int i, int j) {
        if (grid[i][j] != 1) return 0;
        int m = grid.length, n = grid[0].length;
        grid[i][j] = 2;
        int count = 1;
        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (x < 0 || y < 0 || x >= m || y >= n || grid[x][y] != 1) continue;
            count += dfs(grid, x, y);
        }
        return count;
    }

    boolean isConnectToRoof(int[][] grid, int i, int j) {
        if (i == 0) return true;
        int m = grid.length, n = grid[0].length;
        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (x < 0 || y < 0 || x >= m || y >= n) continue;
            if (grid[x][y] == 2) return true;
        }
        return false;
    }
}
