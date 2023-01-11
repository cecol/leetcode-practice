package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class LC324WiggleSortII extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC324WiggleSortII();
    }

    /**
     * https://github.com/wisdompeak/LeetCode/tree/master/Others/324.Wiggle-Sort-II
     * 其實 nums[0] < nums[1] > nums[2] < nums[3]....
     * 是有個 pattern
     * even offset 都小於左右 odd offset
     * odd offset 都大於左右 even offset
     * odd offset 之間大小沒差
     * even offset 之間大小沒差
     * <p>
     * Ex: nums = [1,5,1,1,6,4]
     * 排序後 nums = [1,1,1,4,5,6]
     * 然後取雙指針
     * 1. 正中間 - i
     * 2. 最尾端 - j
     * - [1,1,1,4,5,6]
     * -      i     j
     * odd offset 取 j
     * even offset 取 i
     * 結果 [1,6,1,5,1,4]
     * <p>
     * odd offset 之間大小沒差
     * even offset 之間大小沒差
     * <p>
     * odd 是由最大遞減來取
     * even 也要是由最大遞減來取
     * <p>
     * 如果由 i = 0, j = (n-1) / 2 來取會遇到問題
     * 因為如果前半都是一樣數字 Ex: [1,1,1,1,2,3,4]
     * 取出來的就會出錯           [1,1,1,2,1,3,1]
     */
    public void wiggleSort(int[] nums) {
        int n = nums.length;
        int[] sort = Arrays.copyOf(nums, n);
        Arrays.sort(sort);
        int i = (n - 1) / 2, j = n - 1;
        for (int k = 0; k < n; k++) {
            if (k % 2 == 0) nums[k] = sort[i--];
            else nums[k] = sort[j--];
        }
    }
}
