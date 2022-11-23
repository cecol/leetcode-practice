package leetcode202102.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.*;

public class LC106ConstructBinaryTreeFromInorderAndPostorderTraversal extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC106ConstructBinaryTreeFromInorderAndPostorderTraversal();
        var s = LC.buildTree(new int[]{9, 3, 15, 20, 7}, new int[]{9, 15, 7, 20, 3});
    }

    /**
     * 一開始概念沒想錯 只是處理sub array的index處理一直沒找到正確法方式
     * 因此參考
     * https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/submissions/
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int n = postorder.length;
        if (n == 0) return null;
        TreeNode rt = new TreeNode(postorder[n - 1]);
        if (n == 1) return rt;
        int idx = 0;
        for (; idx < n; idx++) if (inorder[idx] == postorder[n - 1]) break;

        TreeNode l = null, r = null;
        if (idx > 0) l = buildTree(Arrays.copyOfRange(inorder, 0, idx), Arrays.copyOfRange(postorder, 0, idx));
        if (idx < n - 1) r = buildTree(Arrays.copyOfRange(inorder, idx + 1, n), Arrays.copyOfRange(postorder, idx, n - 1));
        rt.left = l;
        rt.right = r;
        return rt;
    }
}
