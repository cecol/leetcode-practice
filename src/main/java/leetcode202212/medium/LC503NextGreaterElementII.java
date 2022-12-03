package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Stack;

public class LC503NextGreaterElementII extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC503NextGreaterElementII();
        LC.nextGreaterElements(new int[]{1, 2, 1});
    }

    /**
     * https://github.com/wisdompeak/LeetCode/tree/master/Stack/503.Next-Greater-Element-II
     * 基本思路有想出來
     * 往後找下一個最大 就是維持一個 monotonic decrease
     * 如果當前 nums[i] > stack.top, 就是兌現前面的 next greater 直到 nums[i] < stack.top
     * 但因為是 circular integer , 所以得走 array 2遍
     *
     * 第二遍因為 stack 會有 第一遍剩下來的 decrease, 所以還可以有機會再兌現還沒找到的
     *
     * 第二遍解法可以無腦第二次 for loop
     * or
     * 一個 for loop 但是 for (int i = 0; i < 2 * n; i++) { int idx = i % n;
     * 會更漂亮
     * */
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        Stack<Integer> sk = new Stack<>();
        int[] res = new int[n];
        Arrays.fill(res, -1);
        for (int i = 0; i < 2 * n; i++) {
            int idx = i % n;
            while (sk.size() > 0 && nums[idx] > nums[sk.peek()]) {
                res[sk.pop()] = nums[idx];
            }
            sk.push(idx);
        }

        return res;
    }
}
