package leetcode202212.contest;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class BiWeeklyContest93 extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new BiWeeklyContest93();
//        LC.maxJump(new int[]{0, 2, 5, 6, 7});
//        LC.maxJump(new int[]{0, 2, 11, 13, 17, 18, 21}); // expect 11
        LC.maxJump(new int[]{0, 2, 16, 26, 36, 40, 58, 59, 71}); // expect 24
    }

    public int maximumValue(String[] strs) {
        int res = 0;
        for (String s : strs) {
            boolean isNum = true;
            for (char c : s.toCharArray()) {
                if (!Character.isDigit(c)) {
                    isNum = false;
                    break;
                }
            }
            if (isNum) {
                while (s.length() > 1 && s.charAt(0) == '0') s = s.substring(1);
                res = Math.max(res, Integer.parseInt(s));
            } else {
                res = Math.max(res, s.length());
            }
        }
        return res;
    }

    public int maxStarSum(int[] vals, int[][] edges, int k) {
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        for (int[] e : edges) {
            if (!map.containsKey(e[0])) map.put(e[0], new PriorityQueue<>((x, y) -> y - x));
            if (!map.containsKey(e[1])) map.put(e[1], new PriorityQueue<>((x, y) -> y - x));

            map.get(e[0]).offer(vals[e[1]]);
            map.get(e[1]).offer(vals[e[0]]);
        }
        int res = Integer.MIN_VALUE;
        int n = vals.length;
        for (int i = 0; i < n; i++) {
            int sum = vals[i];
            PriorityQueue<Integer> e = map.getOrDefault(i, new PriorityQueue<>((x, y) -> y - x));
            for (int j = 0; j < k && e.size() > 0 && e.peek() > 0; j++) {
                sum += e.poll();
            }
            res = Math.max(res, sum);
        }

        return res;
    }

    public int maxJump(int[] stones) {
        int n = stones.length;
        if (n <= 3) return stones[n - 1] - stones[0];
        TreeSet<Integer> forward = new TreeSet<>();
        TreeSet<Integer> backward = new TreeSet<>();
        forward.add(stones[0]);
        forward.add(stones[n - 1]);
        backward.add(stones[0]);
        backward.add(stones[n - 1]);
        int res = 0;
        boolean fc = false, bc = false;
        PriorityQueue<int[]> fq = new PriorityQueue<>((x, y) -> y[2] - x[2]);
        PriorityQueue<int[]> bq = new PriorityQueue<>((x, y) -> y[2] - x[2]);
        fq.offer(new int[]{stones[0], stones[n - 1], stones[n - 1] - stones[0]});
        bq.offer(new int[]{stones[0], stones[n - 1], stones[n - 1] - stones[0]});
        for (int i = 1; i < n - 1; i++) {
            int s = stones[i];
            if (fq.peek()[2] > bq.peek()[2]) {
                int pre = forward.floor(s);
                int next = forward.ceiling(s);
                if (fq.peek()[0] == pre && fq.peek()[1] == next) {
                    fq.poll();
                    fq.offer(new int[]{pre, s, s - pre});
                    fq.offer(new int[]{s, next, next - s});
                }
                forward.add(s);
            } else {
                int pre = backward.floor(s);
                int next = backward.ceiling(s);
                if (bq.peek()[0] == pre && bq.peek()[1] == next) {
                    bq.poll();
                    bq.offer(new int[]{pre, s, s - pre});
                    bq.offer(new int[]{s, next, next - s});
                }
                backward.add(s);
            }
            res = Math.max(fq.peek()[2], bq.peek()[2]);
        }
        return res;
    }

    public long minimumTotalCost(int[] nums1, int[] nums2) {
        return 0l;
    }
}
