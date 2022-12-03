package leetcode202211.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class LC2488CountSubarraysWithMedianK extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC2488CountSubarraysWithMedianK();
    }

    /**
     * 基本概念是
     * 1. sub array 裏面 > median 的數目 - < median 數目 = 0, or 1
     * 2. 不用在意實際實質, 只在意 大於 or 小於 median, 所以要轉換 nums = [3, 4, 1, 5, 6] -> [-1,0,-1,1,1]
     * - -1 = 小於 median
     * - 1 = 大於 median
     * 3. 根據 1 的定義, 可以知道 SUM OF SUBARRAY should be 0 for odd length and 1 for even length
     * 4. 接下來就是 pre sum nums = [3, 4, 1, 5, 6] -> [-1,0,-1,1,1] 來找 sub array 符合條件
     * - 先算右半邊 pre sum 的 加總過程 (是算這個 [-1,0,-1,1,1])
     * - 在走左半邊 pre sum 的 加總過程 (是算這個 [-1,0,-1,1,1])
     * -   過程中看右半邊的 pre sum 的出現有的話就是 加入 result
     * -   result += map.getOrDefault(0 - sum, 0); odd length case
     * -   result += map.getOrDefault(1 - sum, 0); even length case
     *
     * 為什麼要分左右半邊來看? 不是常見的 presum 邊加 邊找前面出現過的?
     * https://leetcode.com/problems/count-subarrays-with-median-k/discuss/2852030/Java-oror-PrefixSum-of-1-01-oror-Total-left-right-oror-Commented
     * 其實看這個解法就知道
     * 因為你的 pre sum sub array 區間得包含 k, 可以你從 0 to n-1 來算 pre sum 可能會踩到 sub array 根本沒有 包含 k
     * 所以他在算完 pre sum 0 to n-1 還要減去 左右結果裡面不 包含 ｋ 的 case
     */
    public int countSubarrays(int[] nums, int k) {
        int idx = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == k) {
                idx = i;
                break;
            }
        }

        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        for (int i = idx; i < nums.length; i++) {
            sum += Integer.compare(nums[i], k);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        long result = 0;
        sum = 0;

        for (int i = idx; i >= 0; i--) {
            sum += Integer.compare(nums[i], k);
            result += map.getOrDefault(0 - sum, 0);
            result += map.getOrDefault(1 - sum, 0);
        }
        return (int) result;
    }
}
