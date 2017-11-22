package leetcode_old;

public class KthLargestElementArray {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        System.out.println(findKthLargest(nums, 2));
    }

    public static int findKthLargest(int[] nums, int k) {
        return quickSelect(0, nums.length - 1, nums, nums.length - k);
    }

    public static int quickSelect(int l, int r, int[] a, int k) {
        if (l == r) return a[l];
        int p = (r - l) / 2 + l;
        int pv = a[p], storeIndex = l;
        swap(a, r, p);
        for (int i = l; i < r; i++) {
            if (a[i] <= pv) {
                swap(a, storeIndex, i);
                storeIndex++;
            }
        }
        swap(a, r, storeIndex);
        if (k == storeIndex) return a[storeIndex];
        else if (k < storeIndex) return quickSelect(l, storeIndex - 1, a, k);
        else return quickSelect(storeIndex + 1, r, a, k);
    }

    public static void swap(int[] a, int p1, int p2) {
        if (p1 == p2) return;
        int t = a[p1];
        a[p1] = a[p2];
        a[p2] = t;
    }
}
