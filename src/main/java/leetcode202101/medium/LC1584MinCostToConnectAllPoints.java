package leetcode202101.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

public class LC1584MinCostToConnectAllPoints extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1584MinCostToConnectAllPoints();
        var s = LC.minCostConnectPoints(new int[][]{{0, 0}, {2, 2}, {3, 10}, {5, 2}, {7, 0}});
    }

    /**
     * 最小生成樹的經典題 -> MST -> Minimum spanning tree -> greedy algorithm的概念, 由一個點出發, 找min edge一直延伸出去
     * https://leetcode.com/problems/min-cost-to-connect-all-points/discuss/843972/Java-Minimum-Spanning-Tree%3A-Prim-%2B-Kruskal
     * Prim's algorithm - https://en.wikipedia.org/wiki/Prim%27s_algorithm
     * 1. 從一個點出發, 成為tree的第一個點
     * 2. 從該MST開始檢查可以往外延伸的node中的min edge -> 可以連到其他尚未在tree中的點 -> 加入MST
     * 3. 重複以上步驟
     * 真正實作是
     * 1 準備一個Set 紀錄有在MST中的Vertex
     * 2 一個走到該node的int[] dist(這邊是紀錄目前走到的node的dist,
     * -   ex: dist[2] -> 就是MST計算過程中以記錄到走到node 2的dist, 但是起點可是MST其中某一點, 這邊沒有紀錄, 因為也用不到)
     * -   所以意義上是用來記錄MST到各點的距離(只知道到該點距離, 但不知到是MST哪個點推算出來的, 該意義沒保存下來)
     * 1. 從node 0 開始 -> 加入MST, dist[i] 都是由0走到其他node的dist(因為此時MST只有node)
     * 2. 從dist中找到min值 -> 就是MST要繼續延伸到尚未觸及的node,且花最小cost -> 這樣最後才會形成MST
     * 3. 找到後更新dist(因為MST擴張了, 所以dist可以再更新了)
     * 4. 持續2 -> 3直到 MST size == n
     */
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        int ans = 0;
        HashSet<Integer> mstNodes = new HashSet<>();
        mstNodes.add(0);
        int[] dist = new int[n];
        for (int i = 0; i < n; i++) dist[i] = getDist(points, 0, i);
        while (mstNodes.size() != n) {
            int next = -1;
            for (int i = 0; i < n; i++) {
                if (mstNodes.contains(i)) continue;
                if (next == -1 || dist[next] > dist[i]) next = i;
            }
            mstNodes.add(next);
            ans += dist[next];
            for (int i = 0; i < n; i++) {
                if (!mstNodes.contains(i)) dist[i] = Math.min(dist[i], getDist(points, i, next));
            }
        }
        return ans;
    }

    private int getDist(int[][] points, int a, int b) {
        return Math.abs(points[a][0] - points[b][0]) + Math.abs(points[a][1] - points[b][1]);
    }

    /**
     * Kruskal's algorithm - https://en.wikipedia.org/wiki/Kruskal%27s_algorithm
     * 基本概念就是 由邊出發, greedy找出最小edge組出MST
     * 取出 min edge -> add to MST(if no cycle)
     *
     * 所以要
     * 1. PriorityQueue<int[]> pq 去記錄所有edge, 並由小的開始取出來
     * 2. 創立union find -> 找到的min邊加進來, 且也不會產生cycle(用UnionFind class 來紀錄MST)
     * -- 這邊的概念是, 一開始所有edge都是獨立的, 所以find parent都是自己, 然後互相union(意義上就是加入MST), parent就會一樣
     * 2. poll pq -> 看看該邊是否同parent(有無cycle) -> 若無就加入MST(union 起來)
     * 3. 直到count == n-1 or pq為空
     * 其實Kruskal概念在過程中都會建立起很多分散的小樹, 所以他們個別的union find parent都不一樣, 可以知道是否有cycle
     */
    public int minCostConnectPoints2(int[][] points) {
        int n = points.length;
        int ans = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                pq.add(new int[]{getDist(points, i, j), i, j});
            }
        }
        int count = 0;
        UnionFind uf = new UnionFind(n);
        while (count < n - 1) {
            int[] edge = pq.poll();
            if (uf.find(edge[1]) != uf.find(edge[2])) {
                ans += edge[0];
                count++;
                uf.union(edge[1], edge[2]);
            }
        }
        return ans;
    }

    class UnionFind {
        int[] parent;

        UnionFind(int n) {
            this.parent = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }

        public void union(int a, int b) {
            parent[find(a)] = parent[find(b)];
        }

        public int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }
    }
}
