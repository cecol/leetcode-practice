package leetcode202101.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class LC94BinaryTreeInorderTraversal extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC94BinaryTreeInorderTraversal();
        var s = LC.inorderTraversal(null);
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        if (root.left != null) res.addAll(inorderTraversal(root.left));
        res.add(root.val);
        if (root.right != null) res.addAll(inorderTraversal(root.right));
        return res;
    }
}
