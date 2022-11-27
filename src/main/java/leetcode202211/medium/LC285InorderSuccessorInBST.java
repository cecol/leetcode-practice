package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.Arrays;

public class LC285InorderSuccessorInBST extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");

    }

    /**
     * https://leetcode.com/problems/inorder-successor-in-bst/discuss/72662/*Java*-5ms-short-code-with-explanations
     * 我是有解出來, 時間複雜度也差不多 O(N)
     * 只是沒這麼簡潔
     * 其實只要從 root 跟 p 比較就知道要往前找還是往後找, 過程中
     * 要善用 BST 特點!!
     * root.val > p -> root = root.left
     * root.val < p -> root = root.right
     * 然後記載當前 root 就好
     *
     * 不用特地 inorder 下去, 記載 pre, succ 兩個 global pointer 這麼複雜
     * */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode res = null;
        while (root != null) {
            if(root.val > p.val) {
                res = root;
                root = root.left;
            } else root = root.right;
        }
        return root;
    }

    /**
     * 自己想出來的醜解法
     * */
    TreeNode pre = null, succ = null;

    public TreeNode inorderSuccessor2(TreeNode root, TreeNode p) {
        if (root == null) return null;
        in(root, p);
        return succ;
    }

    void in(TreeNode rt, TreeNode p) {
        if (rt == null) return;
        in(rt.left, p);
        if (pre == null && rt == p) pre = rt;
        else if (pre != null && succ == null) {
            succ = rt;
            return;
        } else if (pre != null) return;
        in(rt.right, p);
    }
}
