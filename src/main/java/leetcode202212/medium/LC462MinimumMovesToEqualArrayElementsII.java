package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Stack;

public class LC462MinimumMovesToEqualArrayElementsII extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC462MinimumMovesToEqualArrayElementsII();
    }

    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int r = nums.length - 1,l = 0;
        int res = 0;
        while(l < r) res += nums[r--] - nums[l++];
        return res;
    }
}
