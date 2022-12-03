package leetcode202211.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class LC2334SubarrayWithElementsGreaterThanVaryingThreshold extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC2334SubarrayWithElementsGreaterThanVaryingThreshold();
    }

    /**
     * https://github.com/wisdompeak/LeetCode/tree/master/Stack/2334.Subarray-With-Elements-Greater-Than-Varying-Threshold
     * 完全沒想到這題跟 LC84LargestRectangleInHistogram 一模一樣
     * 雖然是問 subarray 每個值要 > threshold/subarray.length
     * 已知 array 都是正整數, 這樣想就好了
     * subarray 最小的 > threshold/k => subarray 最小的*k > threshold !!
     * k 是 subarray length, 這樣就是 LargestRectangleInHistogram 裏面每一 Rectangle 能踩到的最大 area = k * height[i]
     *
     * code 幾乎一模一樣 只是在算完 area > threshold 就可以回傳找到的 redeemW
     * */
    public int validSubarraySize(int[] nums, int threshold) {
        Stack<Integer> stk = new Stack<>();
        for (int i = 0; i <= nums.length; i++) {
            int v = i == nums.length ? 0 : nums[i];
            while (stk.size() > 0 && v < nums[stk.peek()]) {
                int redeemH = nums[stk.pop()];
                int redeemW = i - (stk.size() == 0 ? 0 : stk.peek() + 1);
                int redeem = redeemH * redeemW;
                if (redeem > threshold) return redeemW;
            }
            stk.push(i);
        }
        return -1;
    }
}
