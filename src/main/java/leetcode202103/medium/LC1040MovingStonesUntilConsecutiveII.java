package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class LC1040MovingStonesUntilConsecutiveII extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1040MovingStonesUntilConsecutiveII();
    }

    /**
     * 這題花了20min還是看不懂題目, 直到看到其他人解釋
     * https://leetcode.com/problems/moving-stones-until-consecutive-ii/discuss/286707/JavaC%2B%2BPython-Sliding-Window
     * 給n個 stones, 找出 n 連續位置來放stones
     * -> sliding window size = n
     * -> how many stones are already in this window
     * -> move outside stones into the window
     * -> the number of outside stones == the number of moves
     * ->   but for endpoint stone, it needs to be moved to non-endpoint position
     *
     * 要算出最多 moves, 就是都放在最左leftmost or 都放在最右rightmost
     * 這個可以直接算出來 -> 但我沒看出來
     * 這題還是看不懂 之後再回來看吧 花太多時間了
     */
    public int[] numMovesStonesII(int[] stones) {
        Arrays.sort(stones);
        int i = 0, n = stones.length, low = n;
        int high = Math.max(stones[n - 1] - n + 2 - stones[1], stones[n - 2] - stones[0] - n + 2);
        for (int j = 0; j < n; j++) {

        }

        return new int[]{low, high};
    }
}
