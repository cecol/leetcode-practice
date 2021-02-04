package leetcode202102.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class LC623AddOneRowToTree extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC623AddOneRowToTree();
        var s = LC.addOneRow(null, 0, 0);
    }

    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if (d == 1) {
            return new TreeNode(v, root, null);
        } else {
            List<TreeNode> d1 = new ArrayList<>();
            d1.add(root);
            int dd = d - 1;
            while (dd > 1) {
                List<TreeNode> dd1 = new ArrayList<>();
                for (TreeNode t : d1) {
                    if (t.left != null) dd1.add(t.left);
                    if (t.right != null) dd1.add(t.right);
                }
                d1 = dd1;
                dd--;
            }
            for (TreeNode t : d1) {
                if (t.left != null) {
                    t.left = new TreeNode(v, t.left, null);
                } else t.left = new TreeNode(v);
                if (t.right != null) {
                    t.right = new TreeNode(v, null, t.right);
                } else t.right = new TreeNode(v);
            }

            return root;
        }
    }
}
