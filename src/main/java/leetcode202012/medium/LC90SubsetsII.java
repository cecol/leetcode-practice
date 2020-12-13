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
