package leetcode202103.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC1192CriticalConnectionsInANetwork extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1192CriticalConnectionsInANetwork();
    }

    /**
     * Tarjan演算法, 完全沒想到強聯通圖可以這樣解
     * 這邊有影片解釋 Tarjean https://www.youtube.com/watch?v=TyWtx7q2D7Y&ab_channel=WilliamFiset
     * critical connections 就是 strong connected graph 之間的 edge
     * 所以找出 strong connected graph 的tarjan可以拿來用
     * Tarjan是用來
     * 1. 從任一 unvisited 點開始拜訪 -> 給一個 global incremented id
     * 2. dfs下去, visited 下去的點都會記載到 同一批最小的 increment id, 就是說同一批 strong connected graph 都是用同一個 low id
     * 不過Tarjan 通常是用來講 directed graph, 因此這題是 undirected graph 得做一些變化
     * 1. int[] low 用來記載同一批走到的 strong connected graph, 如果是strong connected graph, 他們的 low 都會是同一個最小
     * 2. int[] timestamp -> 用來記載走到當前點的 timestamp
     * 這邊我們用一個 global seq id 來記載 timestamp,
     * 所以當dfs u vertex, timestamp[u] = ++seqID; , low[u] 也先給當前 seqID
     * 只是當 u 下去試試u的所有 adj vertex v
     * 1. v is not u parent(避免往回走, 因為是 undirected graph)
     * 2. 如果v 未拜訪, unvisited -> dfs v
     * 3. 確認v拜訪之後, update low[u] = Math.min(low[v], low[u]);
     * -> 因為low[u] 是當前 seqID, 但可能 low[v]更小(之前有走過, 代表有聯通點, 聯通點之間取最小low為共識), 更新完確保strong connected graph, 他們的 low 都會是同一個最小
     * 4. 檢查if (low[v] > timestamp[u]) -> 確認是否是bridge
     * -> 因為 u 有押一個 timestamp[u] 拜訪時機點, 如果low[v]的strong connected graph的low大於 timestamp[u] 拜訪時機點
     * -> 代表是分開的強連同通圖, 剛好走在 bridge 上面 -> 所以就是要找的 articulation point
     * -> 同一批strong connected graph, low會一致, 但timestamp還是遞增, 因此當前 strong connected graph走完, low都會是最低值
     * -> 因此遇到 low[v] > timestamp[u], 就是說當前在檢查的 adj v, 是在另一批strong connected graph
     * */
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        int[] timestamp = new int[n];
        int[] low = new int[n];
        List<Integer>[] adj = new ArrayList[n];
        List<List<Integer>> res = new ArrayList<>();
        Arrays.fill(timestamp, -1);
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (List<Integer> conn : connections) {
            adj[conn.get(0)].add(conn.get(1));
            adj[conn.get(1)].add(conn.get(0));
        }

        for (int i = 0; i < n; i++) {
            if (timestamp[i] == -1) dfs(i, low, timestamp, adj, res, i);
        }

        return res;
    }

    int seqID = 0;

    private void dfs(int u, int[] low, int[] timestamp, List<Integer>[] adj, List<List<Integer>> res, int parent) {
        timestamp[u] = low[u] = ++seqID;
        for (int v : adj[u]) {
            if (v == parent) continue;
            if (timestamp[v] == -1) {
                dfs(v, low, timestamp, adj, res, u);
            }
            low[u] = Math.min(low[u], low[v]);

            if (low[v] > timestamp[u]) {
                res.add(Arrays.asList(u, v));
            }
        }
    }
}
