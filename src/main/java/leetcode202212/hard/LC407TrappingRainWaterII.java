package leetcode202212.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class LC407TrappingRainWaterII extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC407TrappingRainWaterII();
    }

    /**
     * https://leetcode.com/problems/trapping-rain-water-ii/solutions/89461/java-solution-using-priorityqueue/
     * 這題主要關鍵是在 從外圍, 從矮的開始網內 淹水 進去看
     * https://github.com/wisdompeak/LeetCode/tree/master/BFS/407.Trapping-Rain-Water-II
     * 所以要準備一個 PriorityQueue<int[]> bfs, int[] = {x, y, height} -> 由矮到高去排
     * 其中 height 是來自 parent 帶進來的值
     *
     * 就是說 bfs 先把 外圍座標都放進去, 依高度 [矮到高] 取出來
     * 關鍵在這, 因為先把所有周圍都加進來了, 所以之後 bfs 遇到 unvisited 都是周圍以內的
     * 我們又是從周圍最小的開始取, 所以遇到 更矮的自然可以填水
     *
     * 取出來的 int[] v = bfs.poll(); 往 4 周圍走, 檢查 visited
     * 1. res += Math.max(0, v[2] - height[x][y]); 新踩到座標如果是矮的  就填水, 如果是高的 就無法填水
     * 2. bfs.offer(new int[]{x, y, Math.max(v[2], height[x][y])});, 帶入兩者誰最高
     * 這邊最關鍵
     * 因為如果 [x,y] 是矮的, 那就會被填差距, 而 [x,y] 帶下去會是 高的 邊 v[2]
     * 因為我們都是取最矮的開始 bfs, 所以當被取出來的 一定是當前最矮的, 他走到誰比他矮, 就一定能填水！！
     * 然後 [x,y] 是矮的, 不用在更矮了, 因為最周圍都比[x,y]高(因為 min heap), 所以 [x,y] 帶入的高度是 v[2], 不是自己！！
     * 之後 [x,y] 適用 v[2] 去填其他格, 不會是自己的高度
     *
     * 如果是 [x,y] 高的  自然不會填水, [x,y] 帶入的高度是自己, [x,y] 進入 min heap 也只會在很後面被拿出來
     * 當這個 [x,y] 被拿出來時候, 它已經是走過中最矮, 所以他在 unvisited 遇到更矮就一定可以填水
     *
     * 所以最關鍵核心概念是
     * 1. min heap, 當前拿出來的是最矮!!, bfs 遇到更矮一定是可以填水,
     * - 更矮的是帶高的進入 bfs, 因為周圍最矮都圍起來了 周圍最矮已經保底, 所以周圍最矮會一路帶入 bfs 保底可以填的高度
     * 2. 周圍先加入 bfs, bfs 下一格矮的一定都可以填
     * */
    public int trapRainWater(int[][] height) {
        if (height == null || height.length == 0 || height[0].length == 0) return 0;
        PriorityQueue<int[]> bfs = new PriorityQueue<>(Comparator.comparingInt(x -> x[2]));
        int m = height.length, n = height[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            visited[i][0] = true;
            visited[i][n - 1] = true;
            bfs.offer(new int[]{i, 0, height[i][0]});
            bfs.offer(new int[]{i, n - 1, height[i][n - 1]});
        }
        for (int i = 0; i < n; i++) {
            visited[0][i] = true;
            visited[m - 1][i] = true;
            bfs.offer(new int[]{0, i, height[0][i]});
            bfs.offer(new int[]{m - 1, i, height[m - 1][i]});
        }
        int res = 0;
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (bfs.size() > 0) {
            int[] v = bfs.poll();
            for (int[] dir : dirs) {
                int x = v[0] + dir[0];
                int y = v[1] + dir[1];
                if (x >= 0 && x < m && y >= 0 && y < n && !visited[x][y]) {
                    res += Math.max(0, v[2] - height[x][y]);
                    visited[x][y] = true;
                    bfs.offer(new int[]{x, y, Math.max(v[2], height[x][y])});
                }
            }
        }
        return res;
    }
}
