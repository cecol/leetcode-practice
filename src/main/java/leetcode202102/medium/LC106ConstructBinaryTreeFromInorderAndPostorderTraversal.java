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
        var s = LC.buildTree(null, null);
    }

    /**
     * 一開始概念沒想錯 只是處理sub array的index處理一直沒找到正確法方式
     * 因此參考
     * https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/submissions/
     * */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int n = inorder.length;
        Map<Integer, Integer> rootInorderIndex = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) rootInorderIndex.put(inorder[i], i);
        return bt(inorder, postorder, 0, n - 1, 0, n - 1, rootInorderIndex);
    }

    private TreeNode bt(int[] inorder, int[] postorder, int is, int ie, int ps, int pe, Map<Integer, Integer> map) {
        if (ps>pe || is>ie) return null;
        int v = postorder[pe];
        int i = map.get(postorder[pe]);
        TreeNode left = i > is ? bt(inorder, postorder, is, i - 1, ps, ps + i - is - 1, map) : null;
        TreeNode right = i < ie ? bt(inorder, postorder, i + 1, ie, ps + i - is, pe - 1, map) : null;
        TreeNode root = new TreeNode(v, left, right);
        return root;
    }
}
