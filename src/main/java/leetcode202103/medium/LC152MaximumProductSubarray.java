package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class LC152MaximumProductSubarray extends BasicTemplate {

    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC152MaximumProductSubarray();
    }

    /**
     * 沒想到除了O(N^2)有什麼更快的, 事實上有 O(N)
     * https://leetcode.com/problems/maximum-product-subarray/discuss/48230/Possibly-simplest-solution-with-O(n)-time-complexity
     * 基本上很像 LC53MaximumSubarray 記載到目前尾止的 sum -> 記載到目前nums[i]為止的 min / max
     * 更直白易懂的DP結構應該是 maxProduct[i],minProduct[i]
     * 但其實只要最大的話, 就只要一個 global variable去記住看到的
     * 如果遇到當前 nums[i] 是負數, 代表 min/max互換
     * -> imax = Math.max(nums[i], imax * nums[i]);
     * -> imin = Math.min(nums[i], imin * nums[i]);
     * 這邊在說的, 目前的a[i] 累計的 max/min 要馬是他自己(所以是從新累計)或者是原本的再繼續累計
     * 為什麼？ 因為如果全部都是負數, 那最後結果的 max 應該是最大的負數
     */
    public int maxProduct(int[] nums) {
        int r = nums[0];
        for (int i = 1, imax = r, imin = r; i < nums.length; i++) {
            if (nums[i] < 0) {
                int t = imax;
                imax = imin;
                imin = t;
            }
            imax = Math.max(nums[i], imax * nums[i]);
            imin = Math.min(nums[i], imin * nums[i]);
            r = Math.max(r, imax);
        }
        return r;
    }
}
