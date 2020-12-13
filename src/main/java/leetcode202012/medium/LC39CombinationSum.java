package leetcode202012.medium;

import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC39CombinationSum extends BasicTemplate {
  public static void main(String[] args) {
    var LC = new LC39CombinationSum();
    var s = LC.combinationSum(new int[]{2, 3, 6, 7}, 7);
  }

  public List<List<Integer>> combinationSum(int[] candidates, int target) {
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
        temp.add(candidates[i]);
        backtrack(res, temp, candidates, remain - candidates[i], i);
        temp.remove(temp.size() - 1);
      }
    }
  }
}
