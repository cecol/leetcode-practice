package leetcode202011.medium;

import leetcode20200921to20201031.BasicTemplate;

public class LC801MinimumSwapsToMakeSequencesIncreasing extends BasicTemplate {
  public static void main(String[] args) {
    var LC = new LC801MinimumSwapsToMakeSequencesIncreasing();
    var s = LC.minSwap(
        new int[]{1, 3, 5, 4},
        new int[]{1, 2, 3, 7});
    var s2 = LC.minSwap(
        new int[]{3, 3, 8, 9, 10},
        new int[]{1, 7, 4, 6, 8});
  }


  /**
   * 這題有比較好的解釋
   * https://leetcode.com/problems/minimum-swaps-to-make-sequences-increasing/discuss/119879/C%2B%2BJavaPython-DP-O(N)-Solution
   * 基本的DP思考邏輯是:
   * A[i]的嚴格遞增, 是由A[i-1]嚴格遞增組出來的
   * B[i]的嚴格遞增, 是由B[i-1]嚴格遞增組出來的
   * 但是這題有思考盲點, 就是說當遇到A or B不是嚴格遞增, 一定可以透過換 A[i], B[i]可以達成嚴格遞增
   * 但A[i]只能跟B[i]交換,  不能A[i]跟B的任意其他非i的位置交換
   * 所以其實A的組成跟B的組成是有限制的才可以符合這題
   * 所以不太可能出現
   * A: [10, 8]
   * B: [7, 5]
   * 這裡A,B不管怎換(換index 0, 1)都不會達成嚴格遞增
   * 所以題目給的A,B組成, 一定是 i or i-1 擇一換or不換就可以達成嚴格遞增
   * 所以最佳解中有個邏輯:
   * 先檢查
   * 1. `A[i-1] < A[i] && B[i-1] < B[i]` => A[i],B[i] 要嘛本來就符合嚴格遞增
   * 但如果A[i] or B[i]有誰不是嚴格遞增呢?
   * 一定會落入下面的情境
   * 2. `A[i-1] < B[i] && B[i-1] < A[i]`
   * => 因為如果沒有符合這個條件, 代表一定是出現A[i],B[i]怎麼換都不會嚴格遞增
   * => 但是題目保證可以換出嚴格遞增, 所以反推一定會落入這個case
   * 我以前就是卡在我不懂若是A[i] or B[i]有誰不是嚴格遞增呢, 怎保證會符合`A[i-1] < B[i] && B[i-1] < A[i]`上面的案例
   * 若是不符合, 這題根本無解
   * 總結
   * 就是指出
   * A[i] 與 A[i-1]
   * B[i] 與 B[i-1]
   * 只有以下兩種關係
   * 1. 本來就嚴格遞增 `A[i-1] < A[i] && B[i-1] < B[i]` => 要換就i與i-1都換, 要嘛都不換, 才可以繼續保持嚴格遞增
   *  a. 但還可能符合 `A[i-1] < B[i] && B[i-1] < A[i]`來多做優化
   * 2. 沒有嚴格遞增, 但必然符合 `A[i-1] < B[i] && B[i-1] < A[i]` (這樣才可以透過交換i-th or (i-1)th 來達成嚴格遞增)
   * 其中case 2可能包含 case 1, 這2者並沒有完全互斥, 所以得有個設定初始條件 keep[i] = swap[i] = A.length;
   * 如果有1&2 => 因為先確認 if 1 才再確認 if 2 => 所以2要 Math.min(2 case, 1 case)
   * 如果1, 2不是兩個獨立if做出來的, 應該不用 keep[i] = swap[i] = A.length;
   */
  public int minSwap(int[] A, int[] B) {
    if (A == null || A.length == 0 | A.length == 1) return 0;
    int[] keep = new int[A.length];
    int[] swap = new int[A.length];
    swap[0] = 1;
    for (int i = 1; i < A.length; i++) {
      keep[i] = swap[i] = A.length;
      if (A[i] > A[i - 1] && B[i] > B[i - 1]) {
        keep[i] = keep[i - 1];
        swap[i] = swap[i - 1] + 1;
      }
      if (A[i] > B[i - 1] && B[i] > A[i - 1]) {
        keep[i] = Math.min(keep[i], swap[i - 1]);
        swap[i] = Math.min(swap[i], keep[i - 1] + 1);
      }
    }
    return Math.min(keep[A.length - 1], swap[A.length - 1]);
  }

}
