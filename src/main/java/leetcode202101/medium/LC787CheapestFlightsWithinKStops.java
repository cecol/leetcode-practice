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
  public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
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
