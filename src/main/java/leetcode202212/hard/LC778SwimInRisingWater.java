package leetcode202212.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class LC778SwimInRisingWater extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC778SwimInRisingWater();
    }

    /**
     * https://leetcode.com/problems/swim-in-rising-water/solutions/113754/concise-solution-with-only-priorityqueue-nlogn-in-java/
     * 所這題其實跟 LC407TrappingRainWaterII 觀念一模一樣
     * 但不熟悉就還是會卡住  卡在哪?
     * 1. 錯誤以為很像一般 bfs ㄧ level 一 level 下去擴增, 然後 level 就是 t time 水增高的次序
     * 2. 以為 t 要從 0 開始找, 根本不是, 低消是 grid[0][0] 起點, 因為如果 [0,0] 高度是 5, 也要等到至少 t=5 才可以開始往其他地方遊
     * 不然就算旁邊比較矮會摔死
     *
     * 這類型 bfs 重點在於 bfs 是基於 PriorityQueue, 常見 bfs 都是離近的點擴增
     * 但這類型問題都是有一些特殊條件, 比如說可以跳格 這題有提到  You can swim infinite distances in zero time.
     * 只要水高度有到 你可以到處任遊
     *
     * 所以 minheap 就是從最低消(最低高度, 最低水位) 開始擴增,
     * 遇到下一個如果是高的, 沒關係, 加入 minheap 會很後面才出來
     * 遇到矮的, 還是用目前低消 加入 min heap, min heap 只會越加入越大, 只要在這些大的出現前 bfs 踩到終點就好
     * 沒踩到就得 把這些大的 poll 出來, 代表得透過大的才會走到終點
     *
     * */
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        boolean[][] visited = new boolean[n][n];
        visited[0][0] = true;
        PriorityQueue<int[]> bfs = new PriorityQueue<>(Comparator.comparingInt(x -> x[2]));
        bfs.offer(new int[]{0, 0, grid[0][0]});
        int[][] dirs = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        while (bfs.size() > 0) {
            int[] v = bfs.poll();
            if (v[0] == n - 1 && v[1] == n - 1) return v[2];
            for (int[] dir : dirs) {
                int x = v[0] + dir[0];
                int y = v[1] + dir[1];
                if (x < 0 || y < 0 || x >= n || y >= n || visited[x][y]) continue;
                visited[x][y] = true;
                bfs.offer(new int[]{x, y, Math.max(grid[x][y], v[2])});
            }
        }
        return -1;
    }
}
