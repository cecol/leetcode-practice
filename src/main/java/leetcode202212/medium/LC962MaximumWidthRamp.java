package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC962MaximumWidthRamp extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC962MaximumWidthRamp();
    }

    /**
     * https://leetcode.com/problems/maximum-width-ramp/discuss/208348/JavaC%2B%2BPython-O(N)-Using-Stack
     * 這題不是很單純的 Stack monotocin 作法了
     * 但第一種解法很直觀 O (N Log N)
     * 還是維持一個 monotonic decrease list -> 不是 stack
     * 因為從 0 to n-1
     * 1. 看是否能加入 monotonic decrease list, 這個 list 不會 pop, 就是一個從頭到尾 monotonic decrease
     * 2. 當前如果 nums[i] > monotonic_decrease_ist.last 代表他可以兌現前面某些元素
     * 3. nums[i] 只兌現能造成最長距離的, 那就是找到第一個 <= nums[i] in list
     * - 右往左邊盡可能找 -> 用 binary search 找最快
     * - 到舊紀錄當前看過的 res
     * <p>
     * 第二種解法是 O(N) 其實也沒有很難
     * 因為是要找最長, 所以
     * 1. 先建立一個 monotonic decrease stack
     * 2. nums[i] 從尾巴開始往 stack 看, 找到第一個 nums[i] >= stack.top
     * 這時候更新 res,
     * 前面小的 pop 掉沒關係, 因為是尾巴開始找 被 pop 給 nums[i-1] 也不會造成更長結果了
     * Ex nums[i-1] < nums[i] 那 nums[i-1] 更不可能找到 stack 更深處,  前面 pop 掉的葉不可能創造更大 ramp
     * Ex nums[i-1] > nums[i] 那 nums[i-1] 可以找到 stack 更深處, 前面 pop 掉的本來也毫無營響
     */
    public int maxWidthRampFast(int[] nums) {
        Stack<Integer> sk = new Stack<>();
        int res = 0, n = nums.length;
        for (int i = 0; i < n; i++) {
            if (sk.size() == 0 || nums[sk.peek()] > nums[i]) sk.push(i);
        }
        for (int i = n - 1; i > 0; i--) {
            while (sk.size() > 0 && nums[i] >= nums[sk.peek()]) res = Math.max(res, i - sk.pop() + 1);
        }
        return res;
    }

    public int maxWidthRamp(int[] nums) {
        List<Integer> s = new ArrayList<>();
        int res = 0, n = nums.length;
        for (int i = 0; i < n; i++) {
            if (s.size() == 0 || nums[i] < nums[s.get(s.size() - 1)]) s.add(nums[i]);
            else {
                int l = 0, r = s.size() - 1;
                while (l < r) {
                    int mid = l + (r - l) / 2;
                    if (nums[s.get(mid)] <= nums[i]) r = mid;
                    else l = mid + 1;
                }
                res = Math.max(res, i - s.get(l));
            }
        }
        return res;
    }
}
