package leetcode202011.easy;

import leetcode20200921to20201031.BasicTemplate;

public class LC746MinCostClimbingStairs extends BasicTemplate {
  public static void main(String[] args) {
    var LC = new LC746MinCostClimbingStairs();
    var s = LC.minCostClimbingStairs(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1});
  }


  public int minCostClimbingStairs(int[] cost) {
    int[] costSum = new int[cost.length + 1];
    for (int i = 2; i < costSum.length; i++) {
      costSum[i] = Math.min(costSum[i - 1] + cost[i - 1], costSum[i - 2] + cost[i - 2]);
    }
    log.debug("costSum: {}", costSum);
    return costSum[cost.length];
  }
}
