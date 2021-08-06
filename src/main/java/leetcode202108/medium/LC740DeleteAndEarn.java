package leetcode202108.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import java.util.HashMap;
import java.util.Map;

public class LC740DeleteAndEarn extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC740DeleteAndEarn();
    }

    /**
     * https://leetcode.com/problems/delete-and-earn/discuss/109895/JavaC%2B%2B-Clean-Code-with-Explanation
     * 完全沒想法, 沒想到其實這題也是 house robbery 題型, 也是 take 當前的值的話, 前後都要 skip
     * 只是要多做一層轉換, 不能直接拿 nums 當作 house to rob
     *
     * 題目中的取 num[i], 就是要刪掉 num[i] - 1 & num[i] + 1 -> 就等同於 house robbery 中偷當前房子, 前後房子都不偷
     * 針對拿當前 nums[i] 能賺到的就是 nums[i] 在 nums 中出現幾次, 因為我們知道 nums range: [1, 10000] -> 這個才是 house,
     * 所以要先 for (int i : nums) values[i] += i;
     * -> 把nums[i] 轉換成 1 to 10000 house 所能偷到的價值, 接著就是 house robbery 的邏輯了
     * -> take_i = 前一次skip + 當前 i 的 value
     * -> skip_i = Math.max(前一次skip, 前一次take)
     * 最後 return Math.max(skip, take);
     *
     * 但是如果用 house robbery 來解的話, 前提是 nums[i] range 要合理, 如果是 1 -> 10^100
     * 這樣空間就太大了((可能要其他方式優化
     * 另一種DP
     * https://leetcode.com/problems/delete-and-earn/discuss/284609/For-top-down-DP-fans-Java-2ms-solution-explained
     * 有機會細看
     * */
    public int deleteAndEarn(int[] nums) {
        int n = 10001;
        int[] values = new int[n];
        for (int i : nums) values[i] += i;
        int take = 0, skip = 0;
        for (int i = 0; i < n; i++) {
            int takei = skip + values[i];
            int skipi = Math.max(skip, take);
            take = takei;
            skip = skipi;
        }
        return Math.max(skip, take);
    }
}
