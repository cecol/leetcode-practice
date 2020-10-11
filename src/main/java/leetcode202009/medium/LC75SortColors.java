package leetcode202009.medium;

import leetcode202009.BasicTemplate;

public class LC75SortColors extends BasicTemplate {
  public static void main(String[] args) {
    var LC = new LC75SortColors();
    LC.sortColors(new int[]{1, 1, 1, 0, 0, 2, 1, 0, 1});
  }

  /**
   * https://leetcode.com/problems/sort-colors/discuss/26549/Java-solution-both-2-pass-and-1-pass
   */
  public void sortColors(int[] nums) {
    int s = 0;
    int e = nums.length - 1;
    int i = 0;
    while (i <= e) {
      if (nums[i] == 0) {
        swap(i, s, nums);
        s++;
      }
      if (nums[i] == 2) {
        swap(i, e, nums);
        e--;
        i--;
      }
      i++;
    }
    log.debug("{}", nums);
  }

  public void swap(int i, int j, int[] n) {
    int t = n[i];
    n[i] = n[j];
    n[j] = t;
  }
}
