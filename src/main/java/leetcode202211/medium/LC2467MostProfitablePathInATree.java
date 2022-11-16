package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC2467MostProfitablePathInATree extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");

    }

    /**
     * https://leetcode.com/problems/most-profitable-path-in-a-tree/discuss/2807832/Java-or-2-solutions-or-DFS-or-BFS
     * 這題題目就需要一些時間理解, 基本思路我有想出來, 但卡在以為DFS 行不通因為 node 數 10^5
     * 但我沒有掌握到 tree 的精髓
     *
     * tree 不像 graph, 2 點之間只有 一條 path, 每個node 只能往下走
     * 所以 DFS 只要記載不要往 parent 走就好, 就是說在 DFS 裏面, 我們把 visit[v] set 成 TRUE
     * 在 DFS 遞迴離開前 不必 把 visit[v] 改回成 FALSE
     * 所以 Tree DFS 一定 O(N),
     * Ex: tree node path A -> C -> B, 不可能會有 A -> D -> B
     * 這是很關鍵的要素, 所以 Tree DFS 比起 graph, 快多了!!
     *
     * 2. 基本思路就是 Alice 走到每個 leaves 過程中 累積多少錢,
     * 然後要扣除如果路徑上是 bob 走過, 拿到的錢會受影響
     *
     * 可以先 DFS bob, 找出 bob 路徑, 然後走 alice 時候 檢查是否 bob 走過就好
     * 最精簡就是一次 DFS 就做完, DFS 要帶入
     * 1. 當前 node, bob 起始 node, 已走過 parents, 當前走的長度
     * 2. 回傳 int[2]{目前看過最大, 遇到 bob path 長度}
     *
     * 所以當前 node 下去 dfs 遞迴走看看 回傳的結果
     * 1. 如果回傳的 int[1] bob 長度 > 0, 代表當前 node 是在 bob 路上, 先把 bobLen 記下來
     * 2. 比較 res = Math.max(res, dfsNext[0]);
     *
     * 遞迴完之後
     * 檢查是否 bobPathLen > 0, 且 bobPathLen <= aliceDist
     * https://leetcode.com/problems/most-profitable-path-in-a-tree/discuss/2807203/Java-oror-DFS-(-Find-Path-)-%2B-DFS-(-get-maxSum-)-oror-Explained
     * 這邊有圖解
     * bobPathLen > 0 代表是在 bob path, 很直觀, 因為 dfs 遞迴下去, 子孫會傳遞這個訊息回來
     * bobPathLen <= aliceDist => 代表 bob走到的時候, alice 還沒走到或者正好遇到,
     * 反過來說 bobPathLen > aliceDist => 代表 bob走到的時候, alice 早走過, 代表 bob 跟本沒影響 alice 的錢
     * Ex: Alice -> A -> B -> C -> bob, Alice 走到 Ａ 時候, A -> B 遞迴回來
     * bobPathLen = 3, dist = 1, 代表 Alice 早走過
     * - if (bobPathLen == aliceDist) amount[node] = amount[node] / 2; => 兩人對分
     * - else amount[node] = 0; => Bob 先消化了
     *
     * 最後回傳
     * new int[]{res == Integer.MIN_VALUE ? amount[node] : amount[node] + res, bobPathLen};
     * res == Integer.MIN_VALUE 代表 leave
     * res != Integer.MIN_VALUE 代表是 parent 收到 子孫 dfs 結果 再加上自己的 amount 再回傳上去
     *
     */
    public int mostProfitablePath(int[][] edges, int bob, int[] amount) {
        int n = amount.length;
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }
        return helper_dfs(graph, 0, bob, amount, new boolean[n], 1)[0];
    }

    private int[] helper_dfs(List<Integer>[] graph, int node, int bob, int[] amount, boolean[] seen, int aliceDist) {
        int res = Integer.MIN_VALUE;
        seen[node] = true;

        int bobPathLen = node == bob ? 1 : 0;
        for (int nextNode : graph[node]) {
            if (seen[nextNode]) continue;
            int[] dfsNext = helper_dfs(graph, nextNode, bob, amount, seen, aliceDist + 1);
            if (dfsNext[1] > 0) bobPathLen = dfsNext[1] + 1;
            res = Math.max(res, dfsNext[0]);
        }

        if (bobPathLen > 0 && bobPathLen <= aliceDist) {
            if (bobPathLen == aliceDist) amount[node] = amount[node] / 2;
            else amount[node] = 0;
        }

        return new int[]{res == Integer.MIN_VALUE ? amount[node] : amount[node] + res, bobPathLen};
    }
}
