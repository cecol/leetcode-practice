package leetcode202101.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class LC543DiameterOfBinaryTree extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC543DiameterOfBinaryTree();
        var s = LC.diameterOfBinaryTree(null);
    }

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        int ld = diameterOfBinaryTree(root.left);
        int rd = diameterOfBinaryTree(root.right);
        int lh = maxH(root.left);
        int rh = maxH(root.right);
        int dh = Math.max(ld,rd);
        dh = Math.max(dh, lh+rh);
        return dh;
    }

    private int maxH(TreeNode root) {
        if (root == null) return 0;
        int h = 0;
        h = Math.max(h, maxH(root.left));
        h = Math.max(h, maxH(root.right));
        return h + 1;
    }
}
