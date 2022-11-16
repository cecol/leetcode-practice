package leetcode202211.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC699FallingSquares extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC699FallingSquares();
    }

    /**
     * https://leetcode.com/problems/falling-squares/discuss/108766/Easy-Understood-O(n2)-Solution-with-explanation
     * 沒想到直觀暴力解也是可以 O(N^2)
     * 就是用 interval 概念來看
     * interval int[3]
     * 0 - start
     * 1 - end
     * 2 - 基礎 height
     * <p>
     * maintain 一個 intervals list, 每當有個 cur position
     * 就去走遍 intervals
     * 尋找有交界的 intervals 中 max height
     * if (i[1] <= cur[0]) continue; 沒交界 在右邊
     * if (i[0] >= cur[1]) continue; 沒交界 在左邊
     * 然後 cur height += max height
     * <p>
     * 最後 cur height 跟當前 max height 比較加入結果
     *
     * 這 O(N^2) 可以再優化成 O(N logN) 就是用 binary search 找
     * 但如果要更快可以 segment tree, 但太複雜了...
     */
    public List<Integer> fallingSquares(int[][] positions) {
        List<int[]> intervals = new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        int h = 0;
        for (int[] p : positions) {
            int[] cur = new int[]{p[0], p[0] + p[1], p[1]};
            int preMaxH = 0;
            for (int[] i : intervals) {
                if (i[1] <= cur[0]) continue;
                if (i[0] >= cur[1]) continue;
                preMaxH = Math.max(preMaxH, i[2]);
            }
            cur[2] += preMaxH;
            intervals.add(cur);
            h = Math.max(h, cur[2]);
            res.add(h);
        }
        return res;
    }
}
