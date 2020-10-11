package leetcode202009.medium;

import leetcode202009.BasicTemplate;

import java.util.*;

public class LC300LongestIncreasingSubsequence extends BasicTemplate {

    public static void main(String[] args) {
        var LC = new LC300LongestIncreasingSubsequence();
        var r = LC.lengthOfLISNlogN(new int[]{1, 101, 1, 1, 1, 1});
    }

    public int lengthOfLIS(int[] nums) {
        if (nums.length == 1) return 1;
        if (nums.length == 0) return 0;
        int m = 1;
        int[] p = new int[nums.length];
        Arrays.fill(p, 1);
        for (int i = 1; i < nums.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    p[i] = Math.max(p[j] + 1, p[i]);
                    if (p[i] > m) m = p[i];
                }
            }
        }
        return m;
    }

    /**
     * O(NlogN) solution
     * https://www.itread01.com/content/1550205367.html
     * Using TreeMap for binary insert next possible LIS element
     */
    public int lengthOfLISNlogN(int[] nums) {
        if (nums.length == 0) return 0;
        TreeMap<Integer, Integer> all = new TreeMap<>();
        all.put(nums[0], 0);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > all.lastKey()) {
                all.put(nums[i], 0);
            } else {
                if (all.get(nums[i]) == null) {
                    Integer h = all.higherKey(nums[i]);
                    if (h != null) {
                        all.remove(h);
                        all.put(nums[i], 0);
                    }
                }
            }
        }
        return all.size();
    }
}
