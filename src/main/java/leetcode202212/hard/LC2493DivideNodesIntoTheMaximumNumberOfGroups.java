package leetcode202212.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC2493DivideNodesIntoTheMaximumNumberOfGroups extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC2493DivideNodesIntoTheMaximumNumberOfGroups();
    }


    /**
     * 這題再考分 group 其實在考 圖論中的 Bipartite
     * https://web.ntnu.edu.tw/~algo/BipartiteGraph.html
     * Bipartite 就是說可以分成兩群點，兩群點之間有邊，兩群點內部無邊。
     * 一個圖能不能形成 Bipartite 關鍵在於有沒有 奇數邊環
     * 檢查一張圖是不是二分圖，方法很簡單：確認圖上是否有奇環，沒有奇環就是二分圖
     * https://leetcode.com/problems/divide-nodes-into-the-maximum-number-of-groups/discuss/2874985/2-Observations-oror-Bipartite-check-oror-C%2B%2B
     * 這說明是 C++, 但提出很明確觀點
     * 1. 先看是不是 Bipartite
     * 2. maximum group 數目 就是 任兩點之間最短距離的最長距離(每個點距離 1)
     * - 如果圖無環, 那就是 (u, v) 兩點間距離 x, (u, v) 每個距離之間的點 1, 2, .. , x 都可以成一個 group
     * - 如果圖有還, (u, v) 兩點間最長距離 x , (u, v) 每個距離之間的點 1, 2, .. , x 都可以成一個 group
     * 所以演算法是
     * 1. 檢查 Bipartite
     * 2. 用 BFS 看每個點之間最短距離, 取出其中最長距離 就是 group 數目
     * <p>
     * https://leetcode.com/problems/divide-nodes-into-the-maximum-number-of-groups/discuss/2875487/Java-simple-and-self-explanatory
     * JAVA 解法 很複雜  但還是根據上面的兩個原則來建構
     * 1. 先建立 int[][] adjacent map, 得到每點跟誰 adjacent
     * 2. 每個點下去 bfs, 根據他的 adjacent, 開始一直走, 紀錄該點 i 走到其他點的 distance
     * - 該點 i BFS 走完後記載 i 點到其他點的 max dist in int[] maxDistance
     * 3. 再每一點下去 bfs 第二次, 看看一個聯通圖裡面的所有點對點之間最大的 maxDist, 就是該 聯通圖的 max groups
     * - 如果在過程中發現有個點已經被走過了, 代表有 Cycle -> 看看 cycle 邊長是不是 odd, 是的話根本無法 Bipartite return -1
     *
     * 我自己整理的 Leetcode 解釋
     * https://leetcode.com/problems/divide-nodes-into-the-maximum-number-of-groups/discuss/2876131/JAVA-or-Bipartite-or-BFS-or-Straighforward-logic-explanation
     */
    public int magnificentSets(int n, int[][] edges) {
        int[] degree = new int[n + 1];
        for (int[] e : edges) {
            degree[e[0]]++;
            degree[e[1]]++;
        }
        int[][] adjacent = new int[n + 1][];
        for (int i = 1; i <= n; i++) adjacent[i] = new int[degree[i]];
        for (int[] e : edges) {
            int a = e[0];
            int b = e[1];
            adjacent[a][--degree[a]] = b;
            adjacent[b][--degree[b]] = a;
        }

        Queue<Integer> bfs = new LinkedList<>();
        int[] distance = new int[n + 1];
        int[] maxDistance = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(distance, -1);
            bfs.offer(i);
            distance[i] = 0;
            int maxDist = 0;
            while (bfs.size() > 0) {
                int node = bfs.poll();
                int nextDist = distance[node] + 1;
                for (int nextNode : adjacent[node])
                    if (distance[nextNode] < 0) {
                        distance[nextNode] = nextDist;
                        maxDist = nextDist;
                        bfs.offer(nextNode);
                    }
            }
            maxDistance[i] = maxDist;
        }

        Arrays.fill(distance, -1);
        int res = 0;
        for (int i = 1; i <= n; i++) {
            if (distance[i] >= 0) continue;
            bfs.offer(i);
            distance[i] = 0;
            int diameter = 0;
            while (bfs.size() > 0) {
                int node = bfs.poll();
                diameter = Math.max(diameter, maxDistance[node]);
                int nextDist = distance[node] + 1;
                for (int nextNode : adjacent[node]) {
                    if (distance[nextNode] < 0) {
                        distance[nextNode] = nextDist;
                        bfs.offer(nextNode);
                    } else if ((nextDist + distance[nextNode]) % 2 != 0) return -1;
                }
            }
            res += diameter + 1;
        }
        return res;
    }
}
