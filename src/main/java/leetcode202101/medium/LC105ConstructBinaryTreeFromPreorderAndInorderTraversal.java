package leetcode202101.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.Arrays;

public class LC105ConstructBinaryTreeFromPreorderAndInorderTraversal extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC105ConstructBinaryTreeFromPreorderAndInorderTraversal();
        LC.buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {

        if (preorder.length == 1) return new TreeNode(preorder[0]);
        if (preorder.length == 0) return null;
        int ri = 0;
        for (; ri < inorder.length; ri++) if (inorder[ri] == preorder[0]) break;

        TreeNode left = null;
        TreeNode right = null;
        if (ri > 0) {
            left = buildTree(Arrays.copyOfRange(preorder, 1, ri + 1), Arrays.copyOfRange(inorder, 0, ri));
        }
        if (ri < inorder.length - 1) {
            right = buildTree(Arrays.copyOfRange(preorder, ri + 1, preorder.length), Arrays.copyOfRange(inorder, ri + 1, inorder.length));
        }
        return new TreeNode(preorder[0], left, right);
    }
}
