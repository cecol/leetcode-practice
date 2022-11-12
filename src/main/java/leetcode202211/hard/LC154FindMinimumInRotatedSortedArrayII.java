package leetcode202211.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

public class LC154FindMinimumInRotatedSortedArrayII extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC154FindMinimumInRotatedSortedArrayII();
    }

    /**
     * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/discuss/167981/Beats-100-Binary-Search-with-Explanations
     * 針對 rotated sorted array 有幾個永恆不變的真理
     * 1. if nums[l] > nums[m]: 代表  min 一定在 l to m, -> r = m
     * 2. if nums[m] > nums[r]: 代表  min 一定在 m to r, -> l = m+1
     * 4 nums[l] <= nums[m] <= nums[r], min 會在 l to m or m to r
     * Ex: [10, 1, 10, 10, 10] or [10, 10, 10, 1, 10] for example, for sure num[lo] which is 10 is not the answer.
     * I think what you actually mean in this case is ===> "hi--"
     * 這邊有解釋: https://github.com/wisdompeak/LeetCode/tree/master/Binary_Search/154.Find-Minimum-in-Rotated-Sorted-Array-II
     * 第三种情况，有一个非常tricky的技巧。既然无法判定mid是否在左区间还是右区间，
     * 但是因为nums[mid]和nums[right]一样，那么去掉nums[right]并不影响结果。
     * 去掉nums[right]（将右边界左移一位）反而可以进一步帮助分析mid所属的位置：如果下一步出现nums[mid]和nums[right]不一样
     * 那就依照之前的逻辑很好处理；否则就继续移动right，同样没有影响。
     */
    public int findMin(int[] nums) {
        int n = nums.length, l = 0, r = n - 1;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (nums[m] > nums[r]) l = m + 1;
            else if (nums[m] < nums[r]) r = m;
            else r--;
        }
        return nums[l];
    }
}
