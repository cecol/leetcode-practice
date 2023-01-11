package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Stack;

public class LC45JumpGameII extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC45JumpGameII();
    }

    /**
     * https://github.com/wisdompeak/LeetCode/tree/master/Greedy/045.Jump-Game-II
     * 用 DP 可以解 但是 O(N^2) 很慢
     * 真正的 Greedy 是可以 O(N)
     *
     * BFS 對每個點下去走 最遠的點就好
     * https://leetcode.com/problems/jump-game-ii/solutions/18014/concise-o-n-one-loop-java-solution-based-on-greedy/
     * 不過這個解法更乾淨
     * 每一格下去走, 記載目前看到的 curFarthest = Math.max(curFarthest, nums[i] + i);
     * 還要記載 目前上一個截止在哪 curEnd
     * 如果 i == curEnd, 代表 當初 "前面 nums[i]  + i" 創造的 curFarthest 已經走到了
     * (這時候的curFarthest 已經是下個 i 走出來的)
     *
     * 所以 jump++, 更新 curEnd = curFarthest;
     * 當前這格 i 以內 所看到的下一個 最遠 該跳的 curEnd
     * */
    public int jump(int[] nums) {
        int n = nums.length, jump = 0, curEnd = 0, curFarthest = 0;
        for (int i = 0; i < n; i++) {
            curFarthest = Math.max(curFarthest, nums[i] + i);
            if (i == curEnd) {
                jump++;
                curEnd = curFarthest;
            }
        }
        return jump;
    }
}
