package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.PriorityQueue;
import java.util.Stack;

public class LC1135ConnectingCitiesWithMinimumCost extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1135ConnectingCitiesWithMinimumCost();
    }

    public int minimumCost(int n, int[][] connections) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> (x[2] - y[2]));
        int[] p = new int[n];
        for (int i = 0; i < n; i++) p[i] = i;
        for (int[] conn : connections) pq.offer(conn);
        int res = 0;
        while (pq.size() > 0) {
            int[] conn = pq.poll();
            int pi = f(p, conn[0] - 1);
            int pj = f(p, conn[1] - 1);
            if (pi == pj) continue;
            res += conn[2];
            p[pj] = pi;
        }
        int count = 0;
        for (int i = 0; i < n; i++) if (p[i] == i) count++;
        if(count > 1) return -1;
        return res;
    }

    int f(int[] p, int i) {
        while (p[i] != i) {
            p[i] = p[p[i]];
            i = p[i];
        }
        return p[i];
    }
}
