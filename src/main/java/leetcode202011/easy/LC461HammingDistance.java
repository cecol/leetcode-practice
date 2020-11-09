package leetcode202011.easy;

import leetcode20200921to20201031.BasicTemplate;

public class LC461HammingDistance extends BasicTemplate {
  public static void main(String[] args) {
    var LC = new LC461HammingDistance();
    var s = LC.hammingDistance(1, 2);
  }


  public int hammingDistance(int x, int y) {
    return Integer.bitCount(x ^ y);
  }
}
