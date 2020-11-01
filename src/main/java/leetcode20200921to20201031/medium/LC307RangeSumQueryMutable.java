package leetcode20200921to20201031.medium;

import leetcode20200921to20201031.BasicTemplate;
import leetcode20200921to20201031.medium.algo.NumArraySegmentTree;

public class LC307RangeSumQueryMutable extends BasicTemplate {

  public static void main(String[] args) {
//    var LC = new NumArrayBIT(new int[]{9, -8});
//    LC.update(0, 3);
//    LC.sumRange(1, 1);
//    LC.update(0, 1);
//    LC.update(1, -3);
//    LC.update(0, 1);
    var LC2 = new NumArraySegmentTree(new int[]{1, 3, 5});
    LC2.sumRangeInclusive(0, 2);
    LC2.update(1, 2);
    LC2.sumRangeInclusive(0, 2);
  }
}
