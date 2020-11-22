package leetcode202011.medium;

import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC673NumberOfLongestIncreasingSubsequence extends BasicTemplate {
  public static void main(String[] args) {
    var LC = new LC673NumberOfLongestIncreasingSubsequence();
//    var s1 = LC.findNumberOfLIS(new int[]{1, 3, 5, 4, 7});
//    var s2 = LC.findNumberOfLIS(new int[]{2, 2, 2, 2, 2});
//    var s3 = LC.findNumberOfLIS(new int[]{1, 2});
    //var s4 = LC.findNumberOfLIS(new int[]{1, 3, 2});
    var s5 = LC.findNumberOfLIS(new int[]{1, 2, 4, 3, 5, 4, 7, 2});
  }

  /**
   * https://www.acwing.com/file_system/file/content/whole/index/content/1086897/
   */
  public int findNumberOfLIS(int[] nums) {
    int[] dpLIS = new int[nums.length];
    int[] dpLISCount = new int[nums.length];
    Arrays.fill(dpLIS, 1);
    Arrays.fill(dpLISCount, 1);
    int maxLength = 1;
    for (int i = 0; i < nums.length; i++)
      for (int j = 0; j < i; j++) {
        if (nums[i] <= nums[j]) continue;
        //找到更長
        if (dpLIS[j] + 1 > dpLIS[i]) {
          dpLIS[i] = dpLIS[j] + 1;
          dpLISCount[i] = dpLISCount[j];
        } else if (dpLIS[j] + 1 == dpLIS[i]) { //找到一樣長, 這樣dpLISCount[i] 就要多補上以j結尾的次數:dpLISCount[j]
          dpLISCount[i] += dpLISCount[j];
        }
        maxLength = Math.max(maxLength, dpLIS[i]);
      }
    int all = 0;
    for (int i = 0; i < nums.length; i++) {
      if (dpLIS[i] == maxLength) {
        all += dpLISCount[i];
      }
    }
    return all;
  }
}
