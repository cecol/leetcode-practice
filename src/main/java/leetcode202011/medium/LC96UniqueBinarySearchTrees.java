package leetcode202011.medium;

import leetcode20200921to20201031.BasicTemplate;

public class LC96UniqueBinarySearchTrees extends BasicTemplate {
  public static void main(String[] args) {
    var LC = new LC96UniqueBinarySearchTrees();
//    var s = LC.numTrees(3);
    var s2 = LC.numTrees(6);
  }

  public int numTrees(int n) {
    if (n <= 1) return n;
    int[][] dp = new int[n + 1][n + 1];
    int res = countTree("", dp, 1, n);
    logIntArray(dp);
    log.debug("res: {}", res);
    return res;
  }

  public int countTree(String tab, int[][] dp, int begin, int end) {
    log.debug(tab + "countTree begin: {}, end: {}", begin, end);
    if (begin >= end) return 0;
    if (end >= dp.length) return 0;
    if (dp[begin][end] != 0) return dp[begin][end];
    if (end - begin == 1) {
      dp[begin][end] = 2;
      return dp[begin][end];
    }

    int res = 0;
    for (int root = begin; root <= end; root++) {
      int left = countTree(tab + "\t", dp, begin, root - 1);
      int right = countTree(tab + "\t", dp, root + 1, end);
      if (left == right && left == 0) res++;
      else if (left <= 1 || right <= 1) res += (left + right);
      else res += left * right;
      log.debug(tab + "countTree root: {}, begin: {}, end: {}, left: {}, right: {}, res: {}", root, begin, end, left, right, res);
    }
    dp[begin][end] = res;
    return res;
  }
}
