package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC56MergeIntervals extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 秒解, 因為才處理過 InsertInterval
    // 關鍵: 要先排序, 接著拿出 cur, 接下來就是 cur 下去找, overlap 就是調整 cur, 沒有 overlap 就是加入結果
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (x, y) -> {
            return x[0] - y[0];
        });
        List<int[]> res = new ArrayList<>();
        int[] cur = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            if(cur[1] < intervals[i][0]) {
                res.add(cur);
                cur = intervals[i];
            } else {
                cur[0] = Math.min(cur[0], intervals[i][0]);
                cur[1] = Math.max(cur[1], intervals[i][1]);
            }
        }
        res.add(cur);
        return res.toArray(new int[res.size()][2]);
    }
}
