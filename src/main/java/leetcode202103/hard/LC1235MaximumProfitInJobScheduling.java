package leetcode202103.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class LC1235MaximumProfitInJobScheduling extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1235MaximumProfitInJobScheduling();
    }

    /**
     * 這題我真的沒什麼想法
     * https://leetcode.com/problems/maximum-profit-in-job-scheduling/discuss/409009/JavaC%2B%2BPython-DP-Solution
     * 沒想到這題就是背包問題
     * 1. dp[time] = profit, dp[i] 代表 i 的時間點, 所能取得的最大profit
     * 2. dp[0] = 0 , 0 time 自然也沒job可作沒profit可得
     * 3. 背包問題的概念
     * -> If we don't do this job, nothing will be changed.
     * -> If we do this job, binary search in the dp to find the largest profit we can make before start time s.
     * -> So we also know the maximum current profit that we can make doing this job.
     * 4. 用 TreeMap, Key = time, value = profit,
     * 5. jobs 先用 end time來排序, 從最早的開始來看
     * -> int cur = dp.floorEntry(j[0]).getValue() + j[2]; 先算出如果要做當前job 會得到多少profit
     * -> j[0] 就是當前 Job 的 start time -> dp.floorEntry(j[0]) 找出這個start time的前一個end time(才可以接著做)
     * -> 所以拿前一個end time 的 value(所以就是該end time時的sub optimized profit) + 當前job的 profit: j[2]
     * -> if (cur > dp.lastEntry().getValue()) dp.put(j[1], cur); 如果當前job 有做的profit勝於沒做
     * -> 沒做就是 dp.lastEntry() -> last end time -> 最近一次的profit
     * -> 就複寫上去
     * 所以 dp.lastEntry() 都是目前的最佳 profit
     */
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        int[][] jobs = new int[n][3];
        for (int i = 0; i < n; i++) {
            jobs[i] = new int[]{startTime[i], endTime[i], profit[i]};
        }
        Arrays.sort(jobs, (x, y) -> x[1] - y[1]);
        TreeMap<Integer, Integer> dp = new TreeMap<>();
        dp.put(0, 0);
        for (int[] j : jobs) {
            int cur = dp.floorEntry(j[0]).getValue() + j[2];
            if (cur > dp.lastEntry().getValue()) dp.put(j[1], cur);
        }
        return dp.lastEntry().getValue();
    }
}
