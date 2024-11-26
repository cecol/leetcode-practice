package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.*;

public class LC128LongestConsecutiveSequence extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 沒過, Set 解法會超時, 只能用 Map
    // Map 放 value + 該 value 邊界延伸長度
    // 每個值進來看 +1/-1 邊界延伸, 算出最大邊界
    // 每個值進去 map 後, 也要去延伸自己 left/right 的 key 邊界
    // 盲點: 會以為。區間全部要更新, 但其實是要邊界就好, 反正最大值在中途就記錄下來了
    // 而且中途值不會再被碰到 (因為 +1/-1 早已經走過)
    public int longestConsecutive(int[] nums) {
        Map<Integer, Integer> m = new HashMap<>();
        int res = 0;
        for (int n : nums) {
            if (!m.containsKey(n)) {
                int left = m.containsKey(n - 1) ? m.get(n - 1) : 0;
                int right = m.containsKey(n + 1) ? m.get(n + 1) : 0;
                int sum = left + right + 1;
                m.put(n, sum);
                res = Math.max(res, sum);
                m.put(n - left, sum);
                m.put(n + right, sum);
            }
        }
        return res;
    }

}
