package leetcode202211.contest;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class BiWeeklyContest91 extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new BiWeeklyContest91();

//        log.debug("{}", LC.countGoodStrings(200, 200, 10, 1)); // 764262396
//        log.debug("{}", LC.countGoodStrings(500, 500, 5, 2)); // 873327137

        log.debug("{}", LC.mostProfitablePath(new int[][]{{0, 1}, {1, 2}, {1, 3}, {3, 4}}, 3, new int[]{-2, 4, 2, -4, 6}));
        log.debug("{}", LC.mostProfitablePath(new int[][]{{0, 1}}, 1, new int[]{-7280,2350}));
    }

    public int distinctAverages(int[] nums) {
        Arrays.sort(nums);
        int i = 0, j = nums.length - 1;
        HashSet<Double> s = new HashSet<>();
        while (i < j) {
            s.add(((double) (nums[i++] + nums[j--]) / 2D));
        }
        return s.size();
    }

    public int countGoodStrings(int low, int high, int zero, int one) {
        long[] dp = new long[high + 1];
        dp[0] = 1;
        int mod = (int) 1e9 + 7;
        long res = 0;

        for (int i = 1; i <= high; i++) {
            if (i >= zero) dp[i] += dp[i - zero] % mod;
            if (i >= one) dp[i] += dp[i - one] % mod;
            if (i >= low) res = (res + dp[i]) % mod;
        }
        return (int) res % mod;
    }

    Map<Integer, List<Integer>> edgeMap;

    public int mostProfitablePath(int[][] edges, int bob, int[] amount) {
        int n = amount.length;
        edgeMap = new HashMap<>();
        for (int[] e : edges) {
            if (!edgeMap.containsKey(e[0])) edgeMap.put(e[0], new ArrayList<>());
            if (!edgeMap.containsKey(e[1])) edgeMap.put(e[1], new ArrayList<>());
            edgeMap.get(e[0]).add(e[1]);
            edgeMap.get(e[1]).add(e[0]);
        }

        boolean a = bobR(bob, new HashSet<>());
        Collections.reverse(bobR);
        Map<Integer, Integer> bobM = new HashMap<>();
        for (int i = 0; i < bobR.size(); i++)
            bobM.put(i, bobR.get(i));

        log.debug("{}", bobM);

        Queue<int[]> alice = new LinkedList<>();
        alice.offer(new int[]{0, amount[0]});
        Set<Integer> seen = new HashSet<>();
        int res = Integer.MIN_VALUE;
        int step = 0;
        while (alice.size() > 0) {
            int s = alice.size();
            if (bobM.containsKey(step)) amount[bobM.get(step)] = 0;
            for (int i = 0; i < s; i++) {
                int[] cur = alice.poll();

                seen.add(cur[0]);
                boolean isL = true;
                for (Integer v : edgeMap.get(cur[0])) {
                    if (seen.add(v)) {
                        isL = false;
                        int cost = amount[v];
                        if (bobM.containsKey(step+1) && bobM.get(step+1) == v) cost /= 2;
                        alice.offer(new int[]{v, cur[1] + cost});
                    }
                }
                if (isL) res = Math.max(res, cur[1]);
            }
            step++;
        }

        return res;
    }

    List<Integer> bobR = new ArrayList<>();

    boolean bobR(int n, Set<Integer> seen) {
        if (n == 0) {
            bobR.add(0);
            return true;
        }
        seen.add(n);
        for (Integer next : edgeMap.get(n)) {
            boolean can = false;
            if (!seen.contains(next)) can = bobR(next, seen);
            if (can) {
                bobR.add(n);
                return true;
            }
        }
        seen.remove(n);
        return false;
    }
}
