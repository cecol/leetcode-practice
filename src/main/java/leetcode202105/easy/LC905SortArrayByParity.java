package leetcode202105.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LC905SortArrayByParity extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC905SortArrayByParity();
    }

    /**
     * 很直觀的 前odd 後even 交換機制
     * */
    public int[] sortArrayByParity(int[] nums) {
        int n = nums.length;
        if(n==1) return nums;
        int l =0,r=n-1;
        while(l<r) {
            while(l<r && nums[l]%2==0) l++;
            while(l<r && nums[r]%2==1) r--;
            if(l<r) {
                int t = nums[l];
                nums[l] = nums[r];
                nums[r] = t;
                l++;
                r--;
            }
        }
        return nums;
    }
}
