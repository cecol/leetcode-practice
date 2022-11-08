package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class LC1838FrequencyOfTheMostFrequentElement extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
    }

    /**
     * 我看得出來是 two pointer, 且是 window maintain i, i+1, .. j , i 到 j 的差值總合要  < k
     * 但要怎麼有效計算 i 到 j 差值總合 我只想到 O(N^2) 算, 沒有想到 O(N) 計算方式
     * https://leetcode.com/problems/frequency-of-the-most-frequent-element/discuss/1175090/JavaC%2B%2BPython-Sliding-Window
     * 看到 lee 思路我才明白要換個方向想
     *
     * 1. 記載目前的 sum
     * 2. sliding window 判定條件就是
     *    sum + k < nums[j] * (j-i+1)
     *    其實就是 nums[j] * (j-i+1) - sum <= k
     *    因為排序過了, j 以前都比 nums[j] 小, 大家(i, i+1, .. )都要加到 nums[j]
     *    所以期望cost <= k 就是大家都是 nums[j] * (j-i+1) - sum of (i, i+1, i+2 ...) <= k
     */
    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int i = 0;
        long sum = 0;
        int res = 1;
        for (int j = 0; j < nums.length; j++) {
            sum += nums[j];
            while (sum + k < (long) (j - i + 1) * nums[j]) sum -= nums[i++];
            res = Math.max(res, j - i + 1);
        }
        return res;
    }
}
