package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class LC310MinimumHeightTrees extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 沒過, 完全沒印象是
    // 1. 找出 leaves, leaves + bfs 往內刪減 直到 剩下 n = 1 or 2 就是答案
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> res = new ArrayList<>();
        if (n == 1) return List.of(0);
        Set<Integer>[] ne = new HashSet[n];
        for (int i = 0; i < n; i++) ne[i] = new HashSet<>();
        for (int[] e : edges) {
            ne[e[0]].add(e[1]);
            ne[e[1]].add(e[0]);
        }
        for (int i = 0; i < n; i++) if (ne[i].size() == 1) res.add(i);
        while (n > 2) {
            n -= res.size();
            List<Integer> res2 = new ArrayList<>();
            for (int i : res) {
                int p = ne[i].iterator().next();
                ne[p].remove(i);
                if (ne[p].size() == 1) res2.add(p);
            }
            res = res2;
        }

        return res;
    }
}
