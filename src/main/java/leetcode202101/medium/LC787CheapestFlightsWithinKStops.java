package leetcode202101.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC787CheapestFlightsWithinKStops extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC787CheapestFlightsWithinKStops();
//    var s = LC.findCheapestPrice(3, new int[][]{{0, 1, 100}, {1, 2, 100}, {0, 2, 500}}, 0, 2, 1);
        var s2 = LC.findCheapestPrice(4, new int[][]{{0, 1, 1}, {0, 2, 5}, {1, 2, 1}, {2, 3, 1}}, 0, 3, 1);
    }

    /**
     * 2021/5/20 才發現原本的 Dijkstra 演算法無法跑過, 會TLE, 應該是有走回頭的案例發生 可參考
     * ->            0
     * ->         /   /\
     * ->     10 /     \
     * ->       /       \  10
     * ->      \/.       \
     * ->      1 -------> 2----------->3
     * ->          10.         1000
     * -> we tend to repeat the cycle 0->1->2->0 until the total path sum becomes > the path
     * -> sum 0->1->2->3 or we exhaust all k moves
     * 看來應該是 Bellman–Ford algorithm 才可以解 而且邏輯比較簡單
     * 雖然速度比較慢O(V * E), 但遇到上述例子 3 會先被走到, 不會卡在  0->1->2->0
     * 是比起 Dijkstra 更加 general solution, 且可以檢測 negative cycle
     * https://en.wikipedia.org/wiki/Bellman%E2%80%93Ford_algorithm
     * This method allows the Bellman–Ford algorithm to be applied to a wider class of inputs than Dijkstra.
     * A final scan of all the edges is performed and if any distance is updated, then a path of length
     * |V| edges has been found which can only occur if at least one negative cycle exists in the graph.
     *
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[] cost = new int[n];
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[src] = 0;
        int res = cost[dst];
        for (int i = k; i >= 0; i--) {
            int[] cur = new int[n];
            Arrays.fill(cur, Integer.MAX_VALUE);
            for (int[] flight : flights) {
                if(cost[flight[0]] != Integer.MAX_VALUE) cur[flight[1]] = Math.min(cur[flight[1]], cost[flight[0]] + flight[2]);
            }
            cost = cur;
            res = Math.min(res, cost[dst]);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    /**
     * https://leetcode.com/problems/cheapest-flights-within-k-stops/discuss/115541/JavaPython-Priority-Queue-Solution
     * 這題基本概念是shortest path: from src -> dst
     * 但因為只有飛K站, 且超過就不用算了
     * 所以也不用特別紀錄飛到其他站的shortest path
     * 而是用來做 minimum first 的BFS heap拿來輪詢, 只要有輪到dst進入BFS heap(有進入BFS heap, 代表該值應該是shortest path)
     * 就可以回傳
     * 否則回-1
     * 比較特別的是也把第幾站的資訊一併納入BFS heap結構內, 用來紀錄飛到該點時剩下幾站
     * 1. 如果還有quota -> 後續點繼續加入BFS進入下次回圈繼續飛
     * 2. 沒有quota且尚未到dst -> pq.isEmpty()就出loop -> return -1
     */
    public int findCheapestPriceTLE(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, Map<Integer, Integer>> prices = new HashMap<>();
        for (int[] f : flights) {
            if (!prices.containsKey(f[0])) prices.put(f[0], new HashMap<>());
            prices.get(f[0]).put(f[1], f[2]);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (Integer.compare(a[0], b[0])));
        pq.offer(new int[]{0, src, K + 1});
        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int price = top[0];
            int city = top[1];
            int stops = top[2];
            if (city == dst) return price;
            if (stops > 0) {
                Map<Integer, Integer> adj = prices.getOrDefault(city, new HashMap<>());
                for (int a : adj.keySet()) {
                    pq.offer(new int[]{adj.get(a) + price, a, stops - 1});
                }
            }
        }
        return -1;
    }
}
