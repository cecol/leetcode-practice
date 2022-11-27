package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class LC1102PathWithMaximumMinimumValue extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");

    }

    /**
     * https://leetcode.com/problems/path-with-maximum-minimum-value/solution/
     * 其實解答蠻直觀的, 但不會想到原來是可以這樣解
     * 就是設定 l = 0, r = Math.min(grid[0][0], grid[m - 1][n - 1]);
     * 來 binary search 找出可能的最大值
     * 所以當算出 mid, 就拿這個 mid 下去 BFS 走看看能不能走到 grid[m - 1][n - 1]
     * 途中只要 grid[i][j] < mid 就不 BFS
     * <p>
     * 如果可以走到  就是 left 往 mid 延伸
     * 不行就是 right 往 mid 縮減
     * - if (possible(grid, mid)) l = mid;
     * - else r = mid - 1;
     *
     * 但這邊有個替別地方是
     * 得用 int mid = r - (r - l) / 2; or int mid = l + (r - l) / 2 + 1;
     * 如果用 int mid = l + (r - l) / 2; 會 TLE
     * https://leetcode.com/problems/path-with-maximum-minimum-value/solution/1328396
     * 通常都是 r = mid, l = mid + 1
     * 但這次是反過來
     *
     * 我在想通常在寫 if(mid >= target) r = mid; else l = mid+1
     * 因為是往 left 縮小 inclusively, 另一邊是完全條件不符, 往 right exclusively 變大
     *
     * 但這次是 if(mid == target) l = mid; else r = mid-1
     * 因為是往 right 變大 inclusively, 另一邊是完全條件不符, 往 left exclusively 縮小
     *
     * 關鍵在於當剩下 2 個 elements Ex: [3,4] 你要找 smallest vs largest
     * 再選 mid 是差別很大
     * int mid = r - (r - l) / 2 => 會選到 4
     * - if(mid >= target) r = mid; else l = mid+1 會又回到 Ex: [3,4] -> TLE -> 根本沒縮減到
     * int mid = l + (r - l) / 2 => 會選到 3
     * - 配上 if(mid == target) l = mid; else r = mid-1 會又回到 Ex: [3,4] -> TLE -> 根本沒縮減到
     *
     * 更細節解釋要到筆記
     * https://docs.google.com/document/d/1w82mOSTYuXj5cI_YXlYqQrerrxaBAbc0Lv0YiF4LbOs/edit#heading=h.35bgdc9ojh0o
     *
     * 更多更關鍵在 binary search 101 - https://leetcode.com/problems/binary-search/discuss/423162/Binary-Search-101-The-Ultimate-Binary-Search-Handbook
     */
    public int maximumMinimumPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int l = 0, r = Math.min(grid[0][0], grid[m - 1][n - 1]);
        while (l < r) {
            int mid = r - (r - l) / 2;
            if (possible(grid, mid)) l = mid;
            else r = mid - 1;
        }
        return l;
    }

    int[][] dirs = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    boolean possible(int[][] grid, int mid) {
        int m = grid.length, n = grid[0].length;
        Queue<int[]> bfs = new LinkedList<>();
        bfs.offer(new int[]{0, 0});
        boolean[][] visited = new boolean[m][n];
        visited[0][0] = true;
        while (bfs.size() > 0) {
            int[] v = bfs.poll();
            if (v[0] == m - 1 && v[1] == n - 1) return true;
            for (int[] dir : dirs) {
                int mx = v[0] + dir[0];
                int my = v[1] + dir[1];
                if (mx >= 0 && my >= 0 && mx < m && my < n && !visited[mx][my] && grid[mx][my] >= mid) {
                    visited[mx][my] = true;
                    bfs.offer(new int[]{mx, my});
                }
            }
        }
        return false;
    }
}
