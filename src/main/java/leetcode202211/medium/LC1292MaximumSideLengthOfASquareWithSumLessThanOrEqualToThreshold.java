package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LC1292MaximumSideLengthOfASquareWithSumLessThanOrEqualToThreshold extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1292MaximumSideLengthOfASquareWithSumLessThanOrEqualToThreshold();
    }

    /**
     * https://leetcode.com/problems/maximum-side-length-of-a-square-with-sum-less-than-or-equal-to-threshold/discuss/452041/Java-Short-Solution-!!!
     * https://github.com/wisdompeak/LeetCode/blob/master/Binary_Search/1292.Maximum-Side-Length-of-a-Square-with-Sum-Less-than-or-Equal-to-Threshold/1292.Maximum-Side-Length-of-a-Square-with-Sum-Less-than-or-Equal-to-Threshold.cpp
     * 這題很直觀 但有幾個很關鍵技巧重點
     * 1, 要算 square 加總要先算好 preSum, 這樣之後根據 square 寬度來查 preSum 就 O(1)
     * 2. 這邊的 binary search 由最寬度最小到最大寬度不斷往右逼近, 所以 我常用的 template 要改
     * while(l < r) {
     *     int mid = r - (r - l)/2; 不在是常見的 l + (r - l) / 2; 或者也可以 l + (r - l+1)/2;
     *     if(isOK()) l=mid; => 目前寬度合法 我們要往大的逼近, 所以要往右走,
     *     else r = mid -1
     * }
     *
     * 原本裳間的 if(isOK) r = mid; 是用來找最小可能性 => 他其實是 if( mid <= xxx ) r = mid => 往左找
     * 但這次是 if( 當前寬度合法 ) l = mid; => 往大的繼續找看看, 所以概念完全反過來了 !!!
     *
     * */
    public int maxSideLength(int[][] mat, int threshold) {
        int m = mat.length, n = mat[0].length;
        int[][] sum = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++)
            for (int j = 1; j <= n; j++)
                sum[i][j] = mat[i - 1][j - 1] + sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1];
        int l = 0, r = Math.min(m, n);
        while (l < r) {
            int mid = l + (r - l + 1) / 2;
            if (isOK(mat, sum, mid, threshold)) {
                l = mid;
            }
            else r = mid - 1;
        }
        return l;
    }

    boolean isOK(int[][] mat, int[][] presum, int side, int threshold) {
        int m = mat.length, n = mat[0].length;
        for (int i = side; i <= m; i++)
            for (int j = side; j <= n; j++) {
                int sum = presum[i][j] - presum[i - side][j] - presum[i][j - side] + presum[i - side][j - side];
                if (sum <= threshold) return true;
            }
        return false;
    }
}
