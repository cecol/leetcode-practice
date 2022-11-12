package leetcode202211.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC719FindKthSmallestPairDistance extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC719FindKthSmallestPairDistance();
    }

    /**
     * 沒想過這題可以先 sort, 不太能抓到 binary search 相關題目是否可以先sort
     * 這題需要 sort 是因為要找 pair 需要 pointer
     * 1. 先 sort 過後就知道 max = nums[n-1] - nums[0]
     * min 就是 兩兩 比較找出 min
     * 2. 由 min 到 max, binary search
     * 關鍵是要怎麼找 pair sum <= mid
     * https://leetcode.com/problems/find-k-th-smallest-pair-distance/discuss/714768/Explanation-with-concrete-example-(Binary-Search-%2B-Sliding-Window)
     * 用 sliding window 找
     * // guess = 1
     * // i = 0, j = 1
     * [1,1,2,3]
     *  i j       <-- cnt = 1
     * // j++
     * [1,1,2,3]
     *  i   j     <-- cnt = 2
     * // j++
     * [1,1,2,3]
     *  i     j   <-- j - i  = 3 - 1 = 2 which does not satisfy <= guess
     * // i++
     * [1,1,2,3]
     *    i   j  <-- cnt = 3
     * // since j reach maximum so we will keep moving i
     * [1,1,2,3]
     *      i j  <-- cnt = 4
     *  i = 0 to n , j = 1 往後找 pair
     *  只要 nums[i] + nums[j] <= mid => j++
     *  count += j - i - 1 就是所有總數
     * */
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int l = nums[1] - nums[0];
        for (int i = 0; i < n - 1; i++) l = Math.min(l, nums[i + 1] - nums[i]);
        int r = nums[n - 1] - nums[0];
        int mid;
        while (l < r) {
            mid = l + (r - l) / 2;
            int c = 0;
            int i = 0, j = 1;
            for (; i < n; i++) {
                for (; j < n && nums[j] - nums[i] <= mid; ) j++;
                c += j - i - 1;
            }

            if (c >= k) r = mid;
            else l = mid + 1;
        }
        return l;
    }


}
