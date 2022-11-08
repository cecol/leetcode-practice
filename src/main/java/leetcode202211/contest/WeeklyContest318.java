package leetcode202211.contest;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.*;

public class WeeklyContest318 extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new WeeklyContest318();
//        LC.totalCost(new int[]{17, 12, 10, 2, 7, 2, 11, 20, 8}, 3, 4);

//        int[] testCase1 = new int[100001]; // [1,2,3 .... ,99998,99999,100000]
//        for (int i = 0; i < 100000; i++) testCase1[i] = i + 1;
//        log.debug("{}", LC.maximumSubarraySum(testCase1, 100000)); // should be 5000050000

        log.debug("{}", LC.minimumTotalDistance(new ArrayList<>(List.of(0, 4, 6)), new int[][]{{2, 2}, {6, 2}}));
    }

    public int[] applyOperations(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                nums[i] *= 2;
                nums[i + 1] = 0;
            }
        }
        int idx = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] != 0) nums[idx++] = nums[i];
        }
        while (idx < n) nums[idx++] = 0;
        return nums;
    }

    public long maximumSubarraySum(int[] nums, int k) {
        int n = nums.length;
        int i = 0;
        int[] c = new int[100001];
        long res = 0;
        long sum = 0;
        for (int j = 0; j < n; j++) {
            c[nums[j]]++;
            sum += nums[j];
            while ((c[nums[j]] > 1 || j - i + 1 > k) & i < j) {
                c[nums[i]]--;
                sum -= nums[i];
                i++;
            }
            if (j - i + 1 == k) {
                res = Math.max(res, sum);
            }
        }
        return res;
    }

    public long totalCost(int[] costs, int k, int candidates) {
        PriorityQueue<Integer> c1 = new PriorityQueue<>();
        PriorityQueue<Integer> c2 = new PriorityQueue<>();
        int ci1 = -1, ci2 = costs.length;
        long res = 0;

        while (k > 0) {
            while (c1.size() < candidates && ci1 + 1 < ci2) c1.offer(costs[++ci1]);
            while (c2.size() < candidates && ci1 + 1 < ci2) c2.offer(costs[--ci2]);
            if (c1.size() > 0 && c2.size() > 0) {
                if (c1.peek() <= c2.peek()) res += c1.poll();
                else res += c2.poll();
            } else if (c1.size() > 0) res += c1.poll();
            else res += c2.poll();
            k--;
        }
        return res;
    }

    /**
     * 沒過, 以為用 greedy 來解, 結果看人家討論是 DP
     * <p>
     * https://leetcode.com/problems/minimum-total-distance-traveled/discuss/2783240/Visualized-solution-(Memoization)-Memory-(Beats-100)-Time(Beats-84)
     * 這邊有圖解說, 為什麼要先 sort robots & factory, 因為 Robot 要被鄰近的 factory 修復
     * <p>
     * lee 說明 討論區有 java 版本, 思路最清晰
     * https://leetcode.com/problems/minimum-total-distance-traveled/discuss/2783305/Python-DP-Solution
     * <p>
     * <p>
     * dp[i][j][k], i = 第i個 robot, j = 第j個工廠, k = j工廠已修了多少robot
     * iRobot, jFactory 有以下遞迴選擇
     * 1. 跳過 jFactory , 用之後的 factory 修 iRobot 的成本
     * 2. 如果當前 jFactory 還有 quota, 用當前 jFactory 往後修 iRobot , 然後剩下的 kFixed+1 繼續往後修 i+1 Robot
     * 的成本
     * 找出上述成本的的 min
     */
    Long[][][] dp;

    public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        Collections.sort(robot);
        Arrays.sort(factory, (x, y) -> (x[0] - y[0]));
        dp = new Long[robot.size() + 1][factory.length + 1][101];
        return fix(robot, factory, 0, 0, 0);
    }

    long fix(List<Integer> robot, int[][] factory, int iRobot, int jFactory, int kFixed) {
        if (iRobot >= robot.size()) return 0; // no more robot to fix, no cost
        if (jFactory >= factory.length) return Long.MAX_VALUE; // no more factory to fix robots, infinite cost

        if (dp[iRobot][jFactory][kFixed] != null) return dp[iRobot][jFactory][kFixed];

        long res1 = fix(robot, factory, iRobot, jFactory + 1, 0);
        long res2 = Long.MAX_VALUE;

        if (factory[jFactory][1] > kFixed) { // jFactory 還有quota
            long val = fix(robot, factory, iRobot + 1, jFactory, kFixed + 1);
            if (val != Long.MAX_VALUE) {
                res2 = Math.min(res2, val + Math.abs(robot.get(iRobot) - factory[jFactory][0]));
            }
        }
        return dp[iRobot][jFactory][kFixed] = Math.min(res1, res2);
    }


}
