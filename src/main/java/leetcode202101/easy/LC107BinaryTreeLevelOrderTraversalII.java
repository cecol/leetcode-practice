package leetcode202101.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class LC107BinaryTreeLevelOrderTraversalII extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC107BinaryTreeLevelOrderTraversalII();
        var s = LC.levelOrderBottom(null);
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        List<TreeNode> lr = new ArrayList<>();
        lr.add(root);
        readLevel(res, lr);
        return res;
    }

    private void readLevel(List<List<Integer>> res, List<TreeNode> cl) {
        if (cl.isEmpty()) return;
        List<TreeNode> nr = new ArrayList<>();
        List<Integer> ll = new ArrayList<>();
        for (TreeNode l : cl) {
            ll.add(l.val);
            if (l.left != null) nr.add(l.left);
            if (l.right != null) nr.add(l.right);
        }
        readLevel(res, nr);
        res.add(ll);
    }
}
