package leetcode202101.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC1334FindTheCityWithTheSmallestNumberOfNeighborsAtAThresholdDistance extends BasicTemplate {
  public static void main(String[] args) {
    Logger log = LoggerFactory.getLogger("Main");
    var LC = new LC1334FindTheCityWithTheSmallestNumberOfNeighborsAtAThresholdDistance();
    var s = LC.findTheCity(5, new int[][]{{0, 1, 2}, {0, 4, 8}, {1, 2, 3}, {1, 4, 2}, {2, 3, 1}, {3, 4, 1}}, 2);
  }

  /**
   * 基本用All Pairs Shortest Path來解 -> Floyd's Algorithm (https://www.cs.rochester.edu/u/nelson/courses/csc_173/graphs/apsp.html)
   * 核心概念就是 -> int[][] 紀錄 點 i -> j 的最短距離
   * 但點 i -> j 距離可以透過中間經過 1 - n-1個點來計算
   * 所以多一個k要計算 -> i -> j 是否有因為透過k點而變短 i -> k -> j
   * 所以time complexity = O(N^3)
   * 該準備的是
   * 1. int[][] 紀錄兩點之間目前距離
   * 2. 三層迴圈, 其中兩層是 i -> j, 最外層是k, 代表 i -> k -> j 是否有縮短
   * 原本自己有解開, 但是還是參考人家比較精簡的code(概念一致)
   * https://leetcode.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/discuss/490312/JavaC%2B%2BPython-Easy-Floyd-Algorithm
   */
  public int findTheCity(int n, int[][] edges, int distanceThreshold) {
    int[][] dp = new int[n][n];
    for (int i = 0; i < n; i++) {
      Arrays.fill(dp[i], 10001);
      dp[i][i] = 0;
    }
    for (int[] e : edges) dp[e[0]][e[1]] = dp[e[1]][e[0]] = e[2];
    for (int k = 0; k < n; k++) {
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
          dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
        }
      }
    }
    int smallest = n;
    int res = 0;
    for (int i = 0; i < n; i++) {
      int count = 0;
      for (int j = 0; j < n; j++) if (dp[i][j] <= distanceThreshold) count++;
      if(count <= smallest){
        res = i;
        smallest = count;
      }
    }
    return res;
  }
}
