public class WiggleSortII {
    public static void main(String[] args) {
        int[] nums = {1, 3, 2, 2, 3, 1};
        int[] nums1 = {1, 1, 2, 1, 2, 2, 1};
        int[] nums2 = {3, 2, 1, 3};
        int[] nums3 = {4, 5, 5, 6};
        int[] nums4 = {2,3,3,2,2,2,1,1};
        wiggleSort(nums4);
        for (int k : nums4) System.out.print(k + " ");
    }

    public static void wiggleSort(int[] nums) {
        if (nums.length == 0) return;
        java.util.Arrays.sort(nums);
        int[] copy = new int[nums.length];
        for (int i : nums) System.out.print(i + " ");
        System.out.println();
        int j = nums.length % 2 == 1 ? nums.length / 2 : nums.length / 2 - 1;
        for (int i = 0, m = nums.length - 1; i < nums.length; i++) {
            if (i % 2 == 0) {
                copy[i] = nums[j];
                j--;
            } else {
                copy[i] = nums[m];
                m--;
            }
        }
        for (int i = 0; i < nums.length; i++) nums[i] = copy[i];
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
