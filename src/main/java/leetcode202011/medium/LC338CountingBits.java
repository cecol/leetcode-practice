package leetcode202011.medium;

import leetcode20200921to20201031.BasicTemplate;

public class LC338CountingBits extends BasicTemplate {
  public static void main(String[] args) {
    var LC = new LC338CountingBits();
    var s = LC.countBits(5);
  }

  /**
   * https://blog.csdn.net/fuxuemingzhu/article/details/70806676
   * i has tow cases:
   *  even: i's 1 bits == i/2( ex: (i/2) >> 1 == i )
   *  odd: i's 1 bits == (i-1)'s bits + 1
   *    (i-1) => even => (i-1)'s bits == (i/2)'s bits
   * */
  public int[] countBits(int num) {
    int[] r = new int[num + 1];
    if (num == 0) return r;
    for (int i = 1; i < r.length; i++) {
      r[i] = r[i >> 1] + (i & 1);
    }
    return r;
  }
}
