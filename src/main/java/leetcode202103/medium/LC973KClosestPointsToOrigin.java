package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.list.ListNode;

import java.util.Arrays;
import java.util.PriorityQueue;

public class LC973KClosestPointsToOrigin extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC973KClosestPointsToOrigin();
    }

    /**
     * https://leetcode.com/problems/k-closest-points-to-origin/discuss/220235/Java-Three-solutions-to-this-classical-K-th-problem.
     * 這題很直觀用 heap 來找 k 是很容易的
     * 21 ms, faster than 65.57% of Java
     * 但有更佳解就是 quick select -> 找 pivot
     * 1. worst case也可能還是 N^2
     * 2. 3 ms, faster than 98.61%
     */
    public int[][] kClosest(int[][] points, int k) {
        int len = points.length, l = 0, r = len - 1;
        while (l <= r) {
            int pivot = quick(points, l, r);
            if (pivot == k) break;
            else if (pivot < k) l = pivot + 1;
            else r = pivot - 1;
        }
        return Arrays.copyOfRange(points, 0, k);
    }

    private int quick(int[][] p, int l, int r) {
        int[] pivot = p[l];
        while (l < r) {
            while (l < r && compare(pivot, p[r]) <= 0) r--;
            p[l] = p[r];
            while (l < r && compare(p[l], pivot) <= 0) l++;
            p[r] = p[l];
        }
        p[l] = pivot;
        return l;
    }

    private int compare(int[] x, int[] y) {
        return x[0] * x[0] + x[1] * x[1] - y[0] * y[0] - y[1] * y[1];
    }

    public int[][] kClosestHeap(int[][] points, int k) {
        PriorityQueue<int[]> q = new PriorityQueue<int[]>((x, y) -> x[0] - y[0]);
        for (int[] p : points) {
            q.offer(new int[]{dist(p[0], p[1]), p[0], p[1]});
        }
        int[][] res = new int[k][2];
        for (int i = 0; i < k; i++) {
            int[] p = q.poll();
            res[i][0] = p[1];
            res[i][1] = p[2];
        }
        return res;
    }

    private int dist(int x, int y) {
        return x * x + y * y;
    }

}
