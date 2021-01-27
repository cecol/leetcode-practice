package leetcode202101.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

public class LC173BinarySearchTreeIterator extends BasicTemplate {
  public static void main(String[] args) {
    Logger log = LoggerFactory.getLogger("Main");
    var LC = new LC173BinarySearchTreeIterator(null);
  }


  TreeNode current = null;

  /**
   * 這題可以用很基本的中序(2原搜尋樹的中序剛好就是sorted)list -> list就是sorted
   * 但這樣空間複雜度就是O(m)
   * 關鍵在於用Morris Traversal
   * https://leetcode.com/problems/binary-search-tree-iterator/discuss/52705/Morris-traverse-solution
   * Morris traversal:
   * 基本概念就是不用到額外空間來inorder走過tree
   * 因此就是利用tree leave的null child 來指向下一個inorder的node
   * 主要就是利用 node.right == null, node.right去指到parent node -> parent.val > node.val -> parent.val也正好是inorder的node.val的下一個
   * 因為是中序-> 基本概念就是先判斷 TreeNode currentPointer 指標是否 currentPointer.left is null
   * 所以到最後所有的null right都是指向inorder的下一個結果(比自己大的前一個parent)
   * 1. if currentPointer.left == null -> 中序沒有左邊 -> 所以currentPointer就是目前中序要直接印出來的(right都比他大)
   * ->   currentPointer = currentPointer.right
   * 2. if currentPointer.left != null -> 中序有左邊 -> 所以得先印currentPointer.left, 因為left都比他小
   * ->    tmp = p.left; -> 左樹開始走, 並找出null right要指向現在的current point
   * ->    while (tmp.right != null && tmp.right != p) tmp = tmp.right; -> 走到該left的最右right -> 因為該right要指向當前的 current
   * ->    因為該最右right就是當前的current的前一個
   * ->    tmp.right != p -> 代表之前建立過的right null 指向parent會被跳過, 因為有機會重複檢查到已走過的路徑
   * ->        也可以視為: tmp.right是真的有其右兒子(所以當然不能亂改R, 也不能改為parent) -> 不是原本null right被指向next parent current
   * ->    if(tmp.right == null) 指向當前 current, 還沒要印, 因為最小的還沒走到, 繼續往最左current = current.left -> 離開進入迴圈繼續往下找出最左
   * ->    if(tmp.right != null) -> 代表走到最左, 且該right也在之前回圈被走過指向inorder的下一個, 所以可以印了
   * ->       然後current 指向下一個 current = current.right;
   * */
  public LC173BinarySearchTreeIterator(TreeNode root) {
    current = root;
  }

  public int next() {
    TreeNode tmp = null;
    int ret = 0;
    while (current != null) {
      if (current.left == null) {
        ret = current.val;
        current = current.right;
        break;
      }
      else {
        tmp = current.left;
        while (tmp.right != null && tmp.right != current) tmp = tmp.right;
        if(tmp.right == null) {
          tmp.right = current;
          current = current.left;
        } else {
          ret = current.val;
          tmp.right = null;
          current = current.right;
          break;
        }
      }
    }
    return ret;
  }

  public boolean hasNext() {
    return current != null;
  }
}
