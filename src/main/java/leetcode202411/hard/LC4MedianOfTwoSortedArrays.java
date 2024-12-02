package leetcode202411.hard;


import leetcode20200921to20201031.BasicTemplate;

import java.util.PriorityQueue;

public class LC4MedianOfTwoSortedArrays extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 沒過
    // 1. 主要是 先算出 l, r,
    // 2. 遞迴找第 K
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int l = (m + n + 1) / 2;
        int r = (m + n + 2) / 2;
        return (getK(nums1, 0, nums2, 0, l) + getK(nums1, 0, nums2, 0, r)) / 2.0;
    }

    int getK(int[] n1, int n1s, int[] n2, int n2s, int k) {
        if (n1s > n1.length - 1) return n2[n2s + k - 1];
        if (n2s > n2.length - 1) return n1[n1s + k - 1];
        if (k == 1) return Math.min(n1[n1s], n2[n2s]);
        int m1 = Integer.MAX_VALUE;
        int m2 = Integer.MAX_VALUE;
        if (n1s + k / 2 - 1 < n1.length) m1 = n1[n1s + k / 2 - 1];
        if (n2s + k / 2 - 1 < n2.length) m2 = n2[n2s + k / 2 - 1];
        if (m1 < m2) return getK(n1, n1s + k / 2, n2, n2s, k - k / 2);
        else return getK(n1, n1s, n2, n2s + k / 2, k - k / 2);
    }
}
