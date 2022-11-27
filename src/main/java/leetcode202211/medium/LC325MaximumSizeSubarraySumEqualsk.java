package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LC325MaximumSizeSubarraySumEqualsk extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC325MaximumSizeSubarraySumEqualsk();
        LC.maxSubArrayLen(new int[]{-2,-1,2,1}, 1);
    }

    public int maxSubArrayLen(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        map.put(0, 0);
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k)) res = Math.max(res, i - map.get(sum - k));
            if (!map.containsKey(sum)) map.put(sum, i);
            log.debug("{}, {}, {}", map, res, sum);
        }
        log.debug("{}, {}", map, res);
        return res;
    }
}
