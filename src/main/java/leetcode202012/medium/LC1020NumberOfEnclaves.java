package leetcode202012.medium;

import leetcode20200921to20201031.BasicTemplate;

public class LC1020NumberOfEnclaves extends BasicTemplate {
  public static void main(String[] args) {
    var LC = new LC1020NumberOfEnclaves();
    LC.numEnclaves(new int[][]{
        {0, 0, 0, 0},
        {1, 0, 1, 0},
        {0, 1, 1, 0},
        {0, 0, 0, 0}
    });
    LC.numEnclaves(new int[][]{
        {0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0},
        {1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1},
        {1, 1, 1, 0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 1, 1},
        {1, 1, 0, 1, 0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 0},
        {1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1},
        {1, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0},
        {0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0},
        {0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0},
        {1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1},
        {1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 0, 1, 0}
    });
  }

  public int numEnclaves(int[][] A) {
    if (A.length < 2 || A[0].length < 2) return 0;
    int m = A.length;
    int n = A[0].length;
    for (int i = 0; i < m; i++) {
      if (A[i][0] == 1) dfs(A, i, 0);
      if (A[i][n - 1] == 1) dfs(A, i, n - 1);
    }
    for (int j = 0; j < n; j++) {
      if (A[0][j] == 1) dfs(A, 0, j);
      if (A[m - 1][j] == 1) dfs(A, m - 1, j);
    }
    int res = 0;
    for (int i = 0; i < m; i++)
      for (int j = 0; j < n; j++)
        if (A[i][j] == 1) res++;
    log.debug("result A:");
    logIntArray(A);
    log.debug("{}", res);
    return res;
  }

  public void dfs(int[][] a, int i, int j) {
    int n = a[0].length;
    int m = a.length;
    if (i < 0 || j < 0 || i >= m || j >= n) return;
    a[i][j] = 2;
    if (i > 1 && a[i - 1][j] == 1) dfs(a, i - 1, j);
    if (i < m - 1 && a[i + 1][j] == 1) dfs(a, i + 1, j);
    if (j > 1 && a[i][j - 1] == 1) dfs(a, i, j - 1);
    if (j < n - 1 && a[i][j + 1] == 1) dfs(a, i, j + 1);
  }
}
