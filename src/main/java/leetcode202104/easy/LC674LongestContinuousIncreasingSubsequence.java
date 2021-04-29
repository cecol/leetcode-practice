package leetcode202104.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class LC674LongestContinuousIncreasingSubsequence extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC674LongestContinuousIncreasingSubsequence();
    }

    /**
     * 很直觀的一題 faster than 98.66%
     * */
    public int findLengthOfLCIS(int[] nums) {
        int n = nums.length;
        if(n <= 1) return n;
        int[] count = new int[n];
        count[0]=1;
        int res = 1;
        for(int i=1;i<n;i++) {
            if(nums[i] > nums[i-1]) count[i] = count[i-1] + 1;
            else count[i]=1;
            res = Math.max(res, count[i]);
        }
        return res;
    }
}
