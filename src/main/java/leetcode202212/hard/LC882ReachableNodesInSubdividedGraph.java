package leetcode202212.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

public class LC882ReachableNodesInSubdividedGraph extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC882ReachableNodesInSubdividedGraph();
    }

    /**
     * https://leetcode.com/problems/reachable-nodes-in-subdivided-graph/solutions/156739/c-java-python-dijkstra-priority-queue/
     * 這題是用 dijkstra algorithm
     * 但關鍵是放進去的是  還剩下多少步數 可以往之後走
     * 所以 PriorityQueue<int[]> int[]{ left maxMove, node i}
     * 所以每往下一步走, 都要扣掉 中間增加的距離, 如果 扣掉後 < 0, 代表根本走不到該 node
     * -  int move2 = moves - e.get(i).get(j) - 1;
     * - if (!seen.containsKey(j) && move2 >= 0) {
     * <p>
     * 所以 bfs 的 PriorityQueue<int[]> 是由還剩下最多步數的點, 優先繼續走, 看能走到哪些 nodes
     * 能走到的 node 會放到 HashMap<Integer, Integer> seen = new HashMap<>();
     * key 是能走到的, value 是走到時候剩下幾步
     * <p>
     * 最後結算時候
     * int res = seen.size(); 這是能走到的低消,
     * - for (int[] v : edges) {
     * -            int a = seen.getOrDefault(v[0], 0);
     * -            int b = seen.getOrDefault(v[1], 0);
     * -            res += Math.min(a + b, v[2]);
     * -        }
     * 針對每一邊下去看 兩邊的點 是不是在 seen 裏面, 拿出在 seen 裏面 剩下的步數
     * 如果剩下步數比較多 那最多能多加的 就是兩間中間增加的 v[2] (代表 至多也只能拿到兩間中間增加的 v[2])
     * 如果剩下步數比較少 那最多能多加的 就是剩下步數 (代表 兩間中間增加的 v[2] 太多 根本走不完)
     *
     */
    public int reachableNodes(int[][] edges, int maxMoves, int n) {
        HashMap<Integer, HashMap<Integer, Integer>> e = new HashMap<>();
        for (int i = 0; i < n; i++) e.put(i, new HashMap<>());
        for (int[] v : edges) {
            e.get(v[0]).put(v[1], v[2]);
            e.get(v[1]).put(v[0], v[2]);
        }
        PriorityQueue<int[]> bfs = new PriorityQueue<>((x, y) -> (y[0] - x[0]));
        bfs.offer(new int[]{maxMoves, 0});
        HashMap<Integer, Integer> seen = new HashMap<>();
        while (bfs.size() > 0) {
            int leftMoves = bfs.peek()[0], i = bfs.peek()[1];
            bfs.poll();
            if (!seen.containsKey(i)) {
                seen.put(i, leftMoves);
                for (int j : e.get(i).keySet()) {
                    int leftMoves2 = leftMoves - e.get(i).get(j) - 1;
                    if (!seen.containsKey(j) && leftMoves2 >= 0) {
                        bfs.offer(new int[]{leftMoves2, j});
                    }
                }
            }
        }
        int res = seen.size();
        for (int[] v : edges) {
            int a = seen.getOrDefault(v[0], 0);
            int b = seen.getOrDefault(v[1], 0);
            res += Math.min(a + b, v[2]);
        }
        return res;
    }
}
