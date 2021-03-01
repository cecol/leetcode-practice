package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LC1248CountNumberOfNiceSubarrays extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1248CountNumberOfNiceSubarrays();
        LC.numberOfSubarrays(new int[]{1, 1, 2, 1, 1}, 3);
    }

    /**
     * atMost 經典題
     * 計數有多少個subarray(sliding window) 符合條件, 因為sliding window算法都是由小長到大, 那就乾脆
     * 每長大一次就計數一次, 這樣就包含到所有subarray(sliding window) case
     * 只有當sliding window條件不符合, 再來縮減window
     * 這樣的過程就把所有sub sub array都算到了
     * 只是最後要把多算得更小可能都去除掉
     * */
    public int numberOfSubarrays(int[] nums, int k) {
        return atMost(nums, k) - atMost(nums, k - 1);
    }

    private int atMost(int[] n, int k) {
        if (k < 0) return 0;
        int i = 0, c = 0, res = 0;
        for (int j = 0; j < n.length; j++) {
            c += n[j] % 2;
            while (c > k) {
                c -= n[i++] % 2;
            }
            res += j - i + 1;
        }
        return res;
    }
}
