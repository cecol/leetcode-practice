package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

public class LC487MaxConsecutiveOnesII extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC487MaxConsecutiveOnesII();
    }

    /**
     * https://github.com/wisdompeak/LeetCode/tree/master/Dynamic_Programming/487.Max-Consecutive-Ones-II
     * 其實只要記載兩個變數
     * 1. c1 = 尚未用過的反轉 得到的連續1個數
     * 2. c2 = 用過的反轉 得到的連續1個數
     *
     * i from 0 to n go through nums[i]
     * nums[i] == 1: c1++, c2++
     * nums[i] == 0:
     * - c2 = c1 + 1,
     * - c1 = 0;
     * 過程中記載最大的 c2 即可
     *
     * 想想 c1 就是過程中  每一段連續 1 的記載
     * 遇到 1 就是繼續接續前面記載
     * 遇到 0 就是 c2 拿前面 c1 來累計看看, 然後 c1 重置後繼續走
     * */
    public int findMaxConsecutiveOnes(int[] nums) {
        int c1 = 0, c2 = 0, res = 0;
        for (int n : nums) {
            if (n == 1) {
                c1++;
                c2++;
            } else {
                c2 = c1 + 1;
                c1 = 0;
            }
            res = Math.max(c2, res);
        }
        return res;
    }
}
