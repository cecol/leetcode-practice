package leetcode202012.medium;

import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC47PermutationsII extends BasicTemplate {
  public static void main(String[] args) {
    var LC = new LC47PermutationsII();
    var s = LC.permuteUnique(new int[]{1, 1, 2});
  }

  public List<List<Integer>> permuteUnique(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();
    Arrays.sort(nums);
    backtrack(res, new ArrayList<>(), nums, new boolean[nums.length]);
    log.debug("res: {}", res);
    return res;
  }

  public void backtrack(List<List<Integer>> res, List<Integer> t, int[] n, boolean[] used) {
    if (t.size() == n.length) res.add(new ArrayList<>(t));
    else {
      for (int i = 0; i < n.length; i++) {
        if (used[i] || i > 0 && n[i] == n[i - 1] && !used[i - 1]) continue;
        t.add(n[i]);
        used[i] = true;
        backtrack(res, t, n, used);
        t.remove(t.size() - 1);
        used[i] = false;
      }
    }
  }
}
