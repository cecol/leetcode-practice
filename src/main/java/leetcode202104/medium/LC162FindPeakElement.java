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
     *
     * 第二次解的時候對這題沒什麼概念, 從頭理解一下為什麼 binary search可以解這題
     * 關鍵在於理解 binary search 的 invariants
     * (An invariant is more "conceptual" than a variable.
     * -> In general, it's a property of the program state that is always true.
     * -> A function or method that ensures that the invariant holds is said to maintain the invariant.)
     * 解題的作者用的 invariants 就是 nums[left - 1] < nums[left] && nums[right] > nums[right + 1]
     * -> 因為一開始 l = 0, r = n-1, 超出 array界外的都是 -infinite -> 所以符合 invariants
     *
     * 1. nums[left] > nums[left + 1]. From the invariant, nums[left - 1] < nums[left] => left is a peak
     * 因為 invariants 中 left > left -1, 所以當 left > left + 1 -> left就是答案
     *
     * 2. The function is increasing from left to right i.e. nums[left] < nums[left + 1] < .. < nums[right - 1] < nums[right].
     * -> From the invariant, nums[right] > nums[right + 1] => right is a peak
     * 因為 binary search 的 invariants 就是遞增 -> 所以當 right > right + 1 -> right 就是答案
     *
     * 3. the function increases for a while and then decreases
     * -> (in which case the point just before it starts decreasing is a peak) e.g. 2 5 6 3 (6 is the point in question)
     *
     * 因為一開始 l = 0, r = n-1, 超出 offset -1 & n 界外的都是 -infinite -> 所以符合 invariants: nums[left - 1] < nums[left] && nums[right] > nums[right + 1]
     * 所以知道 [l, r] 之間應該有個 peak
     * every step of binary search -> rebuild invariant by: mid = (left + right) / 2 and the following 2 cases:
     * -> 1. 因為nums[mid] < nums[mid + 1] -> 所以 [mid + 1, right] 還是保持 invariants,
     * -> 2. nums[mid] > nums[mid + 1]. -> 所以 [l, mid] 還是保持 invariants,
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
