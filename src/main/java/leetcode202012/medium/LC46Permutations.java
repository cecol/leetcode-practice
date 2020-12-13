package leetcode202012.medium;

import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC46Permutations extends BasicTemplate {
  public static void main(String[] args) {
    var LC = new LC46Permutations();
    var s = LC.permute(new int[]{1, 2, 3});
  }

  public List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();
    backtrack(res, new ArrayList<>(), nums);
    log.debug("res: {}", res);
    return res;
  }

  public void backtrack(List<List<Integer>> res, List<Integer> t, int[] n) {
    if (t.size() == n.length) res.add(new ArrayList<>(t));
    else {
      for (int i : n) {
        if (t.contains(i)) continue;
        t.add(i);
        backtrack(res, t, n);
        t.remove(t.size() - 1);
      }
    }
  }
}
