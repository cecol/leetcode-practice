package leetcode202102.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

public class LC1008ConstructBinarySearchTreeFromPreorderTraversal extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1008ConstructBinarySearchTreeFromPreorderTraversal();
        var s = LC.bstFromPreorder(new int[]{});
    }

    public TreeNode bstFromPreorder(int[] preorder) {
        if (preorder == null || preorder.length == 0) return null;
        return bstFromPreorder(preorder, 0, preorder.length - 1);
    }

    public TreeNode bstFromPreorder(int[] preorder, int s, int e) {
        if (s > e) return null;
        int ss = s + 1;
        for (; ss <= e && preorder[ss] < preorder[s]; ss++) ;
        return new TreeNode(
                preorder[s],
                bstFromPreorder(preorder, s + 1, ss - 1),
                bstFromPreorder(preorder, ss, e)
        );
    }
}
