package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;

public class LC57InsertInterval extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 有想起基本架構 頭尾比較 來找出 overlap & ArrayList 收集結果, 但卡在用一個 while
    // 回去看以前的筆記才想起來用 3 個 while 就可以解了
    // while1 處理前面一段, while2 處理 overlap, while3 處理尾巴
    public int[][] insert(int[][] intervals, int[] newInterval) {
        ArrayList<int[]> res = new ArrayList<>();
        int i = 0;
        while (i < intervals.length && intervals[i][1] < newInterval[0]) res.add(intervals[i++]);
        while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
            newInterval[1] = Math.max(intervals[i++][1], newInterval[1]);
        }
        res.add(newInterval);
        while (i < intervals.length) res.add(intervals[i++]);
        return res.toArray(new int[res.size()][2]);
    }
}
