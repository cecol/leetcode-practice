package leetcode202101.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class LC99RecoverBinarySearchTree extends BasicTemplate {
  public static void main(String[] args) {
    Logger log = LoggerFactory.getLogger("Main");
    var LC = new LC99RecoverBinarySearchTree();
    LC.recoverTree(null);
  }

  /**
   * https://leetcode.com/problems/recover-binary-search-tree/discuss/32535/No-Fancy-Algorithm-just-Simple-and-Powerful-In-Order-Traversal
   * 這題我完全錯了,
   * 1 重點是對於中序的了解 -> 我有想到中序, 但沒想過中序過程中紀錄找到的點就好, 不用特地用一個array來記下中序過程
   *  -> 其實這類問就是抽象化中序: 中序(left) -> do some business(找到該換的兩個點就好) -> 中序(right)
   * 2. 找到的兩個點兌換是直接換 TreeNode.val就好 -> 不必真的是換TreeNode的reference
   *  -> 換reference完全超複雜且很容易錯
   *  -> 只要題意沒有要更改tree structure -> 就直接改TreeNode.val就好 -> 直接換TreeNode.val就好
   *  -> 完全不需要動到任何 parent-child reference
   *
   *  重點在於中間的 do business
   *  下面這三行由上到下直接看是不明白的
   *  if(firstEle == null && prevEle.val > n.val) firstEle = prevEle;
   *  if(firstEle != null && prevEle.val > n.val) secondEle = n;
   *  prevEle = n;
   *  分開來看, 先看
   *  prevEle = n; 等於說是記載目前的node成為prevEle
   *  那這個影響就是下一行traverse(n.right);
   *  當進入traverse(n.right); -> 代表n.right的左邊都處理完了 -> 代表prevEle就是當前的node的左邊一格元素
   *  所以當進入中序的business中 -> 可以視為prevEle已經被set好 -> 可以直接檢查first & second
   *  先檢查 first
   *  if(firstEle == null && prevEle.val > n.val) firstEle = prevEle;
   *  當first還沒找到, 但當前node n大於前一個prevELe -> 不符合中序(因為左邊已走完,prevEle應該小於當前node)
   *  -> 所以prev必然是firstEle, 因為prev是錯置的
   *  if(firstEle != null && prevEle.val > n.val) secondEle = n;
   *  當first找到了, 就是找second -> 當前node n小於前一個prevELe -> 不符合中序(因為左邊已走完,prevEle應該小於當前node)
   *  -> 所以當前node 就是找second -> 代表當前node n 是錯置的
   *
   *  所以整個重點在於記載 prev node 來判定
   *  1. 如果大點尚未找到 就出現prev > current node -> prev一定是前面大點要往後移
   *  2. 如果大點找到了 還出現prev > current node -> current node就是後面的小點要往前移
   *  因為是中序從小走到大 -> 所以定是先找到錯置的大點才找到錯置的小點
   * */
  TreeNode firstEle = null;
  TreeNode secondEle = null;
  TreeNode prevEle = new TreeNode(Integer.MIN_VALUE);
  public void recoverTree(TreeNode root) {
    if(root == null) return;
    traverse(root);
    int tmp = firstEle.val;
    firstEle.val = secondEle.val;
    secondEle.val = tmp;
  }

  private void traverse(TreeNode n) {
    if (n == null) return;
    traverse(n.left);
    if(firstEle == null && prevEle.val > n.val) firstEle = prevEle;
    if(firstEle != null && prevEle.val > n.val) secondEle = n;
    prevEle = n;
    traverse(n.right);
  }

}
