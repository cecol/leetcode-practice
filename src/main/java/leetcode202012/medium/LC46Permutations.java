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

  /**
   * https://leetcode.com/problems/permutations/discuss/18239/a-general-approach-to-backtracking-questions-in-java-subsets-permutations-combination-sum-palindrome-partioning
   * backtracking 的重點是
   * 把元素加進去 -> 遞迴 -> 遞迴回來 -> 刪掉最後一個元素
   * 其中
   * '把元素加進去'的語意是 -> 以當前這一層開始加入新的元素繼續建構
   * '遞迴'的語意是 -> 目前這一層(剛剛的'把元素加進去' -> t.add(i))為基礎, 再往後建構
   * '遞迴回來'的語意是 -> 下一層遞迴的建構已在該層被清空(因為已經回來了, 所以目前的 cur list最後一個元素剛好是'當前這一層'遞迴前加入的元素)
   * ->   (因為下一層結束前也都會清掉它該層的元素)
   * ->   回到目前這一層, 把這個建構過的元素刪掉, 才可以換下一個可以建構的元素
   *
   * 中間的重點就是, 你要怎麼判別目前該層這個元素是否要加入建構?
   * permutation -> cur list 有含該元素就跳過
   * 所以用cur list 來記憶當前已包含過的元素, 如果沒有包含 -> 加入建構 -> 遞迴
   * 但上面的前提是: 要permutation的所有元素都是distinct -> 沒有重複
   * 如果有重複, 單單依靠cur list 來記憶是不足的
   * */
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
