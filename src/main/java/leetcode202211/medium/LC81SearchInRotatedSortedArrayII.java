package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class LC81SearchInRotatedSortedArrayII extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");

    }

    /**
     * https://leetcode.com/problems/search-in-rotated-sorted-array-ii/discuss/28202/Neat-JAVA-solution-using-binary-search
     * 跟 154.Find-Minimum-in-Rotated-Sorted-Array-II 很類似
     * 要小心處理duplicate issue
     * 1. 先處理確定性的 大於 小於 範圍
     * 先判定哪邊是 sorted, 哪邊是 unsorted, 因為 rotate 過了, 會有一邊被破壞
     * 1. 判定右半邊是 sorted
     * if (nums[m] < nums[r] || nums[m] < nums[l])
     * nums[m] < nums[r] -> 右半邊是確定遞增 -> sorted 或者
     * nums[m] < nums[l] -> 左半邊是確定遞減 -> 左半邊一定 unsorted
     * 為什麼需要  nums[m] < nums[l] 來補充呢?  因為可能是  [1,1,0,0,0], nums[m] == nums[r] case 只得靠右半邊排協助判斷
     *
     * 接著 判定 target 是在 nums[m] 左邊還是右邊
     * target > nums[m] -> 很直觀
     * && target <= nums[r] -> 確保是真的在右半邊, 因為單獨 target > nums[m] 也可以出現在左半邊
     *
     * 2. 判定左半邊 sorted 同上
     * 3. 無法判定左半或者右半 就是說 nums[l] == nums[m] == nums[r]
     * 就好比 [1,0,1,1,1]
     * 這時候其實 l++ or r-- 都可以, 慢慢縮小, 來踩到 非 nums[l] == nums[m] == nums[r] 就可以繼續下去
     **/
    public boolean search(int[] nums, int target) {
        int n = nums.length;
        int l = 0, r = n - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (nums[m] == target) return true;
            if (nums[m] < nums[r] || nums[m] < nums[l]) {
                if (target > nums[m] && target <= nums[r]) l = m + 1;
                else r = m - 1;
            } else if (nums[m] > nums[l] || nums[m] > nums[r]) {
                if (target < nums[m] && target >= nums[l]) r = m - 1;
                else l = m + 1;
            } else r--; // can l++
        }
        return false;
    }
}
