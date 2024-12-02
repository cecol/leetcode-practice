package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class LC787CheapestFlightsWithinKStops extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 忘了
    // Bellman-Ford 解
    // 1. 準備 int[] cost, 表示 0 - n-1 每一站的成本
    // 2. set cost to Integer MAX
    // 3. cost[start] = 0;
    // 4. for i = k to 0, 經過 k round
    // 5. 每一 round loop flights, 看
    // 6. cost[f-start] != MAX, cur[f-end] = min(cur[f-end], cost[f-start]+cost)
    // 7. cost = cur, 下一輪, 並看一下 cost[n] 是否有出現 min
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[] cost = new int[n];
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[src] = 0;
        int res = cost[dst];
        for (int i = k; i >= 0; i--) {
            int[] cur = new int[n];
            Arrays.fill(cur, Integer.MAX_VALUE);
            for (int[] f : flights) {
                if (cost[f[0]] != Integer.MAX_VALUE)
                    cur[f[1]] = Math.min(cur[f[1]], cost[f[0]] + f[2]);
            }
            cost = cur;
            res = Math.min(res, cost[dst]);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    // Dikastra 算法
    // 1. 準備每一站到其他站的 Map & cost
    // 2. pq 存 int[]{當前花費, 當前這一站, 還剩幾次機會} -> 花費小的優先
    // 3. pq.offer(new int[]{0, src, k+1})
    // 4. loop pq,
    // 5. if 當前這一站 == dst 回傳
    // 6. if 還剩幾次機會 > 0 -> 看看 當前這一站 有多下一站 都加入 pq
    public int findCheapestPriceDijk(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, Map<Integer, Integer>> prices = new HashMap<>();
        for (int[] f : flights) {
            if (!prices.containsKey(f[0])) prices.put(f[0], new HashMap<>());
            prices.get(f[0]).put(f[1], f[2]);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[0] - b[0];
        });
        pq.offer(new int[]{0, src, k + 1});
        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int price = top[0];
            int city = top[1];
            int stops = top[2];
            if (city == dst) return price;
            if (stops > 0) {
                Map<Integer, Integer> adj = prices.getOrDefault(city, new HashMap<>());
                for (int a : adj.keySet())
                    pq.offer(new int[]{adj.get(a) + price, a, stops - 1});
            }
        }
        return -1;
    }
}
