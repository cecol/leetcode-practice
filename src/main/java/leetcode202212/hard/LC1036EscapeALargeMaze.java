package leetcode202212.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC1036EscapeALargeMaze extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1036EscapeALargeMaze();
    }

    /**
     * 這題第一次看到會矇吧
     * https://leetcode.com/problems/escape-a-large-maze/solutions/282849/python-bfs-and-dfs-the-whole-problem-is-broken/
     * 100萬邊界的地圖, 不可能一步一步走到終點
     * 關鍵在於 0 <= blocked.length <= 200
     * 等擋住的最大區塊只能 200 個, 等於說 能擋住的最大範圍是
     * The sum of the area available equals 1+2+3+4+5+...+198+199=(1+199)*199/2=19900 (trapezoid sum)
     * 0th     _________________________
     * -        |-------------------- X
     * -        |-------------------X
     * -        |                .
     * -        |             .
     * -        .           .
     * -        .        X
     * -        .    X
     * 200      | X
     *
     * 19900,
     * 所以如果 從 source 出發能 visited
     * 1. 踩到 target
     * 2. 或者 visited.size() >= 19900
     * 就一定可以踩到 target
     * case 2 一定是圍不住了
     *
     * 但如果是為不住情況得 bfs source -> target && bfs target -> source
     * 因為他可能是圍在終點!!  所以另一邊也要檢查
     *
     * 最後出界 case 還是要檢查
     * if (mx < 0 || my < 0 || mx >= limit || my >= limit) continue;
     * limit = 1000000
     * 為什麼? 因為可能給的起始點就是 999999, 不設定邊界, 一定走到出界還不知道
     * */
    int[][] dirs = new int[][]{{0, 1}, {-1, 0}, {0, -1}, {0, 1}};
    int limit = (int) 1e6;

    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        Set<String> blocks = new HashSet<>();
        for (int[] block : blocked) {
            blocks.add(block[0] + ":" + block[1]);
        }
        return bfs(source, target, blocks) && bfs(target, source, blocks);
    }

    boolean bfs(int[] source, int[] target, Set<String> blocks) {
        Set<String> seen = new HashSet<>();
        seen.add(source[0] + ":" + source[1]);
        Queue<int[]> bfs = new LinkedList<>();
        bfs.offer(source);
        while (bfs.size() > 0) {
            int[] cur = bfs.poll();

            for (int[] dir : dirs) {
                int mx = cur[0] + dir[0];
                int my = cur[1] + dir[1];
                if (mx < 0 || my < 0 || mx >= limit || my >= limit) continue;
                String k = mx + ":" + my;
                if (seen.contains(k) || blocks.contains(k)) continue;
                if (mx == target[0] && my == target[1]) return true;
                bfs.offer(new int[]{mx, my});
                seen.add(k);
            }
            if (seen.size() == 20000) return true;
        }
        return false;
    }
}
