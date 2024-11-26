package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.HashMap;
import java.util.Map;

public class LC525ContiguousArray extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 沒過, 完全沒概念, 看到筆記才想起來的技法
    // 1. zero counter, 0++, 1--
    // 2. 當 zero == 0, 0 to i 都是 答案
    // 3. map 記載看過的 zero count, 如果有看過, 代表中間是 答案, 前面看過 zero = 2, 後面又再看一次, 代表中間都是 equal
    public int findMaxLength(int[] nums) {
        Map<Integer, Integer> m = new HashMap<>();
        int res = 0, zero = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) zero++;
            else zero--;
            if (zero == 0) res = i + 1;
            if (!m.containsKey(zero)) m.put(zero, i);
            else res = Math.max(res, i - m.get(zero));
        }
        return res;
    }
}
