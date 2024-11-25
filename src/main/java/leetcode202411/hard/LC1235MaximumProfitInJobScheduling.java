package leetcode202411.hard;


import leetcode20200921to20201031.BasicTemplate;

import java.util.Arrays;
import java.util.TreeMap;

public class LC1235MaximumProfitInJobScheduling extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 沒過, 有知道 TreeMap<Integer, Integer> tm + int[][] jobs
    // 1. 排序錯了, 用 End time 排, 因為是 end time 決勝負
    // 2. tm 要先擺 0,0 當作 DP 的底
    // 3. 用 tm floor 要下界, 算出當前獲利
    // 4. 當前獲利 > tm.last 就是寫入
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int[][] jobs = new int[startTime.length][3];
        for (int i = 0; i < startTime.length; i++) {
            jobs[i][0] = startTime[i];
            jobs[i][1] = endTime[i];
            jobs[i][2] = profit[i];
        }
        Arrays.sort(jobs, (x, y) -> x[1] - y[1]);
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        tm.put(0, 0);
        for (int[] j : jobs) {
            int cur = tm.floorEntry(j[0]).getValue() + j[2];
            if (cur > tm.lastEntry().getValue()) tm.put(j[1], cur);
        }
        return tm.lastEntry().getValue();
    }
}
