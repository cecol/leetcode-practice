package leetcode202102.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LC713SubarrayProductLessThanK extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC713SubarrayProductLessThanK();
        var s = LC.numSubarrayProductLessThanK(new int[]{10, 5, 2, 6}, 100);
    }

    /**
     * https://leetcode.com/problems/subarray-product-less-than-k/discuss/108861/JavaC%2B%2B-Clean-Code-with-Explanation
     * 知道暴力解但是時間超過, 有想到要用當前走道的格子乘積來反推(因為subarray)
     * 但還是不明白 two pointer的含義在哪 直到看到了答案
     * 基本上就是
     * pointer 1 一直往後乘績
     * pointer 2 ->  pointer1 -> pointer 2 中間乘積 < k, 如果大於 k, pointer 2就得往前移動, 直到小於 k
     * 概念上就是維持一個window, 該window的乘積小於k 中間的長度就是可以累加的subarray可能性
     * */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k == 0) return 0;
        int cnt = 0;
        int pro = 1;
        for (int i = 0, j = 0; j < nums.length; j++) {
            pro *= nums[j];
            while (i <= j && pro >= k) {
                pro /= nums[i++];
            }
            cnt += j - i + 1;
        }
        return cnt;
    }
}
