package leetcode202105.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

public class LC1060MissingElementInSortedArray extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1060MissingElementInSortedArray();
    }

    public int missingElement(int[] nums, int k) {
        int n = nums.length;
        int restK = k;
        int res = 0;
        for (int i = 1; i < n; i++) {
            int diff = nums[i] - nums[i - 1];
            if (diff == 1) continue;
            else {
                if (diff - 1 >= restK) {
                    res = nums[i - 1] + restK;
                    restK = 0;
                    break;
                } else {
                    restK -= (diff - 1);
                }
            }
        }
        if (restK > 0) res = nums[n - 1] + restK;
        return res;
    }
}
