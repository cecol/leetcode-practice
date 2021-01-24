package leetcode202101.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

public class LC617MergeTwoBinaryTrees extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC617MergeTwoBinaryTrees();
        var s = LC.mergeTrees(null, null);
    }

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        TreeNode n = null;
        if (t1 == null && t2 != null) {
            n = new TreeNode(t2.val, mergeTrees(null, t2.left), mergeTrees(null, t2.right));
        }
        if (t1 != null && t2 == null) {
            n = new TreeNode(t1.val, mergeTrees(t1.left, null), mergeTrees(t1.right, null));
        }
        if (t1 != null && t2 != null) {
            n = new TreeNode(t1.val + t2.val, mergeTrees(t1.left, t2.left), mergeTrees(t1.right, t2.right));
        }
        return n;
    }
}
