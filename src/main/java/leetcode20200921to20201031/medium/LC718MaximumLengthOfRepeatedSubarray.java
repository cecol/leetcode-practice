package leetcode20200921to20201031.medium;

import leetcode20200921to20201031.BasicTemplate;

public class LC718MaximumLengthOfRepeatedSubarray extends BasicTemplate {

    public static void main(String[] args) {
        var LC = new LC718MaximumLengthOfRepeatedSubarray();
        var r = LC.findLength(new int[]{1, 2, 3, 2, 1}, new int[]{3, 2, 1, 4, 7});
    }

    public int findLength(int[] A, int[] B) {
        int max = 0;
        if (A == null || B == null || A.length == 0 || B.length == 0) return max;
        int[][] dp = new int[A.length][B.length];
        for (int i = 0; i < A.length; i++)
            if (B[0] == A[i]) {
                dp[0][i] = 1;
                max = 1;
            }
        ;
        for (int i = 1; i < dp.length; i++)
            for (int j = 0; j < dp[i].length; j++) {
                if (B[i] == A[j]) {
                    if (j > 0) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = 1;
                    }
                    max = Math.max(max, dp[i][j]);
                }
            }
        log.debug("{}", max);
        return max;
    }
}
