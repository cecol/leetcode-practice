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
   * ->    tmp = p.left;
   * ->      因為目前current是有左樹, 而current左樹的max node, 會在current左樹的最右leave,
   * ->      current left的最右max node leave的right == null, 就是引線二元樹要指引下一個node, 就是目前的current,
   * ->      但我們還不能更改current pinter去找 previous node(左樹的最右最大node)
   * ->      因此用一個tmp node來開始找這個左樹的最右最大node
   * ->    tmp node = current.left 是有兩個作用
   * ->    1. 一開始拿來建立引線, 所以後續的 while (tmp.right != null && tmp.right != current) tmp = tmp.right;
   * ->       要的是 tmp.right == null case && if(tmp.right == null)
   * ->       這過程中 current 從一開始的root不斷往他的left child走, 而tmp最右leave.right 會指向 parent
   * ->       一直重複直到 current真正走到最左最小值 下一圈就會卡到 if currentPointer.left == null
   * ->    2. 是當 current 從root真的變成了中序的最一開始node, 所有最右leave.right 都已經指向 parent
   * ->       要的是 tmp.right != current case && if(tmp.right != null)
   * ->       這過程中current一直都是向前印的, 然後一直往current.right走就好
   * ->       然後最右leave.right 引線都邊還原成null, 因此tmp.right = null(原本tmp.right = current)
   * ->    所以重點是  while (tmp.right != null && tmp.right != current) 代表兩個含義
   * ->    1.  tmp.right != null 還在建立引線
   * ->    2.  tmp.right != current, 引線建立完, 邊走的過程中印出並把引線拆掉
   * ->    while (tmp.right != null && tmp.right != current) tmp = tmp.right;
   * ->      承上面, 走到該left的最右right -> 因為該right要指向當前的 current
   * ->      因為tmp最右right就是當前的current的前一個(tmp == current.left)
   * ->    tmp.right != current -> 代表之前建立過的right null 指向parent會被跳過, 因為有機會重複檢查到已走過的路徑
   * ->        也可以視為: tmp.right是真的有其右兒子(所以當然不能亂改R, 也不能改為current) -> 不是原本null right被指向next parent current
   * ->    if(tmp.right == null) 指向當前 current, 還沒要印, 因為最小的還沒走到, 繼續往最左current = current.left -> 離開進入迴圈繼續往下找出最左
   * ->       這一塊只有在一開始 current = root, 要把current移到中序最一開始有用, 在這個移動過程中把leave right node引線建立起來
   * ->    if(tmp.right != null) -> 代表走到最左,引線也已經都建立完成, 所有right 都有值1. 要馬是原本自己的right child, 2.要馬是引線parent
   * ->       然後current 指向下一個 current = current.right;
   * ->       tmp.right = null; 是把原本的引線right還原成null,
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
