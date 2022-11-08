package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LC16_3SumClosest extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");

    }

    /**
     * https://leetcode.com/problems/3sum-closest/discuss/352747/Java-or-3-pointers-or-Explained
     * 其實跟3SUM一樣, 透過pointers 來找答案
     * 第一次看到沒把握 還是看解答才確認
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int res = 0;
        for (int i = 0, minDiff = Integer.MAX_VALUE; i < nums.length - 2; i++) {
            for (int j = i + 1, l = nums.length - 1; j < l; ) {
                int s = nums[i] + nums[j] + nums[l];
                int diff = Math.abs(target - s);
                if (diff < minDiff) {
                    res = s;
                    minDiff = diff;
                }
                if (s > target) l--;
                else j++;
            }
        }
        return res;
    }
}
