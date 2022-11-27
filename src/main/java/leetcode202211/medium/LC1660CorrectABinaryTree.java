package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.*;

public class LC1660CorrectABinaryTree extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");

    }

    /**
     * https://leetcode.com/problems/correct-a-binary-tree/discuss/940769/Java-HashSet.-One-Pass.-Short.
     * 我有解出來 思路也是一樣, 但沒想過正解可以正麼美
     * 基本思路就是找誰有 2個 parent, 然後哪個 parent 跟自己視同 level
     *
     * 但更快的方式是 dfs
     * 1. 先走右子樹
     * 2. 在走左子樹
     * 3. 過程紀錄已看過的 node,
     * 4. 先看看當前 dfs , root.right 是否看過, 因為先走 right 在 left, 所以只有錯誤的 left 會踩到這個
     * 踩到就 return null;
     * 5. dfs 回到上層就是
     * - root.right = correctBinaryTree(root.right);
     * - root.left = correctBinaryTree(root.left);
     * 自然哪一層找到 就會 assign null 到錯誤的 TreeNode
     * */

    Set<TreeNode> visited = new HashSet<>();
    public TreeNode correctBinaryTree(TreeNode root) {
        if (root == null) return null;
        if (root.right != null && visited.contains(root.right)) return null;
        visited.add(root);
        root.right = correctBinaryTree(root.right);
        root.left = correctBinaryTree(root.left);
        return root;
    }
}
