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

    /**
     * 沒想到 rotate array , 用很基本的 binary search 就可以解
     * 其實只要比 nums[mid] < nums[r] -> 代表一定遞增 -> 那麼最小 不可能是 m+1 -> r之間, worst case 也只會是 nums[m]
     * 而其他case 就是 l 要移動到 m+1
     * 其實另一個判定就是 nums[m] >= nums[l] && nums[m] > nums[r]
     * 我其實沒有很明白 為什麼
     * l,r 移動時候, 有時候是 l = m + 1, 有時候是 l = m;
     * 應該是有細節的邏輯在其中
     * 這題的解法 nums[mid] < nums[r] 是比較直觀 r = m;, 因為 不可能是 m+1 -> r之間 -> 但為什麼其他case 就要 l = m+1;
     * 其實隱含的是 其他cases , nums[m]不會是答案, 所以直接跳過 m
     * 總有一邊縮減要 m + 1 or m - 1
     * 看來要考慮的是, 在那個情境下, nums[m]是否是淺在對象
     * 所以 當 nums[m] > nums[r] -> 代表 m->r 之間有下去又有上來, nums[m] 一定不是選項 所以縮l 時候要 l = m+1;
     * */
    public int findMin(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        int l = 0, r = n - 1;
        while (l < r) {
            int m = (l + r) / 2;
            if (nums[m] > nums[r]) {
                r = m;
            } else l = m + 1;
        }
        return nums[l];
    }
}
