package leetcode202102.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC230KthSmallestElementInABST extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC230KthSmallestElementInABST();
        var s = LC.kthSmallest(null, 0);
    }

    int i = 0;
    int r = 0;

    public int kthSmallest(TreeNode root, int k) {
        inorder(root, k);
        return r;
    }

    public void inorder(TreeNode n, int k) {
        if (n == null) return;
        inorder(n.left, k);
        i++;
        if (i == k) {
            r = n.val;
            return;
        }
        inorder(n.right, k);
    }
}
