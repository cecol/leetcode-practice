package leetcode202411.easy;


import leetcode20200921to20201031.BasicTemplate;

import java.util.HashMap;
import java.util.Map;

public class LC977SquaresOfASortedArray extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 沒過, O(N) 解法沒想到 頭尾內縮就好, int[] res 從尾巴開始擺即可
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        int i = 0, j = n - 1;
        for (int mv = n - 1; mv >= 0; mv--) {
            int si = nums[i] * nums[i];
            int sj = nums[j] * nums[j];
            if (si > sj) i++;
            else j--;
            res[mv] = Math.max(si, sj);
        }
        return res;
    }

}
