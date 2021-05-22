package leetcode202105.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LC1168OptimizeWaterDistributionInAVillage extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1168OptimizeWaterDistributionInAVillage();
    }

    /**
     * 看得出來是 graph, 但沒有好好看出來是 MST, 這題是用Kruskal's來建立 tree
     * 1. 建立一個 dummy node到個點的 edge, cost 就是該點的 well 花費
     * 2. 然後把 dummy node到個點的 edge 加上題目給的 pipes -> sort by cost
     * 3. iterate edge -> union find 去找, 如果 parent 不一樣就 join, 並加上 edge cost
     * 加總的 res 就是需要的答案
     */
    int[] p = null;

    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        p = new int[n + 1];
        List<int[]> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            p[i + 1] = i + 1;
            edges.add(new int[]{0, i + 1, wells[i]});
        }
        for (int[] p : pipes) edges.add(p);
        Collections.sort(edges, (x, y) -> Integer.compare(x[2], y[2]));
        int res = 0;
        for (int[] e : edges) {
            int x = find(e[0]), y = find(e[1]);
            if(x != y) {
                res+=e[2];
                p[x] = y;
                --n;
            }
        }
        return res;
    }

    private int find(int x) {
        if (x != p[x]) p[x] = find(p[x]);
        return p[x];
    }
}
