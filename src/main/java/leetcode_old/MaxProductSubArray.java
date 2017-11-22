package leetcode_old;

public class MaxProductSubArray {
    public static void main(String[] args) {
        int[] nums = {0, 3, 5, 0, -2, -5, -4, 6, -1, 0};
        System.out.println(maxProduct(nums));
    }

    public static int maxProduct(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int max = A[0], min = A[0], result = A[0];
        for (int i = 1; i < A.length; i++) {
            int temp = max;
            max = Math.max(Math.max(max * A[i], min * A[i]), A[i]);
            min = Math.min(Math.min(temp * A[i], min * A[i]), A[i]);
            if (max > result) {
                result = max;
            }
            System.out.println(i + " " + max + " " + min + " " + result);
        }
        return result;
    }
}
