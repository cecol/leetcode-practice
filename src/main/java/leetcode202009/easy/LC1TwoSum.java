package leetcode202009.easy;


import leetcode202009.BasicTemplate;

import java.util.HashMap;
import java.util.Map;

public class LC1TwoSum extends BasicTemplate {
    public static void main(String[] args) {
        var LC = new LC1TwoSum();
        var s = LC.twoSum(new int[]{2, 5, 5, 11}, 10);
    }

    public int[] twoSum(int[] nums, int target) {
        int[] r = new int[2];
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (m.get(target - nums[i]) != null) {
                r[0] = m.get(target - nums[i]);
                r[1] = i;
                return r;
            }
            m.put(nums[i], i);
        }
        return r;
    }

}
