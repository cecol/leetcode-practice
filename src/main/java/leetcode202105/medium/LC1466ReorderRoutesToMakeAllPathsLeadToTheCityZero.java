package leetcode202105.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC1466ReorderRoutesToMakeAllPathsLeadToTheCityZero extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1466ReorderRoutesToMakeAllPathsLeadToTheCityZero();
    }

    /**
     * 花了很多時間, 但一直沒有歸納出好的解法
     * 一開始就完全想錯, 用 union find 想說最後都要 find到0, 可能可以解 但可能也很複雜
     * 後來看到人家解法, 基本概念就是
     * 1. bidirectional, 把所有提供的 path 都變成雙向, ex有 2->3, 多加上 3->2 ,
     * -> 然後取可以走向 0的 path, 如果取到的 path 是後來加上的 bidirectional, 那就是 count++
     * https://leetcode.com/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero/discuss/844552/Easy-BFS-Solution-in-JAVA-in-Depth
     * 這邊有圖解
     * 2. 但後來有看到更簡單的理解方式: 紀錄可以到 0 的 node (Set<Integer> v = new HashSet<>();)
     * 所以當 v.size() < n 代表要繼續找
     * 所以每次都一直 loop int[][] connections
     * 1. 如果v.contains(c[1]), 直接加入
     * 2. v.contains(c[0]) && !v.contains(c[1]) 代表反向 -> 要加入 & res++
     */
    public int minReorder(int n, int[][] connections) {
        Set<Integer> v = new HashSet<>();
        v.add(0);
        int res = 0;
        while (v.size() < n) {
            for (int[] c : connections) {
                if (v.contains(c[1])) v.add(c[0]);
                else if (v.contains(c[0]) && !v.contains(c[1])) {
                    res++;
                    v.add(c[1]);
                }
            }
        }
        return res;
    }
}
