package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.PriorityQueue;

public class LC435NonOverlappingIntervals extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC435NonOverlappingIntervals();
    }

    /**
     * 我覺得跟 LC 53 很像, 但是我沒有真正哩咧這題要比較得是什麼
     * 看到這個才理解
     * https://leetcode.com/problems/non-overlapping-intervals/discuss/91713/Java%3A-Least-is-Most
     * (也忘了 int[][] intervals是要用 Arrays.sort, 而非塞進 PriorityQueue)
     * 這題題意也可以看成是
     * Given a collection of intervals, find the maximum number of intervals that are non-overlapping.
     * 要用 end的觀點來思考, 相較 LC 53, 這邊只要保留最後的 end, (53 是保留當前 interval, overlap 就去擴增)
     * 如果要用 LC53保留 int[] -> 那麼就要再 overlap 時候 依據誰的 end比較遠來保留 int[]
     * 那就直接保留 end就好
     * */
    public int eraseOverlapIntervals(int[][] intervals) {
        if(intervals == null || intervals.length <= 1) return 0;
        Arrays.sort(intervals, (i1, i2) -> i1[1] - i2[1]);
        int preE = intervals[0][1];
        int c = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (preE > intervals[i][0]) {
                c++;
                preE = Math.min(preE, intervals[i][1]);
            } else {
                preE = intervals[i][1];
            }
        }
        return c;
    }
}
