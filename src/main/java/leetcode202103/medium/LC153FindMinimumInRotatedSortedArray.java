package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC153FindMinimumInRotatedSortedArray extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC153FindMinimumInRotatedSortedArray();
    }

    public int findMin(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        int min = nums[0];
        int amax = min + n;
    }
}
