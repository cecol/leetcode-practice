package leetcode202009.medium;

import leetcode202009.BasicTemplate;

import java.util.PriorityQueue;
import java.util.TreeMap;

public class LC621TaskScheduler extends BasicTemplate {

    public static void main(String[] args) {
        var LC = new LC621TaskScheduler();
        LC.leastIntervalOptimize(new char[]{'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G'}, 2);
        LC.leastIntervalOptimize(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 2);
        LC.leastIntervalOptimize(new char[]{'A', 'B', 'C', 'D', 'E', 'A', 'B', 'C', 'D', 'E'}, 4);
    }

    public int leastInterval(char[] tasks, int n) {
        if (tasks == null || tasks.length == 0) return 0;
        if (tasks.length == 1) return 1;
        if (n == 0) return tasks.length;
        int interval = 0;
        TreeMap<Character, Integer> tm = new TreeMap<>();
        for (int i = 0; i < tasks.length; i++) {
            tm.compute(tasks[i], (k, v) -> (v == null) ? 1 : v + 1);
        }
        PriorityQueue<Integer> q = new PriorityQueue<>();
        tm.forEach((k, v) -> q.offer(-v));
        int max = -q.poll();
        int totalInterval = (max - 1) * n;
        interval = totalInterval + max;
        while (!q.isEmpty()) {
            var i = -q.poll();
            if (i == max) {
                interval += 1;
                totalInterval -= (i - 1);
            } else {
                totalInterval -= i;
            }
        }
        if (totalInterval < 0) interval -= totalInterval;
        log.debug("tasks: {}, n: {}, r: {}", tasks, n, interval);
        return interval;
    }

    public int leastIntervalOptimize(char[] tasks, int n) {
        int[] c = new int[26];
        for (char ch : tasks) c[ch - 'A']++;
        int max = 0;
        int maxC = 0;
        for (int i : c) {
            if (i > max) {
                max = i;
                maxC = 1;
            } else if (i == max) {
                maxC++;
            }
        }
        var interval = Math.max(tasks.length, max + n * (max - 1) + maxC - 1);
        log.debug("tasks: {}, n: {}, r: {}", tasks, n, interval);
        return interval;
    }

}
