package leetcode202011.easy;

import leetcode20200921to20201031.BasicTemplate;

public class LC70ClimbingStairs extends BasicTemplate {
  public static void main(String[] args) {
    var LC = new LC70ClimbingStairs();
    var s = LC.climbStairs(5);
  }


  public int climbStairs(int n) {
    int s1 = 1;
    int s2 = 2;
    if(n == 0) return 0;
    if(n == 1) return s1;
    if(n == 2) return s2;
    for(int i = 3;i<=n;i++) {
      int k = s1+s2;
      s1=s2;
      s2=k;
    }
    return s2;
  }
}
