package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class LC215KthLargestElementInAnArray extends BasicTemplate {
    public static void main(String[] args) {
    }

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((x, y) -> {
            return y - x;
        });
        for (int n : nums) pq.offer(n);
        int res = 0;
        while (k > 0) {
            k--;
            res = pq.poll();
        }

        return res;
    }
}
