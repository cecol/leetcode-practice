package leetcode202101.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.IntStream;

public class LC743NetworkDelayTime extends BasicTemplate {
  public static void main(String[] args) {
    Logger log = LoggerFactory.getLogger("Main");
    var LC = new LC743NetworkDelayTime();
    var s = LC.networkDelayTime(new int[][]{{2, 1, 1}, {2, 3, 1}, {3, 4, 1}}, 4, 2);
  }

  /**
   * 這題Dijkstra minimum-BFS走完, 最後一個走到的點的shortest path cost(Max value)就是總共要花的時間
   * Shortest path經典題, 題目核心: 從一個點K開始, 找到到其他點最短路徑
   * Dijkstra's algorithm 基本概念:從起始點下去, 找最小路徑擴散(minimum-BFS)到其他點, 再由其他已知shortest path的點擴散下去
   *       create vertex set Q                     -> 記錄尚未確定為shortest path的點
   *
   *       for each vertex v in Graph:             -> 設定每個點距離的初始值: MAX_VALUE
   *           dist[v] ← INFINITY                  -> 起始點到每個點已計算的距離
   *           prev[v] ← UNDEFINED
   *           add v to Q                          -> 先加入到還沒走過的點
   *       dist[source] ← 0                        -> 起始點到自己的距離 = 0
   *
   *       while Q is not empty:                   -> 還有點還沒計算到
   *           u ← vertex in Q with min dist[u]    -> 拿出尚在計算shortest pat的點中已知最小值
   *
   *           remove u from Q                     -> 把他移出(因為他是最小值, 他已經是shortest path,
   *                                                   而是要看能否從他在走道其他點造成下一次的shortest pat)
   *
   *           for each neighbor v of u:           -> 就是上面說的, 已經是確認shortest pat的u, 找出他能走到的點,
   *                                                  看能否再次更新shortest path的紀錄
   *               alt ← dist[u] + length(u, v)
   *               if alt < dist[v]:               -> 確實透過這個點u, 可以更新shortest path到其他點
   *                   dist[v] ← alt
   *                   prev[v] ← u
   *
   * 所以這題的K 就是起始點, int[][] times裡面的(u, v, w)中的w是weight, 就是到那個u -> v的path cost
   * 所以先建立 Map<Integer, Map<Integer, Integer>> path, 每個點到其他點的path costs
   * - key 是 u, value是 u到其他點v的 costs, 一個u到v可能有多條path, 只記錄下minimum path
   * Map<Integer, Integer> kToOthersDistance = new HashMap<>(); 就是紀錄起點K到其他點的distance
   * 最後跑完就會是 shortest path
   *
   * PriorityQueue<int[]> shortestPathMinHeapBfs = new PriorityQueue<>(Comparator.comparingInt(i -> i[1]));
   * 就是用來放起點K到其他點的所有距離, PriorityQueue是讓你直接拿到最短那個繼續下去算
   * 所以裡面先放起始點 [K,0], 代表從K出發, 開始找其他shortest path, 而且是由最小的來往後找, 所以是由最小點開始BFS -> minimum BFS
   *
   * 這題Dijkstra minimum-BFS走完, 最後一個走到的點的shortest path(Max value)就是總共要花的時間
   * 如果最後走完的點數量跟實際不樣, 代表有點走不到 -> -1
   *
   * 時間複雜度 Time complexity: O(Nlog(N) + E), Space complexity: O(N + E)
   * 其中Nlog(N)是因為用PriorityQueue來找minimum cost, 用heap tree有加速
   * 其實還可以用以下解, 但時間複雜度沒有 Dijkstra + BFS Heap來的快
   * Floyd-Warshall - Time complexity: O(N^3), Space complexity: O(N^2)
   * Bellman-Ford解 - Time complexity: O(N*E), Space complexity: O(N)
   * 所以沒有細讀
   * 可參考
   * https://leetcode.com/problems/network-delay-time/discuss/183873/Java-solutions-using-Dijkstra-FloydWarshall-and-Bellman-Ford-algorithm
   *
   * 原始解法參考
   * https://leetcode.com/problems/network-delay-time/discuss/109968/Simple-JAVA-Djikstra's-(PriorityQueue-optimized)-Solution-with-explanation
   * */
  public int networkDelayTime(int[][] times, int N, int K) {
    if (times == null || times.length == 0) return -1;
    Map<Integer, Map<Integer, Integer>> path = new HashMap<>();
    for (int[] time : times) {
      Map<Integer, Integer> destM = path.get(time[0]);
      if (destM == null) {
        destM = new HashMap<>();
        path.put(time[0], destM);
      }
      Integer distance = destM.get(time[1]);
      if (distance == null || distance > time[2]) {
        destM.put(time[1], time[2]);
      }
    }

    Map<Integer, Integer> kToOthersDistance = new HashMap<>();
    kToOthersDistance.put(K, 0);
    PriorityQueue<int[]> shortestPathMinHeapBfs = new PriorityQueue<>(Comparator.comparingInt(i -> i[1]));
    shortestPathMinHeapBfs.offer(new int[]{K, 0});
    int max = -1;
    while (!shortestPathMinHeapBfs.isEmpty()) {
      int[] nextSPBfs = shortestPathMinHeapBfs.poll();
      int SPNode = nextSPBfs[0];
      int SPDist = nextSPBfs[1];

      if (kToOthersDistance.containsKey(SPNode) && kToOthersDistance.get(SPNode) < SPDist) continue;
      Map<Integer, Integer> SPNodeToOther = path.get(SPNode);
      if (SPNodeToOther == null) continue;
      for (Map.Entry<Integer, Integer> nextNode : SPNodeToOther.entrySet()) {
        int nextDist = SPDist + nextNode.getValue();
        int nextNodeV = nextNode.getKey();
        if (kToOthersDistance.containsKey(nextNodeV) && kToOthersDistance.get(nextNodeV) <= nextDist) continue;
        kToOthersDistance.put(nextNodeV, nextDist);
        shortestPathMinHeapBfs.offer(new int[]{nextNodeV, nextDist});
      }
    }
    for (int val : kToOthersDistance.values()) if (val > max) max = val;
    return kToOthersDistance.size() == N ? max : -1;
  }
}
