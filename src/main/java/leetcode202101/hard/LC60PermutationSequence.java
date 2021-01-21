package leetcode202101.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LC60PermutationSequence extends BasicTemplate {
  public static void main(String[] args) {
    Logger log = LoggerFactory.getLogger("Main");
    var LC = new LC60PermutationSequence();
    var s = LC.getPermutation(3, 1);
  }

  /**
   * https://leetcode.com/problems/permutation-sequence/discuss/22507/%22Explain-like-I'm-five%22-Java-Solution-in-O(n)
   * 這個舉例比較完整, 但程式碼比較雜, 但可以先看過他的解釋有基本概念
   * say n = 4, k = 13, you have {1, 2, 3, 4} -> notUsed = 1,2,3,4
   * k = 13 -> 3開頭的區間是 13-18 -> 3 + (permutations of 1, 2, 4) subset
   * 怎麼得到3 -> (14-1)/fact(3) == 2 -> 剛好notUsed的第2 offset位置就是3
   * 為什麼一開始 k-1? -> 應該是取notUsed是透過 offset, 由0開始 -> 因此k要先減1
   *
   * 這時候 k 會變成 k -= index * factorial -> 13 -= 2*factorial(3) -> k = 1
   *
   * 下一次就是 notUsed list = 1,2,4 (原本1,2,3,4取出offset 2就是3所剩下的) with k = 1, f =2
   * index = k / f = 0 -> 取出1,2,4的1
   *
   * 以此類推
   *
   * 然後 factorial 先計算個 1 to n總值
   * 後面for loop -> i = 0 to n-1 -> factorial /= (n - i); 往後減少後面的 factorial from n-1 to 1
   * factorial 的意義在於 剩下多少可能性 -> k/factorial -> 才可以得到當前區間的offset
   *
   * 覺得特別的地方在於
   * List<Integer> notUsed = new ArrayList<>();
   * 先把要拿來排列的1 to n都放進去
   * 然後不段計算剩下的k, 換算出他是剩下的notUsed落在哪個offset -> 取出來
   * -> 下一圈就是新的k for 剩下的 notUsed
   * 要用offset概念去找出下一個區間值
   *
   * index = k / factorial 的語意是, 是拿notUsed中該offset的區間範圍的 permutation
   * 比如
   * -            index
   * 1 -> "123" -> 0
   * 2 -> "132" -> 0
   * 3 -> "213" -> 1
   * 4 -> "231" -> 1
   * 5 -> "312" -> 2
   * 6 -> "321" -> 3
   * notUsed = 1,2,3, k = 6
   * k-1/f(n-1) -> (6-1)/(3-1) = 2 就是 3 in notUsed(offset)
   * index = 2 代表取到3也代表前面1,2的permutation可能性的刪除掉
   *
   * 所以k要在更新才會達成下面 -> k -= index * factorial; -> 5 - 2*2 = 1 -> k = 1
   * 接下來
   * -           index
   * 1 -> "12" -> 0
   * 2 -> "21" -> 1
   *
   * 原本思考路徑就錯了 想說loop 1 to n 一直找就好了, 被用過就跳過, 這樣很多細節要處理, 沒有來的用 list offset 不斷remove來的簡單
   */
  public String getPermutation(int n, int k) {
    StringBuilder sb = new StringBuilder();
    List<Integer> notUsed = new ArrayList<>();
    int factorial = 1;
    for (int i = 1; i <= n; i++) {
      notUsed.add(i);
      factorial *= i;
    }
    k--;
    for (int i = 0; i < n; i++) {
      factorial /= (n - i);
      int index = (k / factorial);
      sb.append(notUsed.remove(index));
      k -= index * factorial;
    }
    return sb.toString();
  }
}
