package leetcode20200921to20201031.medium.algo;

import leetcode20200921to20201031.BasicTemplate;

/**
 * https://codeforces.com/blog/entry/18051
 * https://www.youtube.com/watch?v=Oq2E2yGadnU&ab_channel=JAlgs
 */
public class NumArraySegmentTree extends BasicTemplate {
  private int[] st = null;
  private int size;

  public NumArraySegmentTree(int[] nums) {
    this.size = nums.length;
    // nums are all put into leaf, so we need (nums.length - 1) parent nodes to build segment tree
    // we ignore st[0](useless)
    this.st = new int[size * 2];
    // build tree
    for (int i = 0; i < nums.length; i++) st[i + size] = nums[i]; // build leaf from nums
    for (int i = size - 1; i > 0; i--) // build parent node
      st[i] = // parent node
          st[i * 2] + // left child
              st[i * 2 + 1]; // right child
    log.debug("new st: {}", st);
  }

  public void update(int i, int val) {
    i += size; // go to leaf
    st[i] = val; // update leaf
    i /= 2; // parent node
    for (;
         i > 0; // we ignore st[0](useless)
         i /= 2)  // build parent
    {
      st[i] = // parent node
          st[i * 2] + // left child
              st[i * 2 + 1]; // right child
    }
    log.debug("update st: {}", st);
  }

  public int sumRange(int i, int j) {
    int sum = 0;
    j++;
    i += size; // go to leaf
    j += size; // go to leaf
    for (;
         i < j; // no more range to sum
         i /= 2, j /= 2 // go to parent respectively
    ) {
      log.debug("sum, i: {}, j: {}", i, j);
      // range left is odd -> edge case
      if (i % 2 == 1) {
        sum += st[i];
        i += 1;
      }
      // range right is odd -> edge case
      if (j % 2 == 1) {
        j -= 1;
        sum += st[j];
      }
    }
    log.debug("sum, i: {}, j: {}", i, j);
    log.debug("sum, sum: {}", sum);
    return sum;
  }
}
