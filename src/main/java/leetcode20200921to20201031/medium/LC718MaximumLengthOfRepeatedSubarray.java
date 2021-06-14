package leetcode20200921to20201031.medium;

import leetcode20200921to20201031.BasicTemplate;

public class LC718MaximumLengthOfRepeatedSubarray extends BasicTemplate {

    public static void main(String[] args) {
        var LC = new LC718MaximumLengthOfRepeatedSubarray();
        var r = LC.findLength(new int[]{1, 2, 3, 2, 1}, new int[]{3, 2, 1, 4, 7});
    }
    /**
     * https://leetcode.com/problems/maximum-length-of-repeated-subarray/discuss/109039/Concise-Java-DP%3A-Same-idea-of-Longest-Common-Substring
     * 很久回來做, 才覺得應當參考思路比較清晰的答案
     * 1. int[][] dp = new int[m+1][n+1]; -> dp[0][j] or dp[i][0] 一定是0, 因為有一邊是empty string, 沒什麼好比對
     * 2. if(nums1[i-1] == nums2[j-1]) -> dp[i][j] = dp[i-1][j-1] + 1; 只有在比對中才有substring 串接意義
     * ->  所以沒比對中, 也不用去繼承前面的, 因為要 substring
     * 3. 用一個 max 來記錄目前看到最長的比對
     * */
    public int findLength(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int[][] dp = new int[m+1][n+1];
        int mx = 0;
        for(int i=1;i<=m;i++) {
            for(int j=1;j<=n;j++) {
                if(nums1[i-1] == nums2[j-1]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                    mx = Math.max(mx, dp[i][j]);
                }
            }
        }
        return mx;
    }
}
