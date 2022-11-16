package leetcode202211.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class LC1658MinimumOperationsToReduceXToZero extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1658MinimumOperationsToReduceXToZero();
    }

    /**
     * 這題太不直觀了, 是要反過來看這題才能解
     * https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero/discuss/2136570/Change-Your-Perspective-or-JAVA-Explanation
     * 作者也說面試就是要考你這種 在一堆條件不明或者轉移你注意力的情況下怎麼找到核心問題
     * 作者是說他會試著重構問題
     * <p>
     * 簡單來說 消掉左右加總來符合 x
     * 剩下來的就是中間 subarray , 反過來說就是找到 最大的[中間 subarray sum] 使得 total sum - [中間 sum] 剛好是 x
     * 所以就是找preSum : total sum - x
     * tow pointer or preSum Map
     *
     * https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero/discuss/935935/Java-Detailed-Explanation-O(N)-Prefix-SumMap-Longest-Target-Sub-Array
     * java preSum Map 解法
     */
    public int minOperations(int[] nums, int x) {
        int target = -x;
        for (int n : nums) target += n;
        Map<Integer, Integer> mc = new HashMap<>();
        mc.put(0, -1);
        int res = Integer.MIN_VALUE, sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (mc.containsKey(sum - target)) res = Math.max(res, i - mc.get(sum - target));
            mc.put(sum, i);
        }
        return res == Integer.MIN_VALUE ? -1 : nums.length - res;
    }
}
