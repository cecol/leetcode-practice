package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Deque;
import java.util.LinkedList;
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
     */
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> q = new PriorityQueue<>((i1, i2) -> -i1 - -i2);
        for (int n : nums) q.add(n);
        while (k > 1) {
            q.poll();
            k--;
        }
        return q.poll();
    }

    /**
     * O(n) 解法, 是有想到說用 count sort
     * 只是看到 10000, -10000 區間就覺得不是, 其實也是可以解
     * 看題目要你 O(n) 應該就是 count sort 無誤
     * 特別在處理 -1 -> -10000 用 int[] table = new int[2 * 10000 + 2]; 來解
     */
    public int findKthLargest2(int[] nums, int k) {
        int[] table = new int[2 * 10000 + 2];
        for (int i : nums) {
            table[i + 10000]++;
        }
        for (int i = table.length - 1; i >= 0; --i) {
            k -= table[i];
            if (k <= 0)
                return i - 10000;
        }
        return 0;
    }
}
