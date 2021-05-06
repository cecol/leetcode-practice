package leetcode202105.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LC1099TwoSumLessThanK extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1099TwoSumLessThanK();
    }

    /**
     * 很直觀的先sort過, 頭尾開始找出max sum < k
     * */
    public int twoSumLessThanK(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length,l=0,r=n-1,res = -1;
        while(l<r) {
            if(nums[l] + nums[r] >= k) {
                r--;
            } else {
                res = Math.max(res, nums[l] + nums[r]);
                l++;
            }
        }
        return res;
    }
}
