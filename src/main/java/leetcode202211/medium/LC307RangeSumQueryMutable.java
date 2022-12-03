package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import leetcode20200921to20201031.medium.algo.NumArraySegmentTree;

public class LC307RangeSumQueryMutable extends BasicTemplate {

    public static void main(String[] args) {
        var LC2 = new NumArraySegmentTree(new int[]{1, 3, 5});
    }

    class SegTree {
        SegTree left, right;
        int sum, start, end;

        SegTree(int s, int e) {
            this.start = s;
            this.end = e;
        }
    }

    SegTree root;

    SegTree build(int[] nums, int s, int e) {
        if (s > e) return null;
        SegTree rt = new SegTree(s, e);
        if (s == e) rt.sum = nums[s];
        else {
            int mid = s + (e - s) / 2;
            rt.left = build(nums, s, mid);
            rt.right = build(nums, mid + 1, e);
            rt.sum = rt.left.sum + rt.right.sum;
        }
        return rt;
    }

    void update(SegTree rt, int idx, int val) {
        if (rt.start == rt.end) rt.sum = val;
        else {
            int mid = rt.start + (rt.end - rt.start) / 2;
            if (idx <= mid) update(rt.left, idx, val);
            else update(rt.right, idx, val);
            rt.sum = rt.left.sum + rt.right.sum;
        }
    }

    int sum(SegTree rt, int l, int r) {
        if (rt.start == l && rt.end == r) return rt.sum;
        int mid = rt.start + (rt.end - rt.start) / 2;
        if (r <= mid) return sum(rt.left, l, r);
        else if (l > mid) return sum(rt.right, l, r);
        else return sum(rt.left, l, mid) + sum(rt.right, mid + 1, r);
    }

    public void NumArray(int[] nums) {
        root = build(nums, 0, nums.length - 1);
    }

    public void update(int index, int val) {
        update(root, index, val);
    }

    public int sumRange(int left, int right) {
        return sum(root, left, right);
    }
}
