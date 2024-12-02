package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.Arrays;

public class LC435NonOverlappingIntervals extends BasicTemplate {
    public static void main(String[] args) {
    }

    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) return 0;
        Arrays.sort(intervals, (x, y) -> {
            return x[1] - y[1];
        });
        int end = intervals[0][1];
        int res = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < end) res++;
            else end = intervals[i][1];
        }
        return res;
    }
}
