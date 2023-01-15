package leetcode202102.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

public class LC450DeleteNodeInABST extends BasicTemplate {
  public static void main(String[] args) {
    Logger log = LoggerFactory.getLogger("Main");
    var LC = new LC450DeleteNodeInABST();
    var s = LC.deleteNode(null, 0);
  }

  /**
   * 這題我真的完全想歪, 直到看了正確答案
   * https://leetcode.com/problems/delete-node-in-a-bst/discuss/93296/Recursive-Easy-to-Understand-Java-Solution
   * 一開始要想對的地方是 要用 deleteNode 遞迴下去
   * root.val > key
   * 代表在左子樹 -> 左子樹下去 deleteNode -> 然後回傳的給現在的root的左邊
   * root.val < key 同上
   * 所以最後一定有遞迴到某一顆子樹root.val == key
   * 1 根本找不到 直接回null -> 一開始的if (root == null) return null;
   * 2 找到了, 當前的root就是要拿來delete
   * -> 1. 如果左子樹是空的 -> 直接拿右子樹來當結果 -> 當前root刪掉後, 根本也不用接分開的子樹(沒有分開的子樹) -> 回傳
   * -> 右子樹空也同理
   * -> 2. 找右子樹的最左(最小點-leaf)來當銜接點, 左子樹接在左邊(因為左子樹皆小於它) -> 回傳root.right(因為左邊已經被揭過去了)
   * -> 這邊概念也可以反過來 用左子樹來當最後結果 那就要找左子樹的最大值來接右子樹
   *
   * 我想錯的點是一直想說要紀錄找到那一個要被刪除的點 然後還要紀錄parent
   * 導致整個程式碼很混亂, 沒想到善用 deleteNode 的遞迴形式
   * 用 deleteNode 的回傳來作為 subtree 下去繼續找的語意
   */
  public TreeNode deleteNode(TreeNode root, int key) {
    if (root == null) return null;
    if (root.val > key) {
      root.left = deleteNode(root.left, key);
    } else if (root.val < key) {
      root.right = deleteNode(root.right, key);
    } else {
      if(root.left == null) return root.right;
      if(root.right == null) return root.left;

      TreeNode rightSmallest = root.right;
      while (rightSmallest.left != null) rightSmallest = rightSmallest.left;
      rightSmallest.left = root.left;
      return root.right;
    }
    return root;
  }
}
