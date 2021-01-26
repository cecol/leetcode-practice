package leetcode202101.easy;

import leetcode20200921to20201031.BasicTemplate;
import util.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class LC637AverageOfLevelsInBinaryTree extends BasicTemplate {
    public static void main(String[] args) {
        var LC = new LC637AverageOfLevelsInBinaryTree();
        var s = LC.averageOfLevels(null);
    }

    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new ArrayList<>();
        if (root == null) return res;
        List<TreeNode> l = new ArrayList<>();
        l.add(root);
        while (!l.isEmpty()) {
            List<TreeNode> nl = new ArrayList<>();
            double sum = 0;
            for (TreeNode r : l) {
                if (r.left != null) nl.add(r.left);
                if (r.right != null) nl.add(r.right);
                sum+=r.val;
            }
            res.add(sum/l.size());
            l = nl;
        }
        return res;
    }

}
