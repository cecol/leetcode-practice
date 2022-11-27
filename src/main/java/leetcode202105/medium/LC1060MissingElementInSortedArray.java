package leetcode202105.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

public class LC1060MissingElementInSortedArray extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1060MissingElementInSortedArray();
    }

    /**
     * https://leetcode.com/problems/missing-element-in-sorted-array/discuss/477727/Java-Binary-Search-faster-than-100.00
     * 關鍵是要理解 nums[r], nums[l] 區間的 missing number 總數是 missing = nums[r] - nums[l] - (r - l);
     * 如果 missing < k 代表 k-th miss 一定在 nums[r] 更右邊
     * 所以直接 return nums[r] + k - missing (要把左邊有的 miss 拿掉)
     *
     * 若是 missing > k, k 就是在 nums[r], nums[l] 區間
     * 所以就是找
     * int leftMiss = nums[m] - nums[l] - (m - l);
     * 是否 leftMiss >= k
     * if k > leftMiss, 左邊縮
     * 但是 k 也要減去 leftMiss 當作兌現的 miss
     *
     * https://leetcode.com/problems/missing-element-in-sorted-array/discuss/536752/java-binary-search-beats-100100-with-clear-explanation
     * 但這題的邊界得是 while(l + 1 < r)
     * 因為如果要是最後回傳 nums[l] + k
     * 就得停在左邊最靠近k, 配上  while(l + 1 < r) 就會是剛好最後 l 落在 k 左邊
     * */
    public int missingElement(int[] nums, int k) {
        int l = 0, r = nums.length - 1;
        int missing = nums[r] - nums[l] - (r - l);
        if (k > missing) return nums[r] + (k - missing);
        while (l + 1 < r) {
            int m = l + (r - l) / 2 ;
            int leftMiss = nums[m] - nums[l] - (m - l);
            if (k <= leftMiss) r = m;
            else {
                l = m;
                k -= leftMiss;
            }
        }
        return nums[l] + k;
    }
}
