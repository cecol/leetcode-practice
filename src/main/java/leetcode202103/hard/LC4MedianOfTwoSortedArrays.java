package leetcode202103.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class LC4MedianOfTwoSortedArrays extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC4MedianOfTwoSortedArrays();
    }

    /**
     * https://leetcode.com/problems/median-of-two-sorted-arrays/discuss/2496/Concise-JAVA-solution-based-on-Binary-Search
     * O(log (m+n)), 用 binary search 找出 median
     * in getKth:
     * 從 n1的n1s 開始算與 n2的n2s開始算 來找到第k個
     * if (n1s >= n1.length) return n2[n2s + k - 1]; -> n1s 超出n1範圍, 所以第k個會在n2中 -> n2[n2s + k - 1]
     * if (n2s > n2.length - 1) return n1[n1s + k - 1]; 同上理
     * if (k == 1) return Math.min(n1[n1s], n2[n2s]); -> 如果要的正是第一個 , 直接拿小的出去, 因為大的是第2個
     * if (n1s + k / 2 - 1 < n1.length) mid1 = n1[n1s + k / 2 - 1]; -> 看看 n1 的 k/2個
     * if (n2s + k / 2 - 1 < n2.length) mid2 = n2[n2s + k / 2 - 1]; -> 看看 n2 的 k/2個
     * if (mid1 < mid2) -> 如果 n1的k/2個比較小, 代表 n1 k/2的右邊納入, 所以繼續往後找 -> n1s往前移動 n1s + k / 2
     * -> k - k / 2 代表k/2個已找到, 剩下要找 k-k/2個 -> binary search
     *
     * 幾個重點
     * l = (m + n + 1) / 2
     * r = (m + n + 2) / 2
     * -> 如果 m + n is odd, l==r, 所以 (m + n + 1) / 2 其實是移動1格(odd -> even 移動1)
     * -> 如果 m + n is odd, l==r, 所以 (m + n + 2) / 2 其實是移動2格(odd -> odd 移動1)
     * -> 如果 m + n is even, 所以 (m + n + 1) / 2 其實是移動0格(even -> odd  移動0)
     * -> 如果 m + n is even, 所以 (m + n + 2) / 2 其實是移動1格(even -> even 移動1)
     *
     * getKth(int[] n1, int n1s, int[] n2, int n2s, int k) 中 -> k 每次都是走一半
     * 1. 先檢查有無超出範圍 if (n1s > n1.length - 1) return n2[n2s + k - 1];
     * 2. 檢查是否 k 降為 1 -> 已經找到答案 如果是就是給 nums1, nums2之間的 min
     * 3. 找出誰是目前走 k/2 格最相對小, 就往前走
     * 4. 下一次往下走的k 會變成 k - k/2,  不能直接用 k/2, 因為可能有 1 的誤差
     * -> 回到步驟2 -> 檢查是否 k 降為 1, 如果下一次直接用 k/2, 那麼k可能就變成0, 就要用不一樣的理解方式了
     * -> 因為k 可能是 odd/even -> 所以必然用 k - k/2 確保不會漏掉餘數 1 的 case
     * 5. n1s + k/2 - 1, 因為 n1s是 0 based offset, k 是1 base steps, 所以得 -1 才能是真正的 offset
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int l = (m + n + 1) / 2;
        int r = (m + n + 2) / 2;
        return (getKth(nums1, 0, nums2, 0, l) + getKth(nums1, 0, nums2, 0, r)) / 2.0;
    }

    private int getKth(int[] n1, int n1s, int[] n2, int n2s, int k) {
        if (n1s > n1.length - 1) return n2[n2s + k - 1];
        if (n2s > n2.length - 1) return n1[n1s + k - 1];
        if (k == 1) return Math.min(n1[n1s], n2[n2s]);
        int mid1 = Integer.MAX_VALUE;
        int mid2 = Integer.MAX_VALUE;
        if (n1s + k / 2 - 1 < n1.length) mid1 = n1[n1s + k / 2 - 1];
        if (n2s + k / 2 - 1 < n2.length) mid2 = n2[n2s + k / 2 - 1];
        if (mid1 < mid2)
            return getKth(n1, n1s + k / 2, n2, n2s, k - k / 2);
        else
            return getKth(n1, n1s, n2, n2s + k / 2, k - k / 2);
    }

    /**
     * 有很直觀解法, 但真正要考的是 O(log (m+n)), 用 binary search 找出 median
     * 直接用heap解
     * 5 ms, faster than 21.15%
     */
    public double findMedianSortedArraysHeap(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        int l, r;
        if ((n1 + n2) % 2 == 0) {
            l = (n1 + n2) / 2 - 1;
            r = (n1 + n2) / 2;
        } else {
            l = r = (n1 + n2) / 2;
        }
        int i = 0, j = 0;
        PriorityQueue<Integer> q = new PriorityQueue<>((x, y) -> (y - x));
        while (q.size() < r) {
            if (i >= n1) q.offer(nums2[j++]);
            else if (j >= n2) q.offer(nums1[i++]);
            else if (nums1[i] <= nums2[j]) q.offer(nums1[i++]);
            else q.offer(nums2[j++]);
        }
        return l == r ? q.peek() : (q.poll() + q.poll()) / 2.0;
    }
}
