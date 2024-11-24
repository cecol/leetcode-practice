package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.Arrays;

public class LC105ConstructBinaryTreeFromPreorderAndInorderTraversal extends BasicTemplate {
    public static void main(String[] args) {
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // 基本思路沒想錯, 但還是沒過, 死在切左右子樹的 offset 沒想好
    // 但是 Arrays.copyOfRange 有想到 簡化很多問題， 關鍵在於遞迴時, 要處理 沒有 左/右子樹情況的 if
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) return null;
        TreeNode rt = new TreeNode(preorder[0]);
        if (preorder.length == 1) return rt;
        int idx = 0;
        while (idx < inorder.length && inorder[idx] != preorder[0]) idx++;
        TreeNode l = null, r = null;
        if (idx > 0) {
            l = buildTree(Arrays.copyOfRange(preorder, 1, idx + 1), Arrays.copyOfRange(inorder, 0, idx));
        }
        if (idx < preorder.length - 1) {
            r = buildTree(Arrays.copyOfRange(preorder, idx + 1, preorder.length), Arrays.copyOfRange(inorder, idx + 1, inorder.length));
        }
        rt.left = l;
        rt.right = r;
        return rt;
    }
}
