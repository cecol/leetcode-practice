package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC2049CountNodesWithTheHighestScore extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC2049CountNodesWithTheHighestScore();
        LC.countHighestScoreNodes(new int[]{-1, 2, 0, 2, 0});
    }

    /**
     * https://leetcode.com/problems/count-nodes-with-the-highest-score/discuss/1537878/Java-Solution-or-Subnodes-Count-or-With-Explanation
     * 覺得這題我有想到答案, 但很多細節沒處理好 一直 TLE
     * 細節
     * 1. 建立 parent to child map 應該用 List<Integer>[] tree = new ArrayList[n]; 就好
     * 2. 建立 每個 node count 應該用  int[] count = new int[n];
     * 配上 Arrays.fill(count, 1);
     * 填滿 count 可以用 dfs 配合 List<Integer>[] tree
     * 3. 計算 prod 是要用 long
     * */
    public int countHighestScoreNodes(int[] parents) {
        int n = parents.length;
        List<Integer>[] tree = new ArrayList[n];
        for (int i = 0; i < n; i++) tree[i] = new ArrayList<>();
        for (int i = 1; i < n; i++) tree[parents[i]].add(i);
        int[] count = new int[n];
        Arrays.fill(count, 1);
        dfs(0, tree, count);

        long max = 0, res = 0;
        HashMap<Long, Long> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            long prod = 1;
            for (int ch : tree[i]) prod *= count[ch];

            if (i != 0) prod *= n - count[i];
            map.put(prod, map.getOrDefault(prod, 0L)+ 1L);
            if(prod >= max) {
                max = prod;
                res = map.get(prod);
            }
        }
        return (int)res;
    }

    int dfs(int node, List<Integer>[] tree, int[] count) {
        for (int next : tree[node]) count[node] += dfs(next, tree, count);
        return count[node];
    }
}
