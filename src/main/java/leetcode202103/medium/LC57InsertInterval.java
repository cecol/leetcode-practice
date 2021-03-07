package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC57InsertInterval extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC57InsertInterval();
        LC.insert(new int[][]{{1, 3}, {6, 9}}, new int[]{2, 5});
    }

    /**
     * 因為解過56大概知道無腦解法, 但後來想太多(還想到用binary search去找插入點)
     * 人家答案的思路比較簡單明瞭
     * https://leetcode.com/problems/insert-interval/discuss/21602/Short-and-straight-forward-Java-solution
     * 1. 找到index, 在那之前的 interval的 end 都沒有包到 newInterval start -> 我一開始沒想到這一步就走歪了 -> intervals[i][1] < newInterval[0]
     * 2. 找到 overlap in newInterval -> intervals[i][0] < newInterval[1] -> 更新 newInterval
     * 3. 加入 newInterval, 把剩下尾的intervals加入
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList<>();
        int i = 0;
        while (i < intervals.length && intervals[i][1] < newInterval[0]) res.add(intervals[i++]);
        while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        res.add(newInterval);
        while (i < intervals.length) res.add(intervals[i++]);
        return res.toArray(new int[res.size()][2]);
    }
}
