package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class LC525ContiguousArray extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC525ContiguousArray();
        LC.findMaxLength(new int[]{1000, 100, 10, 2});
    }

    /**
     * https://leetcode.com/problems/contiguous-array/discuss/99652/One-passuse-a-HashMap-to-record-0-1-count-difference
     * 沒想到該怎麼解比較好, 看到答案才覺得沒看過不知道
     * 1. 有 presum概念, loop nums from index 0 -> nums.length - 1 to count zero
     * 2. count zero for 0 -> zero++, 1 -> zero-- 代表的是從 0 到 i 目前為止共有 幾個zero & one的 diff
     * zero > 0 means we have more zeros than ones
     * zero < 0 means we have more ones than zeros
     * zero == 0 means we have equal number of ones and zeros -> 當 zero == 0, index: 0 到 i 目前為止都是 balance
     * 如果 i 時候 zero == 1, 若更後面的 j 之累計zero == 1, 代表 i->j 之間 0,1 是 balance (zero 一直保持平衡都是1)
     * -> 所以要用HashMap 記載 [第一次]前面出現過的 zero 是多少的 case -> 讓後面的 j 如果遇到一樣的就可以計數balance
     */
    public int findMaxLength(int[] nums) {
        Map<Integer, Integer> m = new HashMap<>();
        int res = 0;
        int zero = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) zero++;
            else zero--;
            if (zero == 0) res = i + 1;
            if (!m.containsKey(zero)) m.put(zero, i);
            else res = Math.max(res, i - m.get(zero));
        }
        return res;
    }
}
