package leetcode20200921to20201031.medium;

import leetcode20200921to20201031.BasicTemplate;

import java.util.*;
import java.util.stream.Collectors;

public class LC78Subsets extends BasicTemplate {
  public static void main(String[] args) {
    var lc = new LC78Subsets();
    var r = lc.subsetsBacktracking(new int[]{1, 2, 3});
  }

  public List<List<Integer>> subsetsBacktracking(int[] nums) {
    List<List<Integer>> r = new ArrayList<>();
    backtracking(r, new ArrayList<>(), nums, 0, "");
    log.debug("{}", r);
    return r;
  }

  /**
   * https://leetcode.com/problems/permutations/discuss/18239/a-general-approach-to-backtracking-questions-in-java-subsets-permutations-combination-sum-palindrome-partioning
   * backtracking 的重點是
   * 把元素加進去 -> 遞迴 -> 遞迴回來 -> 刪掉最後一個元素
   * 其中
   * '把元素加進去'的語意是 -> 以當前這一層開始加入新的元素繼續建構
   * '遞迴'的語意是 -> 目前這一層(剛剛的'把元素加進去' -> t.add(i))為基礎, 再往後建構
   * '遞迴回來'的語意是 -> 下一層遞迴的建構已在該層被清空(因為已經回來了, 所以目前的 cur list最後一個元素剛好是'當前這一層'遞迴前加入的元素)
   * ->   (因為下一層結束前也都會清掉它該層的元素, 遞迴寫法讓每一層做完自己都會清掉自己)
   * ->   回到目前這一層, 把這個建構過的元素刪掉(遞迴寫法讓每一層做完自己都會清掉自己), 才可以換下一個可以建構的元素
   *
   * 中間的重點就是, 你要怎麼判別目前該層這個元素是否要加入建構?
   * 這題的精妙在於
   * 1. subset建構 -> 已加入過的元素不可再加入
   * 2. 所以要多一個int start來處理subset概念, 表明該層是尚未計算過的subset元素
   * */
  private void backtracking(List<List<Integer>> r, List<Integer> cur, int[] nums, int start, String nest) {
    r.add(new ArrayList<>(cur));
    for (int i = start; i < nums.length; i++) {
      cur.add(nums[i]);
      backtracking(r, cur, nums, i + 1, nest + "\t");
      cur.remove(cur.size() - 1);
    }
  }

  public static List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> r = new ArrayList<>();
    List<Integer> x = new ArrayList<>();
    for (int i : nums) x.add(i);
    getSet(x, new HashSet<Integer>(), r);
    return r;
  }

  public static void getSet(List<Integer> x, Set<Integer> a, List<List<Integer>> r) {
    if (x.isEmpty()) {
      r.add(new ArrayList<>(a));
    } else {
      Integer g = x.remove(0);
      getSet(new ArrayList<>(x), new HashSet<>(a), r);
      a.add(g);
      getSet(new ArrayList<>(x), new HashSet<>(a), r);
    }
  }

  public static List<List<Integer>> subsets2(int[] nums) {
    List<List<Integer>> r = new ArrayList<>();
    List<Integer> x = new ArrayList<>();
    r.add(new ArrayList<>());
    getSet2(nums, 0, x, r);
    return r;
  }

  /**
   * recursive case
   */
  public static void getSet2(int[] nums, int offset, List<Integer> x, List<List<Integer>> r) {
    if (offset == nums.length) {
      return;
    } else {
      x.add(nums[offset]);
      r.add(new ArrayList<>(x));
      getSet2(nums, offset + 1, x, r);
      x.remove(x.size() - 1);
      getSet2(nums, offset + 1, x, r);
    }
  }

  /**
   * iteration case
   */
  public static List<List<Integer>> subsets3(int[] nums) {
    List<List<Integer>> r = new ArrayList<>();
    r.add(new ArrayList<>());
    for (int i : nums) {
      List<List<Integer>> n = new ArrayList<>();
      for (List<Integer> l : r) {
        List<Integer> cp = new ArrayList<>(l);
        cp.add(i);
        n.add(cp);
      }
      r.addAll(n);
    }
    return r;
  }
}
