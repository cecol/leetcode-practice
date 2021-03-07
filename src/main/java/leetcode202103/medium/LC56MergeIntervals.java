package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class LC56MergeIntervals extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC56MergeIntervals();
        LC.merge(new int[][]{{1, 4}, {5, 6}});
    }

    /**
     * 我一開始有想到要sort interval start, 但一直沒有想清楚透過sort之後一個個比對下去是正確的方向
     * 直到看到了答案
     * https://leetcode.com/problems/merge-intervals/discuss/21222/A-simple-Java-solution
     * 1. 先把 sort intervals by start
     * 2. assign newInterval = intervals[0], add into result
     * 3. 開始 iterate intervals, 看看當前拿出來的(因為經過sort, 所以最相近的最有可能overlap)有無辦法因為overlap 擴增newInterval的值
     * -> 1. 可以擴增 直接改 newInterval[1]
     * ->    因為newInterval 早就在 result裡面, 而且最先被加入的代表newInterval[0]也就是start 必然是最小
     * ->    只有因為 overlap去擴增 newInterval[1] , 擴增完也不用把 newInterval從新加入 result list, 因為一開始就加入了
     * ->    這裡是善用  newInterval reference in result 的特性
     * -> 2. 只有當 當前加入的 interval沒有 overlap -> 代表是新的 interval 開始
     * ->    assign newInterval = current non-overlap interval, add into result
     * ->    然後繼續往下一個iterate
     */
    public int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) return intervals;
        Arrays.sort(intervals, (i1, i2) -> Integer.compare(i1[0], i2[0]));
        List<int[]> res = new ArrayList<>();
        int[] newInterval = intervals[0];
        res.add(newInterval);
        for (int[] i : intervals) {
            if (i[0] <= newInterval[1]) newInterval[1] = Math.max(newInterval[1], i[1]);
            else {
                newInterval = i;
                res.add(newInterval);
            }
        }
        return res.toArray(new int[res.size()][]);
    }
}
