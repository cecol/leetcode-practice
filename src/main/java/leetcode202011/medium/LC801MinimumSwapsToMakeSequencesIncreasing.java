package leetcode202011.medium;

import leetcode20200921to20201031.BasicTemplate;

public class LC801MinimumSwapsToMakeSequencesIncreasing extends BasicTemplate {
    public static void main(String[] args) {
        var LC = new LC801MinimumSwapsToMakeSequencesIncreasing();
        var s = LC.minSwap(
                new int[]{1, 3, 5, 4},
                new int[]{1, 2, 3, 7});
        var s2 = LC.minSwap(
                new int[]{3, 3, 8, 9, 10},
                new int[]{1, 7, 4, 6, 8});
    }


    /**
     *  https://zxi.mytechroad.com/blog/dynamic-programming/leetcode-801-minimum-swaps-to-make-sequences-increasing/
     *  
     */
    public int minSwap(int[] A, int[] B) {
        if (A == null || A.length == 0 | A.length == 1) return 0;
        int[] dp = new int[A.length];
        for (int i = 1; i < dp.length; i++) {
            dp[i] = dp[i - 1];
            if (A[i] <= A[i - 1] || B[i] <= B[i - 1]) {
                dp[i]++;
                swap(i, A, B);
            }
        }
        return dp[dp.length - 1];
    }

    private void swap(int i, int[] A, int[] B) {
        int t = A[i];
        A[i] = B[i];
        B[i] = t;
    }
}
