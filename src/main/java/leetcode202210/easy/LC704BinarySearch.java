package leetcode202210.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LC704BinarySearch extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC704BinarySearch();
    }

    public int search(int[] nums, int target) {
        int l =0, r = nums.length-1;
        while(l<r) {
            int m = l + (r-l)/2;
            if(nums[m] >= target)r=m;
            else l=m+1;
        }
        return nums[l] == target?l:-1;
    }
}
