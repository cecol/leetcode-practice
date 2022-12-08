package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Stack;

public class LC698PartitionToKEqualSumSubsets extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC698PartitionToKEqualSumSubsets();
        LC.canPartitionKSubsets(new int[]{10, 1, 10, 9, 6, 1, 9, 5, 9, 10, 7, 8, 5, 2, 10, 8}, 11);
    }

    /**
     * LC473MatchsticksToSquare 的 ｋ 版本
     * LC473MatchsticksToSquare 已知這是 partition problem
     * 而且是 NP Complete, 看起來就直觀的 DFS 下去了
     * 而且可以知道 1 <= k <= nums.length <= 16
     * <p>
     * 要優化除了 LC473MatchsticksToSquare 先把 Arrays.sort(nums)
     * DESC 下去 DFS 每個 bucket[1 to k] 下去加看看
     * 還是會 TLE
     * <p>
     * https://leetcode.com/problems/partition-to-k-equal-sum-subsets/discuss/1772704/Java-Solution-(with-comments)-oror-100-faster-oror-1ms
     * 有個超特別優化: 走過每個 bucket[i] 檢查 if(cur[i]==0) break;\
     * 我是跑了 debug 模式才發現有個巧妙是
     * nums: [1,1,2,5,5,6,7,8,8,9,9,9,10,10,10,10], k = 11
     * 從一開始一直遞迴下去 到最後一層會得到 int[] cur = [10,10,10,10,9,9,9,8,8,7,6] (size = 11)
     * 因為下一層 遞迴 什麼 bucket 都加不了 就回退
     * 在遞迴 退回來會變成 [10,10,10,10,9,9,9,8,8,7,0] 就達成 if (cur[i] == 0) break;
     * 然後就跳出去了
     * 看起來就是, 如果遞迴回來 當前又打回原形, 代表後面 DFS 根本都沒加到, 變回 0, 就代表撐爆了, 後面也不用在檢查了
     * 不知道原創者怎麼觀察出這個 剪枝 優化...
     *
     * 另一個想法是 回退回來 變回 0 代表後面數字都加不了了 -> 因為後續遞迴被 nums[idx] + cur[i] <= t 卡死
     * 所以代表 根本無法把 nums 分配完達成 k buckets 且 <= sum/k
     */
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int n : nums) sum += n;
        if (sum % k != 0) return false;
        Arrays.sort(nums);
        return dfs(nums, new int[k], sum / k, nums.length - 1);
    }

    boolean dfs(int[] nums, int[] cur, int t, int idx) {
        if (idx == -1) {
            for (int i = 0; i < cur.length; i++) if (cur[i] != t) return false;
            return true;
        }
        for (int i = 0; i < cur.length; i++) {
            if (nums[idx] + cur[i] <= t) {
                cur[i] += nums[idx];
                if (dfs(nums, cur, t, idx - 1)) return true;
                cur[i] -= nums[idx];
            }
            if (cur[i] == 0) break;
        }
        return false;
    }
}
