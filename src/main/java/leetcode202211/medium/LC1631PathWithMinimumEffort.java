package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC1631PathWithMinimumEffort extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1631PathWithMinimumEffort();
    }

    /**
     * 這題有 Bellman Ford and Dijkstra 解法
     * 基本上兩個之前移動的距離就是 高度差, Dijkstra 往最低高度差一直走
     * <p>
     * 但如果用 binary search 來走的話
     * https://leetcode.com/problems/path-with-minimum-effort/discuss/909002/JavaPython-3-3-codes%3A-Binary-Search-Bellman-Ford-and-Dijkstra-w-brief-explanation-and-analysis.
     * 就是先界定邊界
     * l = 最低高度差 0
     * r = 最高高度差 max
     * <p>
     * 然後找 mid 下去 走看看
     * 但是走的過程不能用dfs 會走不完
     * 用 bfs 走到終點就可以
     * <p>
     * if (mx >= 0 && mx < m && my >= 0 && my < n && effort >= Math.abs(h[x][y] - h[my][my]) && seen.add(mx * n + my))
     * 這邊我卡超久 關鍵在於
     * <p>
     * 要先比過 effort >= Math.abs(h[x][y] - h[my][my])
     * 才可以放 seen.add(mx * n + my)
     * <p>
     * 如果先 seen.add(mx * n + my) && effort >= Math.abs(h[x][y] - h[my][my])
     * 會錯
     * 因為可能比不過, 所以當下沒走, 但之後可以回來走 !!
     */
    public int minimumEffortPath(int[][] heights) {
        int min = 10000000, max = 0;
        for (int[] r : heights)
            for (int h : r) {
                min = Math.min(min, h);
                max = Math.max(max, h);
            }

        int l = 0, r = max - min;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (hasRoute(heights, mid)) r = mid;
            else l = mid + 1;
        }
        return l;
    }


    int[][] dirs = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    boolean hasRoute(int[][] h, int effort) {
        int m = h.length, n = h[0].length;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0});
        Set<Integer> seen = new HashSet<>();
        seen.add(0);
        while (q.size() > 0) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1];
            if (x == m - 1 && y == n - 1) return true;
            for (int[] d : dirs) {
                int mx = x + d[0], my = y + d[1];
                if (0 <= mx && mx < m && 0 <= my && my < n && effort >= Math.abs(h[mx][my] - h[x][y]) && seen.add(mx * n + my))
                    q.offer(new int[]{mx, my});
            }
        }
        return false;
    }

}
