package leetcode202211.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;
import java.util.TreeSet;

public class LC363MaxSumOfRectangleNoLargerThanK extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC363MaxSumOfRectangleNoLargerThanK();
    }

    /**
     * https://leetcode.com/problems/max-sum-of-rectangle-no-larger-than-k/discuss/1313721/JavaPython-Sub-problem%3A-Max-Sum-of-Subarray-No-Larger-Than-K-Clean-and-Concise
     * 想不到這題這麼直觀暴力法解了, 就是 2D 暴力法成 1D 下去算 preSum
     * 只是因為要接近k, 所以要 TreeSet 幫忙找
     *
     * 1. 2D 從 r1 0 to m, 配上 r2 = r1 to m
     * 就是每一row 配後續rows 所有組合都加總到 1D array arr[n]
     * 然後該 1D array 下去 preSum 去算 最接近 k 的結果
     *
     * 用一個 TreeSet 紀錄 preSum
     * 因為要找之前的 preSum, 可以 curSum - preSum <= k
     * 所以是 curSum - k <= preSum
     * TreeSet.ceiling(curSum - k) 去找, 不是取 floor
     * 取 floor 會太接近 curSum
     * 是要剛好距離 curSum 遠到剛好是 k, 所以減完要 ceiling, 如果取 floor 可能會 > k
     */
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        int res = Integer.MIN_VALUE;
        for (int r1 = 0; r1 < m; r1++) {
            int[] arr = new int[n];
            for (int r2 = r1; r2 < m; r2++) {
                for (int c = 0; c < n; c++) arr[c] += matrix[r2][c];
                res = Math.max(res, maxSumSubArray(arr, n, k));
            }

        }
        return res;
    }

    int maxSumSubArray(int[] arr, int n, int k) {
        TreeSet<Integer> ts = new TreeSet<>();
        ts.add(0);
        int res = Integer.MIN_VALUE;
        for (int i = 0, right = 0; i < n; i++) {
            right += arr[i];
            Integer left = ts.ceiling(right - k);
            if (left != null) res = Math.max(res, right - left);
            ts.add(right);
        }
        return res;
    }
}
