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
   * 這題的精妙在於
   * 1. 每個元素可重複拿來建構
   * 2. 但建構出來的pattern 只能有一種 ex [2,2,3] == [3,2,2] == [2,3,2]
   * 3. 用一個remain來紀錄目前建構加總 -> 用來判定是否要繼續建構
   * 4. 多用一個start來紀錄目前建構進度 -> 就是用來排除'建構出來的pattern只能有一種' -> 也是處理subset概念
   * ->    前提是int[] candidates有先排序過, 確保是由小到大開始建構
   * ->    每往下一層就是不考慮上一層以前建構過的 ex candidates = [1,2,3],
   * ->      目前建構用到2(start == 1) 就不回頭考慮1, 因為1可用來繼續重複建構的情境在更之前已經用過了
   * ->      但新的當前這一層還是得考慮2, 因為2還可以繼續重複使用建構 -> subset是結果的subset -> 而非過程subset
   * ->      所以 [2,2,3] 與 [3,2,2] 是因為在考慮[3,2,2]時的第一個3就不要回頭看2了 -> candidates 有排序過
   * ->      以3為開頭繼續往後看, 不往前看, 因為跟[3,2,2]等價的[2,2,3]在第一個2就算出來過了
   * ->    這題精妙就在於它有subset的概念在裡面, 所以還要多一個start, 用來紀錄目前subset到第幾個元素, 前面建構過的所有情境以建構過
   * */
  public void backtrack(List<List<Integer>> res, List<Integer> temp, int[] candidates, int remain, int s) {
    if (remain < 0) return;
    else if (remain == 0) res.add(new ArrayList<>(temp));
    else {
      for (int i = s; i < candidates.length && candidates[i] <= remain; i++) {
        temp.add(candidates[i]);
        backtrack(res, temp, candidates, remain - candidates[i], i); // not i + 1 because we can reuse same elements
        temp.remove(temp.size() - 1);
      }
    }
  }
}
