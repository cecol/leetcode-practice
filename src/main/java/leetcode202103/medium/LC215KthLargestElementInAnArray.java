package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Deque;
import java.util.PriorityQueue;

public class LC215KthLargestElementInAnArray extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC215KthLargestElementInAnArray();
    }

    /**
     * 用 Heap 做是 faster than 60.19%
     * https://leetcode.com/problems/kth-largest-element-in-an-array/discuss/60294/Solution-explained
     * 只是沒想到直接 Arrays.sort(nums), 然後nums[N - k]; 是最快的  faster than 98.06% of Java
     * */
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> q = new PriorityQueue<>((i1, i2) -> -i1 - -i2);
        for (int n : nums) q.add(n);
        while (k > 1) {
            q.poll();
            k--;
        }
        return q.poll();
    }
}
