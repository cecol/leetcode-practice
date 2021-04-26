package leetcode202104.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LC162FindPeakElement extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC162FindPeakElement();
        LC.findPeakElement(new int[]{-2147483648, -2147483647});
    }

    /**
     * https://leetcode.com/problems/find-peak-element/discuss/50239/Java-solution-and-explanation-using-invariants
     * 是有自己解出來  速度也夠快 faster than 100.00%
     * 但實在極度不精簡, 處理各種case
     * 其實只要比較 mid 跟 mid + 1
     * 這個是更精簡方式 只比較 m, m+1
     */
    public int findPeakElement(int[] nums) {
        int n = nums.length;
        if (n == 1) return 0;
        int l = 0, r = n - 1;
        while (l < r) {
            int m = (l + r) / 2;
            int m1 = m + 1;
            if (nums[m] < nums[m1]) l = m1;
            else r = m;
        }
        return (l == n - 1 || nums[l] > nums[l + 1]) ? l : r;
    }

    public int findPeakElementMime(int[] nums) {
        int n = nums.length;
        if (n == 1) return 0;
        int l = 0, r = n - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (mid == 0) {
                if (nums[mid + 1] > nums[mid]) l = mid + 1;
                else l = mid;
                break;
            } else if (mid == n - 1) {
                if (nums[mid - 1] > nums[mid]) l = mid - 1;
                else l = mid;
                break;
            } else {
                if (nums[mid - 1] < nums[mid] && nums[mid + 1] < nums[mid]) {
                    l = mid;
                    break;
                } else if (nums[mid - 1] < nums[mid] && nums[mid + 1] > nums[mid]) l = mid + 1;
                else r = mid;
            }
        }
        return l;
    }
}
