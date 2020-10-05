package leetcode202009.easy;


import leetcode202009.BasicTemplate;

import java.util.Arrays;
import java.util.HashMap;

public class LC1365HowManyNumbersAreSmallerThanTheCurrentNumber extends BasicTemplate {
    public static void main(String[] args) {
        var LC = new LC1365HowManyNumbersAreSmallerThanTheCurrentNumber();
        LC.smallerNumbersThanCurrentByHashMap(new int[]{8, 1, 2, 2, 3});
    }

    /**
     * https://leetcode.com/problems/how-many-numbers-are-smaller-than-the-current-number/
     * Constraints:
     * 2 <= nums.length <= 500
     * 0 <= nums[i] <= 100
     */
    public int[] smallerNumbersThanCurrentByConstrains(int[] nums) {
        log.debug("nums: {}", nums);
        int[] c = new int[101];
        for (int i = 0; i < nums.length; i++) c[nums[i]]++;
        log.debug("c: {}", c);
        int[] s = new int[101];
        s[0] = c[0];
        for (int i = 1; i < c.length; i++) s[i] = c[i] + s[i - 1];
        log.debug("s: {}", s);
        int[] r = new int[nums.length];
        for (int i = 0; i < r.length; i++) r[i] = s[nums[i]] - c[nums[i]];
        log.debug("r: {}", r);
        return r;
    }

    /**
     * by hashmap for count, but is slower than above solution
     */
    public int[] smallerNumbersThanCurrentByHashMap(int[] nums) {
        int[] r = Arrays.copyOf(nums, nums.length);
        Arrays.sort(nums);
        int pre = nums[0];
        int count = 0;
        HashMap<Integer, Integer> m = new HashMap<>();
        m.put(pre, count);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != pre) {
                m.put(nums[i], count);
                pre = nums[i];
            }
            count++;
        }
        log.debug("m: {}", m);
        for (int i = 0; i < r.length; i++) r[i] = m.get(r[i]);
        return r;
    }
}
