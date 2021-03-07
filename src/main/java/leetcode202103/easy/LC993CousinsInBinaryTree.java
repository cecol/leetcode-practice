package leetcode202103.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class LC993CousinsInBinaryTree extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC993CousinsInBinaryTree();
    }

    /**
     * https://leetcode.com/problems/cousins-in-binary-tree/discuss/240081/Java-easy-to-understand-and-clean-solution
     * 我都以為Tree 的 easy應該有非常短且快速解, 如果我直覺想法太複雜都會覺得我想錯了
     * 但這題看起來就是 直接下去找, 配合global vaiable
     */
    TreeNode xp = null;
    TreeNode yp = null;
    int xh = -1, yh = -1;

    public boolean isCousins(TreeNode root, int x, int y) {
        getBoth(root, x, y, 0, null);
        return xh == yh && xp != yp;
    }

    private void getBoth(TreeNode root, int x, int y, int d, TreeNode p) {
        if (root == null) return;
        if (root.val == x) {
            xp = p;
            xh = d;
        } else if (root.val == y) {
            yp = p;
            yh = d;
        }
        if (xp == null || yp == null) {
            getBoth(root.left, x, y, d + 1, root);
            getBoth(root.right, x, y, d + 1, root);
        }
    }
}
