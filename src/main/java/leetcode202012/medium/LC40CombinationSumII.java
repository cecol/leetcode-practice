package leetcode202012.medium;

import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC40CombinationSumII extends BasicTemplate {
  public static void main(String[] args) {
    var LC = new LC40CombinationSumII();
    var s = LC.combinationSum2(new int[]{2, 3, 6, 7}, 7);
  }

  public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    List<List<Integer>> res = new ArrayList<>();
    Arrays.sort(candidates);
    backtrack(res, new ArrayList<>(), candidates, target, 0);
    return res;
  }

  public void backtrack(List<List<Integer>> res, List<Integer> temp, int[] candidates, int remain, int s) {
    if (remain < 0) return;
    else if (remain == 0) res.add(new ArrayList<>(temp));
    else {
      for (int i = s; i < candidates.length; i++) {
        if (i > s && candidates[i] == candidates[i - 1]) continue; // skip duplicates
        temp.add(candidates[i]);
        backtrack(res, temp, candidates, remain - candidates[i], i + 1);
        temp.remove(temp.size() - 1);
      }
    }
  }
}
