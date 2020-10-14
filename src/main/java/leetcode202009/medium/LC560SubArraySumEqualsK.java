package leetcode202009.medium;

import leetcode202009.BasicTemplate;

import java.util.HashMap;
import java.util.Map;

public class LC560SubArraySumEqualsK extends BasicTemplate {

    public static void main(String[] args) {
        var LC = new LC560SubArraySumEqualsK();
        var r = LC.subarraySumOptimized(new int[]{-1, -1, 1}, 0);
    }

    public int subarraySum(int[] nums, int k) {
        int c = 0;
        if (nums == null || nums.length == 0 || (nums.length == 1 && nums[0] != k)) return 0;
        for (int i = 0; i < nums.length; i++) {
            var s = 0;
            for (int j = i; j < nums.length; j++) {
                s += nums[j];
                if (s == k) c++;
            }
        }
        log.debug("{}", c);
        return c;
    }

    /**
     * explaination:
     *  https://leetcode.com/problems/subarray-sum-equals-k/discuss/803317/Java-Solution-with-Detailed-Explanation
     *  https://leetcode.com/problems/subarray-sum-equals-k/discuss/124151/22ms-Java-Hash-with-Explanations
     *  https://leetcode.com/problems/subarray-sum-equals-k/discuss/876722/Yet-another-explanation-in-Java
     */
    public int subarraySumOptimized(int[] nums, int k) {
        int c = 0;
        if (nums == null || nums.length == 0 || (nums.length == 1 && nums[0] != k)) return 0;
        Map<Integer, Integer> sm = new HashMap<>();
        sm.put(0, 1);
        int ss = 0;
        for (int n : nums) {
            ss += n;
            c += sm.getOrDefault(ss - k, 0);
            sm.put(ss, sm.getOrDefault(ss, 0) + 1);
        }
        return c;
    }
}
