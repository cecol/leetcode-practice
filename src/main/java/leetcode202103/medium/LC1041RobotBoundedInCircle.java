package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class LC1041RobotBoundedInCircle extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1041RobotBoundedInCircle();
    }


    /**
     * https://leetcode.com/problems/robot-bounded-in-circle/discuss/290859/java-solution-%2B-clear-explanation
     * 沒有想出思路, 看到別人的解釋才明白
     * 1. 從一個點出發, 走完指令後
     * -> 1. 如果回到原點 那麼就是 cycle
     * -> 2. 如果你不在原點且面向北邊(一開始的面向) 那就是一直無止境走下去
     * -> 3. 其他結果也都是cycle -> 就是說不是在原點但也不是面向北邊, 一直走下去也是 cycle
     * 有個細節是 int[][] dirs = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
     * 怎麼會是上{1, 0}, 下{0, 1}, 左{-1, 0}, 右{0, -1}？
     * 其實真正要的邏輯是 當取LL 時候, 原本的方向要變成反向
     * 代表原本的 dir[0], 取L = (dir+3) % 4 -> 兩次 LL 會變成 dir[2] -> dir[0] 跟 dir[2] 要設定好互為反方向
     * 所以 {1, 0}, {0, 1}, {-1, 0}, {0, -1} 的設置只要 0,跟 3互為反方向就好
     * 所以可以是 {1, 0}, {0, 1}, {-1, 0}, {0, -1}
     * or {0, 1}, {-1, 0}, {0, -1}, {1, 0} 不過這個比較像是正確版的上下左右
     */
    public boolean isRobotBounded(String instructions) {
        int[] cur = new int[]{0, 0};
        int dir = 0;
        int[][] dirs = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        for (char i : instructions.toCharArray()) {
            if (i == 'G') {
                cur[0] += dirs[dir][0];
                cur[1] += dirs[dir][1];
            } else if (i == 'L') dir = (dir + 3) % 4;
            else dir = (dir + 1) % 4;
        }
        if(cur[0] == 0 && cur[1] == 0) return true;
        if(dir == 0) return false;
        return true;
    }
}
