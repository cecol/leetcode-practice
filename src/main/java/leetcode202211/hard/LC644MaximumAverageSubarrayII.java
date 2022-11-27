package leetcode202211.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

public class LC644MaximumAverageSubarrayII extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC644MaximumAverageSubarrayII();
    }

    /**
     * https://leetcode.com/problems/maximum-average-subarray-ii/discuss/132164/Java-Clear-Code-using-Binary-Search-with-Detailed-Explanations
     * 這題真的很難直接看出怎麼應用 binary search
     * 基本概念就是
     * 1. 從 min, max 之間 不斷逼近 binary search
     * 因為 min/max 可以是 Integer.MIN_VALUE/Integer.MAX_VALUE
     * 但因為 -10^4 <= nums[i] <= 10^4, 所以可以直接找拿 -10001, 10001
     * 2. 然後 min/max 找出 mid, 拿這個 mid 去確認是否 nums 真的有 sub array average 大於 mid,
     * 是的話 left = mid, 不然 right - mid
     * <p>
     * 要確認是否 nums 真的有 sub array average 大於 mid
     * 先把 nums[i] - mid, 剩下來的值  如果有 sub array 加總 > 0 就一定有 average > mid
     * 1. for (int i = 0; i < k; i++) cur += a[i];  先看看前面 k 個是否真的有 avg > mid
     * 2. for (int i = k; i < n; i++) 前面k個沒有, 就再往後看
     * - cur += a[i]; 再往前看
     * - pre += a[i - k]; 前面 subarray 是否要保留
     * - if(pre < 0)  如果前面 subarray 是負數, 根本不考慮保留, 因為對於找 avg > mid 沒幫助
     * -   cur -= pre; - 不考慮保留, 就把他減回來吧
     * -   pre = 0; - 不考慮保留 就 reset 0
     * - if (cur >= 0) return true; 一但找到 avg > mid 就先回傳了
     */
    public double findMaxAverage(int[] nums, int k) {
        double l = -10001, r = 10001;
        while (l + 0.00001 < r) {
            double m = l + (r - l) / 2;
            if (canLarger(nums, k, m)) l = m;
            else r = m;
        }
        return l;
    }

    boolean canLarger(int[] nums, int k, double m) {
        int n = nums.length;
        double[] a = new double[n];
        for (int i = 0; i < n; i++) a[i] = nums[i] - m;
        double cur = 0, pre = 0;
        for (int i = 0; i < k; i++) cur += a[i];
        if (cur >= 0) return true;
        for (int i = k; i < n; i++) {
            cur += a[i];
            pre += a[i - k];
            if (pre < 0) {
                cur -= pre;
                pre = 0;
            }
            if (cur >= 0) return true;
        }
        return false;
    }
}
