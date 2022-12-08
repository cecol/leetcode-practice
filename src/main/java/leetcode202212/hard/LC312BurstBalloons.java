package leetcode202212.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.PriorityQueue;

public class LC312BurstBalloons extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC312BurstBalloons();
    }

    /**
     * 這題算是 DP + DFS
     * https://github.com/wisdompeak/LeetCode/tree/master/DFS/312.Burst-Balloons
     * 要算 [left, right] , 必然有最後一槍 k, 打到 nums[k] 之前已經先消除了 [left, k -1] && [k+1, right]
     * 最後得到分數是 left * k * right + [left, k] + [k, right]
     * 要用 DP 來存已算過的結果 -> int[][] memo
     * 1. 先重整 int[] nums, Ex: [1,2,3,4] to [1,1,2,3,4,1], 頭尾加 1 元素
     * 2. 界定邊界 if (left + 1 == right) return 0; 中間沒區間可以爆破 -> return 0
     * 3. 先看看 memo[left][right] 有無結果 有的話回傳
     * 4. i 在 [left/right] 區間選擇
     * */
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n + 2];
        arr[0] = arr[n + 1] = 1;
        for (int i = 1; i <= n; i++) arr[i] = nums[i - 1];
        int[][] memo = new int[n + 2][n + 2];
        return burst(memo, arr, 0, n + 1);
    }

    int burst(int[][] memo, int[] nums, int left, int right) {
        if (left + 1 == right) return 0;
        if (memo[left][right] > 0) return memo[left][right];
        int res = 0;
        for (int i = left + 1; i < right; i++) {
            res = Math.max(res, nums[left] * nums[i] * nums[right] + burst(memo, nums, left, i) + burst(memo, nums, i, right));
        }
        memo[left][right] = res;
        return res;
    }
}
