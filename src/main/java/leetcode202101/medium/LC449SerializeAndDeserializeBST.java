package leetcode202101.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class LC449SerializeAndDeserializeBST extends BasicTemplate {
  public static void main(String[] args) {
    Logger log = LoggerFactory.getLogger("Main");
    var LC = new LC449SerializeAndDeserializeBST();
  }


  /**
   * https://leetcode.com/problems/serialize-and-deserialize-bst/discuss/177617/the-General-Solution-for-Serialize-and-Deserialize-BST-and-Serialize-and-Deserialize-BT
   * 這題我一直以為要做成inorder&preorder才可以還原
   * 但其實preorder就可以
   * 因為他是BST, 當你拿出preorder[0],
   * 就知道後面的value < preorder[0]都是left child
   * 就知道後面的value > preorder[0]right child
   * 就這樣去分辨就好
   * 只是透過一個queue + lower + upper就可以快速還原
   *
   * 但是如果是建立BT 就要多加一些特殊符號in encoding來知道哪邊是left end
   * */
  // Encodes a tree to a single string.
  public String serialize(TreeNode root) {
    StringBuilder sb = new StringBuilder();
    ser(root, sb);
    return sb.toString();
  }

  private void ser(TreeNode r, StringBuilder sb) {
    if (r == null) return;
    sb.append(r.val).append(",");
    ser(r.left, sb);
    ser(r.right, sb);
  }

  // Decodes your encoded data to tree.
  public TreeNode deserialize(String data) {
    if (data.isEmpty()) return null;
    Queue<String> q = new LinkedList<>(Arrays.asList(data.split(",")));
    return des(q, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  private TreeNode des(Queue<String> q, int lower, int upper) {
    if(q.isEmpty()) return null;
    String s = q.peek();
    int val = Integer.parseInt(s);
    if(val < lower || val > upper) return null;
    q.poll();
    TreeNode r = new TreeNode(val);
    r.left = des(q,lower,val);
    r.right = des(q,val,upper);
    return r;
  }
}
