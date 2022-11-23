package leetcode202211.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class LC2322MinimumScoreAfterRemovalsOnATree extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC2322MinimumScoreAfterRemovalsOnATree();
    }

    int res = Integer.MAX_VALUE;

    /**
     * https://leetcode.com/problems/minimum-score-after-removals-on-a-tree/discuss/2200046/Java-and-C%2B%2B-Simple-Solution-using-DFS-twice.-O(N2)
     * 就題意直接刪除任兩 edges 來找答案, 因為限制太少, 所以得 DFS 下去找
     * 關鍵在於 有效找出 XOR
     * 1. 先找出整棵樹的 totalXor
     * 2. 找出 remove one edge xor1
     * 3  找出 remove one edge xor2
     * 4 xor3 = totalXor ^ xor1 ^ xor2
     *
     * 但要怎麼 dfs 出三棵 sub tree xor?
     * 第一次 dfs, 就是繼續往下每個鄰居 i 走 dfs, 只是不回頭走 parent, 因為是 tree, 所以絕對不會有迴圈且可以走到某一個到底
     * 鄰居 i dfs 出來後 就有 xor1,
     * 就再取該鄰居 i 為 block, 再去 另一個 dfs(helper), 除了不走 parent, 也不走該鄰居 in dfs(helper)
     * 在 dfs(helper) 裡面取出 xor2, 再接著算出 xor3, 就可以找出 min/max 來更新
     *
     * 我在想 刪掉任 2 edges 其實是 dfs 一直走的過程中不要去走到該點就好
     * 所以 dfs 一路走到底 (tree) - dfs(0, graph, nums, -1, totalXor); - xor1
     * 另一個 dfs 一路走到底但不要走該點 - helper(0, graph, nums, -1, nbr, nbrXor, totalXor); - xor2
     * 這樣就可以推出 xor3
     *
     * 而不是真的選出 edges 來刪除
     * 因為它是 tree, 所以只要不走到該點, 就是代表刪除該 node, 成為另一個 sub tree
     * */
    public int minimumScore(int[] nums, int[][] edges) {
        int n = nums.length;
        ArrayList<Integer>[] graph = new ArrayList[n];
        int totalXor = 0;
        for (int i = 0; i < n; i++) {
            totalXor ^= nums[i];
            graph[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }
        dfsSubTree1(0, graph, nums, -1, totalXor);
        return res;
    }

    int dfsSubTree1(int src, ArrayList<Integer>[] graph, int[] nums, int par, int totalXor) {
        int myXor = nums[src];
        for (Integer nbr : graph[src]) {
            if (nbr != par) {
                int subTree1Xor1 = dfsSubTree1(nbr, graph, nums, src, totalXor);
                int subTree2NotIncludeNbr = nbr;
                dfsSubTree2(0, graph, nums, -1, subTree2NotIncludeNbr, subTree1Xor1, totalXor);
                myXor ^= subTree1Xor1;
            }
        }
        return myXor;
    }

    int dfsSubTree2(int src, ArrayList<Integer>[] graph, int[] nums, int parent,
                    int subTree2NotIncludeNbr, int xor1, int totalXor) {
        int myXor = nums[src];
        for (Integer nbr : graph[src]) {
            if (nbr != parent && nbr != subTree2NotIncludeNbr) {
                int xor2 = dfsSubTree2(nbr, graph, nums, src, subTree2NotIncludeNbr, xor1, totalXor);
                int xor3 = (totalXor ^ xor1) ^ xor2;

                int max = Math.max(xor1, Math.max(xor2, xor3));
                int min = Math.min(xor1, Math.min(xor2, xor3));
                res = Math.min(res, max - min);
                myXor ^= xor2;
            }
        }
        return myXor;
    }
}
