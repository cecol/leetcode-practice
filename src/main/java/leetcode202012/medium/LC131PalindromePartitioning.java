package leetcode202012.medium;

import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.List;

public class LC131PalindromePartitioning extends BasicTemplate {
  public static void main(String[] args) {
    var LC = new LC131PalindromePartitioning();
    var s = LC.partition("aab");
  }

  public List<List<String>> partition(String s) {
    List<List<String>> res = new ArrayList<>();
    backtrack(res, new ArrayList<>(), s, 0);
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
   * 這題是去找出String s的所有 Partition -> partition內元素(substring)都是Palindrome
   * List<String> cur 就是正在遞迴計算過程中的partition的substring組成
   * int start 就是指String s前面字串已經被切到 Palindrome Partition(List<String> cur)
   * 所以只能繼續往後找, 如果找到 start == s.length() 代表帶回一直切到不能再切 ->
   * 之前遞迴以切的都是 Palindrome -> 所以以切出一個Partition 是所有substring都是 Palindrome
   * for (int i = start; i < s.length(); i++) -> 從該層遞迴的start 開始往後找substring
   * if (isP(s, start, i)) -> 當該層遞迴的start 開始往後找到的substring是Palindrome -> 往下遞迴繼續建構
   *
   * 這題是難在我沒想出遞迴終止條件 -> 怎判定現在的 List<String> cur 可以加入 List<List<String>> res
   * 這邊精妙的是用
   * int start來知道下一層要從哪邊開始繼續找 Palindrome substring
   * 也一起當作如果 start == s.length -> 已找完 -> 就代表目前的List<String> cur 可以加入 List<List<String>> res
   * 然後backtrack 結束 -> 回去上一層
   * 其實一般直覺想法應該是看 -> 當前的List<String> cur裡面所包含的substring 串再一起是否剛好等於String s -> 因為都有切完
   * 但這樣太浪費時間, 換個觀點是應該想說 目前剩下哪些要找 Palindrome substring
   * -> 因此 int start 某種程度代表的意思是, 往剩下的去找
   * -> 有subset 的概念的題意都是如此
   * -> permutation 就不是subset概念 -> 就沒有 int start
   *
   * 而且這題的 Palindrome 尋找方式是
   * 給一段string 直接判斷整個是否是 Palindrome
   * 如果是DP題目(647 - Palindromic Substrings) 它是由小 Palindrome substring 找出大的Palindromic substring
   * 不能搞混, 因為兩邊的題目問法不一樣, 所以找 Palindrome 也會不一樣
   * */
  public void backtrack(List<List<String>> res, List<String> cur, String s, int start) {
    if (start == s.length()) res.add(new ArrayList<>(cur));
    else {
      for (int i = start; i < s.length(); i++) {
        if (isP(s, start, i)) {
          cur.add(s.substring(start, i + 1));
          backtrack(res, cur, s, i + 1);
          cur.remove(cur.size() - 1);
        }
      }
    }
  }

  public boolean isP(String s, int b, int e) {
    while (b < e) if (s.charAt(b++) != s.charAt(e--)) return false;
    return true;
  }
}
