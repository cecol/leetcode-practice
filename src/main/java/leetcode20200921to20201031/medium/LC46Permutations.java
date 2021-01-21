package leetcode20200921to20201031.medium;

import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LC46Permutations extends BasicTemplate {

  public static void main(String[] args) {
    var LC = new LC46Permutations();
    var r = LC.permute(new int[]{1, 2, 3});
    for (List<Integer> i : r) System.out.println(i.stream().map(Object::toString).collect(Collectors.joining(",")));
  }

  public List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> r = new ArrayList<>();
    backtrack(r, new ArrayList<>(), nums);
    return r;
  }

  public void backtrack(List<List<Integer>> res, List<Integer> cur, int[] nums) {
    if (cur.size() == nums.length) {
      res.add(new ArrayList<>(cur));
    } else {
      for (int num : nums) {
        if (cur.contains(num)) continue;
        cur.add(num);
        backtrack(res, cur, nums);
        cur.remove(cur.size() - 1);
      }
    }
  }
}
