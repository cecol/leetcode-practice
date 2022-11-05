package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class LC55JumpGame extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC55JumpGame();
        LC.canJump(new int[]{3, 2, 1, 0, 4});
    }

    /**
     * 我一開始有解出來, 但是是 DFS 下去, 速度超慢
     * 後來想說從尾巴往前倒推, 但是一直沒有想出正確辦法, 也不確定這樣想是否正確
     * 直到看到最佳解
     * https://leetcode.com/problems/jump-game/discuss/20900/Simplest-O(N)-solution-with-constant-space
     * 是從最後開始nums.length - 2找沒錯, 但是要記得一開始的 last = nums.length - 1;
     * 然後因為 i 可以到達 last, last就變成 i 繼續下去
     */
    public boolean canJump(int[] nums) {
        int last = nums.length - 1, i;
        for (i = nums.length - 2; i >= 0; i--) {
            if (i + nums[i] >= last) last = i;
        }
        return last == 0;
    }
}
