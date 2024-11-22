package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class LC973KClosestPointsToOrigin extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 很直觀想起 PriorityQueue 就能完解, 但是 qp comparator 想了一下
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> {
            return (x[0] * x[0] + x[1] * x[1]) - (y[0] * y[0] + y[1] * y[1]);
        });

        for (int[] p : points) pq.add(p);
        ArrayList<int[]> res = new ArrayList<>();
        while (k > 0) {
            k--;
            res.add(pq.poll());
        }
        return res.toArray(new int[res.size()][2]);
    }
}
