package leetcode_old;

import java.util.*;

public class HouseRobberIII {
    public static void main(String[] s) {
        TreeNode t1 = new TreeNode(14);
        TreeNode t2 = new TreeNode(3);
        TreeNode t3 = new TreeNode(7);
        t1.left = t2;
        t1.right = t3;
        rob(t1);
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static int rob(TreeNode root) {
        int[] rob = rob1(root);
        return Math.max(rob[0], rob[1]);
    }

    public static int[] rob1(TreeNode root) {
        if (root == null) return new int[]{0, 0};

        int[] leftRob = rob1(root.left);
        int[] rightRob = rob1(root.right);
        int[] res = {0, 0};

        res[0] = Math.max(leftRob[0], leftRob[1]) + Math.max(rightRob[0], rightRob[1]);
        res[1] = root.val + leftRob[0] + rightRob[0];

        return res;
    }
}
