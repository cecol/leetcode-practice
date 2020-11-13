package leetcode202011.medium;

import leetcode20200921to20201031.BasicTemplate;

public class LC931MinimumFallingPathSum extends BasicTemplate {
  public static void main(String[] args) {
    var LC = new LC931MinimumFallingPathSum();
    var s = LC.minFallingPathSum(new int[][]{});
  }

  public int minFallingPathSum(int[][] A) {
    for(int i = 1;i<A.length;i++)
      for(int j=0;j<A[i].length;j++) {
        int m1 = A[i-1][j];
        if(j>0) m1 = Math.min(m1,A[i-1][j-1]);
        if(j<A[i].length-1) m1 = Math.min(m1,A[i-1][j+1]);
        A[i][j] = A[i][j] + m1;
      }
    int m = A[A.length-1][0];
    for(int i=1;i<A[A.length-1].length;i++) if(A[A.length-1][i] < m) m = A[A.length-1][i];
    return m;
  }
}
