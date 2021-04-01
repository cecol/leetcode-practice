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
     * 我覺得跟 LC 53 很像, 但是我沒有真正理解這題要比較得是什麼
     * 看到這個才理解
     * https://leetcode.com/problems/non-overlapping-intervals/discuss/91713/Java%3A-Least-is-Most
     * (也忘了 int[][] intervals是要用 Arrays.sort, 而非塞進 PriorityQueue)
     * 這題題意也可以看成是
     * Given a collection of intervals, find the maximum number of intervals that are non-overlapping.
     * 要用 end的觀點來思考, 相較 LC 53, 這邊只要保留最後的 end, (53 是保留當前 interval, overlap 就去擴增)
     * 如果要用 LC53保留 int[] -> 那麼就要再 overlap 時候 依據誰的 end比較遠來保留 int[]
     * 那就直接保留 end就好
     * 關鍵在於
     * if (preE > intervals[i][0]) 代表目前有 overlap
     * -> preE = Math.min(preE, intervals[i][1]); 留下來的 end 是拿min end, 為什麼？
     * 因為比較大的end 的interval 是要拿出來被刪掉的, 如果保留比較大的 end 的interval去往後找只會找到更多overlap
     * 所以取小的 end , 然後繼續往後比較
     * 因為是要刪除最少的 intervals 所以是 greedy: 主要邏輯是
     * 1. 因為是透過 end > begin 來判定有無 overlap, 又加上是先刪除overlap中小的 end -> 所以排序是用 end 由小來到大
     * -> 排序過後的  intervals[0] 一定是會被保留的那一個, 就算他跟別人 overlap -> 但因為他是最早 end -> 所以後面的end只會比他大於等於
     * -> 因為排序 end 由小排到大 -> 所以overlap 的 intervals 才會兩倆放在一起
     *
     * 後來發現 preE = Math.min(preE, intervals[i][1]); 可以拿掉也可以過
     * 因為排序過了 後面的intervals[i][1]一定 >= preE
     * 所以 greedy去刪除 end大的那一個, 只保留 overlap中 end小的(這樣才會不overlap)
     * */
    public int eraseOverlapIntervals(int[][] intervals) {
        if(intervals == null || intervals.length <= 1) return 0;
        Arrays.sort(intervals, (i1, i2) -> i1[1] - i2[1]);
        int preE = intervals[0][1];
        int c = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (preE > intervals[i][0]) {
                c++;
            } else {
                preE = intervals[i][1];
            }
        }
        return c;
    }
}
