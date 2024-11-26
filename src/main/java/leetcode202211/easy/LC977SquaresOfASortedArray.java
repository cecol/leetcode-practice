package leetcode202211.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class LC977SquaresOfASortedArray extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC977SquaresOfASortedArray();
    }

    /**
     * 無腦 O(NlogN) 是很好解
     * 或者 counting sort, 但耗廢 space O(N)
     * 沒想到 O(N) 正解這麼單純,
     * https://leetcode.com/problems/squares-of-a-sorted-array/discuss/221922/Java-two-pointers-O(N)
     * two pointers, 從尾巴開始放, 因為尾巴是最大的,
     * 要嘛最右邊因為是負數最小(但因為平方後便最大)開使放, 或者最左邊正數開始放
     */
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int i = 0, j = n - 1;
        int[] result = new int[n];

        for (int p = n - 1; p >= 0; p--) {
            int imx = nums[i] * nums[i];
            int jmx = nums[j] * nums[j];
            if (imx > jmx) {
                i++;
            } else j--;
            result[p] = Math.max(imx, jmx);
        }
        return result;
    }
}
