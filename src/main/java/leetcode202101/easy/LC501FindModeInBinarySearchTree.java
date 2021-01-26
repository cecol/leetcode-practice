package leetcode202101.easy;

import leetcode20200921to20201031.BasicTemplate;
import util.TreeNode;

public class LC501FindModeInBinarySearchTree extends BasicTemplate {
  public static void main(String[] args) {
    var LC = new LC501FindModeInBinarySearchTree();
    var s = LC.findMode(null);
  }

  /**
   * 這題easy卻花了一堆時間QQ
   * 這題是蠻好解的 -> 用一個 HashMap走過這棵樹就解了
   * 主要是題目後面有要求Space O(1) -> 不能HashMap
   * 後來找了很多答案雖然是聲稱O(1), 但實際上不是
   * 有看到這一篇
   * https://leetcode.com/problems/find-mode-in-binary-search-tree/discuss/98101/Proper-O(1)-space
   * 看起來是先中序走過一遍 -> 中序中間處理root.val就是拿去算目前的count, 如果有大於 maxCount -> 更新 maxCount & modeCount
   * -> 其中看看當前處理的curVal(就是root.val)是否有改變, 有改變就reset count
   * (我覺得概念跟687很像, 只是這邊是root.val跟走過的左子樹不一樣 -> reset, 687是root.val跟左子樹不一樣, 以root來算+left都是0)
   * 先確認有多少modes -> maxCount == curCount 來累加modeCount(但如果下次有踩到curCount > maxCount, 會reset)
   * -> 然後initial int[] modes
   * 在中序走過一遍把 int[] modes填滿
   *
   * 我在想為什麼得走中序(我試跑過前序 -> 失敗)
   * 我想因為中序就是sorted order -> 往後面走都知道前面都是小於等於自己 -> 不會出現大於自己
   * 所以只要拿當前val 去算當前max , modeCount就足矣
   */
  int curVal;
  int curCount = 0;
  int maxCount = 0;
  int modeCount = 0;
  int[] modes = null;

  public int[] findMode(TreeNode root) {
    inorder(root);
    modes = new int[modeCount];
    modeCount =0;
    curCount=0;
    inorder(root);
    return modes;
  }

  private void handleValue(int i) {
    if (i != curVal) {
      curVal = i;
      curCount = 0;
    }
    curCount++;
    if (curCount > maxCount) {
      maxCount = curCount;
      modeCount = 1;
    } else if (maxCount == curCount) {
      if (modes != null) modes[modeCount] = curVal;
      modeCount++;
    }
  }

  private void inorder(TreeNode r) {
    if (r == null) return;
    handleValue(r.val);
    inorder(r.left);
    inorder(r.right);
  }
}
