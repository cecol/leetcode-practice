package leetcode202102.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class LC685RedundantConnectionII extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC685RedundantConnectionII();
        var s = LC.findRedundantDirectedConnection(null);
    }


    /**
     * 有解過684無向圖版本  但這題思路比較複雜  想了想還是參考別人的想法
     * 這個中文解釋很清楚 該怎麼思考這題 後面的解發都是以這個中心點來出發
     * https://leetcode.com/problems/redundant-connection-ii/discuss/278105/topic
     * 基本概念概念就是
     * 1. 既然刪掉一個邊就是會有合法的樹 -> 遇到這種有強烈的先決條件問題我都不見得看得出來
     * 2. 沒辦法造成他是樹的情境
     * -> 1. 有node有兩個parent -> 2 in degree (parent可以有多個child)
     * -> 2. 有cycle -> 可能是沒有root -> 就是沒有node是 0 in-degree -> or 有人是1的情境
     * 依此來想就可以很清楚知道 如果去看所有node的 in degree 就大概可以知道哪個edge是多餘的
     * <p>
     * 這個回應裡面的第一個回應的解法比較理解 所以採用
     * https://leetcode.com/problems/redundant-connection-ii/discuss/108045/C%2B%2BJava-Union-Find-with-explanation-O(n)
     *
     * 其實跟684很像只是要多一些修正
     * 1. 先找出哪個node是否 in degree == 2
     * -> 1. 如果沒有, 代表是純cycle case -> 沒有root->沒有node是in-degree == 0
     * ->    因此所有edge下去union -> 只會有唯一一個造成cycle -> 回傳的那個就是
     * -> 2. 如果有 -> 可能有多個邊都可以選來刪除來達成合法 tree -> 所以需要拿最後一個邊
     * ->    所以從edges最後開始找, 若找到有edge有的node是 two in degree -> 省略該edge -> 下去建立union
     * ->    若省略該edge可以建立出tree(684 algo return null) -> 代表該edge就是答案
     * ->    其中
     * ->    int[] res = getRedundantEdge(edges, i);
     * ->    if (res == null) return edges[i];
     * ->    是必要的, 因為你拿到的這個邊, 儘管它踩到node parent == 2, 但這個邊可能不是造成cycle
     * ->    可能是另一個, 所以得下去getRedundantEdge(edges, i); 檢查 如果回傳null, 代表有建立出tree,
     * ->    那麼這個被省略的edge就是毒瘤 -> 回傳
     */
    public int[] findRedundantDirectedConnection(int[][] edges) {
        Map<Integer, Integer> inDegrees = new HashMap<>();
        int nodeIdHasTwoParent = -1;
        for (int[] e : edges) {
            inDegrees.put(e[1], inDegrees.getOrDefault(e[1], 0) + 1);
            if (inDegrees.get(e[1]) == 2) {
                nodeIdHasTwoParent = e[1];
                break;
            }
        }
        if (nodeIdHasTwoParent == -1) {
            return getRedundantEdge(edges, -1);
        } else {
            for (int i = edges.length - 1; i >= 0; i--) {
                if (edges[i][1] == nodeIdHasTwoParent) {
                    int[] res = getRedundantEdge(edges, i);
                    if (res == null) return edges[i];
                }
            }
        }
        return null;
    }

    private int[] getRedundantEdge(int[][] edges, int nodeToSkip) {
        int[] p = new int[edges.length + 1];
        for (int i = 0; i <= edges.length; i++) p[i] = i;
        for (int i = 0; i < edges.length; i++) {
            if (i == nodeToSkip) continue;
            if (!union(p, edges[i][0], edges[i][1])) return edges[i];
        }
        return null;
    }

    private int find(int[] p, int i) {
        while (p[i] != i) {
            p[i] = p[p[i]];
            i = p[i];
        }
        return i;
    }

    private boolean union(int[] p, int i, int j) {
        int pi = find(p, i);
        int pj = find(p, j);
        if (pi == pj) return false;
        p[pj] = pi;
        return true;
    }
}
