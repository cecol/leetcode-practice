package leetcode202012.medium;

import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC90SubsetsII extends BasicTemplate {
  public static void main(String[] args) {
    var LC = new LC90SubsetsII();
    var s = LC.subsetsWithDup(new int[]{1, 2, 2});
  }

  public List<List<Integer>> subsetsWithDup(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();
    Arrays.sort(nums);
    sub(res, new ArrayList<>(), nums, 0);
    log.debug("res:{}", res);
    return res;
  }

  /**
   * 我這邊解到後面才意會到if (i > s && nums[i] == nums[i - 1]) continue;的真正含義
   * 我原本以為是 i > 0 就好
   * 但因為 i 是從 s 開始, i 可能是從1,2,3 開始
   * nums[i] == nums[i - 1] 確實知道目前處理到重複
   * 但重複是指當前迴圈已處理過的 因此第一個 i=s 都要被處理
   * 是 s+1 -> nums.length 中間有重複才要略過,
   * 因此 i>0 是把第一個都省略了, 這時候根本還沒發生重複
   * */
  public void sub(List<List<Integer>> res, List<Integer> tmp, int[] nums, int s) {
    res.add(new ArrayList<>(tmp));
    for (int i = s; i < nums.length; i++) {
      if (i > s && nums[i] == nums[i - 1]) continue;
      tmp.add(nums[i]);
      sub(res, tmp, nums, i + 1);
      tmp.remove(tmp.size() - 1);
    }
  }
}
