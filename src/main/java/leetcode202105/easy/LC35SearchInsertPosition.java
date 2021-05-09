package leetcode202105.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class LC35SearchInsertPosition extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC35SearchInsertPosition();
    }

    /**
     * 很直觀的binary search, 只是因為對於沒找到的情況要給出 insert offset
     * 所以還要多檢查 if(target > nums[l]) return l+1;
     * 所以以要記得如果用法是 while(i<r) 且 if(nums[m] < target) l = m+1; 且 else r=m;
     * 最後停止點就是 l==r  代表區間內找不到了
     * 有兩種可能
     * 1. nums[l] == target
     * 2. nums[l] != target
     * -> 1. nums[l] < target -> l+1就是要insert位置, 因為 r 可能是邊境or更大的的值
     * -> 2. nums[l] > target -> 就是要insert位置
     * */
    public int searchInsert(int[] nums, int target) {
        int l = 0, r = nums.length-1;
        while(l<r) {
            int m = (l+r)/2;
            if(nums[m] < target) l = m+1;
            else r=m;
        }
        if(target > nums[l]) return l+1;
        return l;
    }
}
