package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC368LargestDivisibleSubset extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC368LargestDivisibleSubset();
    }

    /**
     * https://leetcode.com/problems/largest-divisible-subset/solutions/83999/easy-understood-java-dp-solution-in-28ms-with-o-n-2-time/
     * 這題其實不難, 要先觀察出他是 O(N^2) 的 DP
     * 1. 先 sort array, 因為次序不重要, 誰除誰只要整除就好, 所以由小到大可以 一直往前檢查前面的 整除 case
     * 2. O(N^2), 是因為當前 i, 往前找 j, 就算 j 整除了, 可能更前面還可以整除且組出更長的 case, 所以得走到底才知道
     * 3. 比較麻煩是 回頭組出結果
     * 這種組出結果要觀察出來的關聯性是
     * - 1. 先記載最長的 dp mxIdx,
     * - 2. 這個 mxIdx to 0 路上就有可以跟他組出來的 i, 但過程中得記載
     * - 3. 因為每找到一個 nums[mxIdx] % nums[i] == 0 還得要剛好 dp[i] == dp[mxIdx] - res.size()
     * -    因為最終的 dp[mxIdx] 是前面慢慢租出來的, 所以增加是有次序關係, 所以目前找到多少, 就要驗證下一個剛好是剩下的 未找到size
     * -    而且不能 單用 nums[mxIdx] % nums[i] == 0, 因為可能會有重複 case, 所以只能用剛好前一個 temp % nums[i]
     * */
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int mxIdx = 0, mx = 0;
        for (int i = 1; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] % nums[j] == 0) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            if (dp[i] > mx) {
                mx = dp[i];
                mxIdx = i;
            }
        }

        List<Integer> res = new ArrayList<>();
        res.add(nums[mxIdx]);
        int temp = nums[mxIdx];
        for (int i = mxIdx - 1; i >= 0; i--) {
            if (temp % nums[i] == 0 && dp[i] == dp[mxIdx] - res.size()) {
                temp = nums[i];
                res.add(nums[i]);
            }
        }
        Collections.reverse(res);
        return res;
    }
}
