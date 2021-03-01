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
   * permutation ->
   * 如果只用 cur list 有含該元素就跳過 -> 有重複元素無法處理
   * 得用個boolean[] 來紀錄當前元素是否用過, 是否算過重複元素
   * (其實boolean[]可以用在distinct case -> 只是不夠精簡)
   *
   * 所以if (used[i] || i > 0 && n[i] == n[i - 1] && !used[i - 1]) continue;
   * 其中
   * -  used[i]代表該元素用過 -> 一定不拿來建構
   * -  i > 0 && n[i] == n[i - 1] && !used[i - 1] 在該層換新元素建構, 而換到的建構元素, 前一次也用過
   * -    但上面前提是int[] nums有拿來排序過, 相同元素都會擺在一起, 如果相同元素沒有擺在相鄰 -> 這個解法就行不通
   * -    !used[i - 1] 比較特別, 怎會是看前一個要沒用過呢?
   * -    主因是前一個最後會被set成 used[i] = false; 這時候的下一個如果重複 (i, i-1) 的關係才是因同一層重複而要拿掉
   * -    因為每一層都是從0開始, 所以要分清楚同層遞迴還是不同層
   * -    所以如果 i > 0 && n[i] == n[i - 1] 成立但是 !used[i - 1]不成立 代表, used[i-1] = true
   * -    此時的i不應該被省略, 因為他是下一層的i, 看到的 i-1是上一層的, 此時 i 要被納入
   * */
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
