package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC18_4sum extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");

    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Long>> res = kSum(nums, 0, 4, target);
        List<List<Integer>> res2 = new ArrayList<>();
        for (List<Long> r : res) {
            List<Integer> t = new ArrayList<>();
            for (Long l : r) t.add(l.intValue());
            res2.add(t);
        }
        return res2;
    }

    /**
     * generalized for kSums
     * https://leetcode.com/problems/4sum/discuss/8609/My-solution-generalized-for-kSums-in-JAVA
     * kSum 分割成
     * 1. 2 sum
     * 2. reduce k sum to k-1 sum problems
     */
    private List<List<Long>> kSum(int[] nums, int start, int k, long target) {
        int len = nums.length;
        List<List<Long>> res = new ArrayList<>();
        if (k == 2) {
            int l = start, r = len - 1;
            while (l < r) {
                long sum = nums[l] + nums[r];
                if (target == sum) {
                    List<Long> path = new ArrayList<>();
                    path.add((long) nums[l]);
                    path.add((long) nums[r]);
                    res.add(path);
                    while (l < r && nums[l + 1] == nums[l]) l++;
                    while (l < r && nums[r - 1] == nums[l]) r--;
                    l++;
                    r--;
                } else if (target < sum) r--;
                else l++;
            }
        } else {
            for (int i = start; i < len - k + 1; i++) {
                if (i > start && nums[i] == nums[i - 1]) continue;
                List<List<Long>> kLess1Sum = kSum(nums, i + 1, k - 1, target - nums[i]);
                for (List<Long> s : kLess1Sum) s.add(0, (long) nums[i]);
                res.addAll(kLess1Sum);
            }
        }
        return res;
    }
}
