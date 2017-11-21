package util;

public class BuildTreeFromInAndPre {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        int[] pre = {3, 1, 2, 4};
        int[] in = {1, 2, 3, 4};
        preorder(buildTree(pre, in));
    }

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(0, 0, inorder.length - 1, preorder, inorder);
    }

    public static TreeNode build(int rootPos, int inStart, int inEnd, int[] preorder, int[] inorder) {
        if (preorder.length == 0) return null;
        TreeNode node = new TreeNode(preorder[rootPos]);

        if (inStart == inEnd) {
            node.left = null;
            node.right = null;
        } else {
            int inPos = inStart;
            for (; inPos <= inEnd && inorder[inPos] != preorder[rootPos]; inPos++) ;
            int leftEnd = inPos - 1;
            int rightStart = inPos + 1;
            boolean hasLeft = leftEnd >= 0 && leftEnd >= inStart;
            boolean hasRight = rightStart < inorder.length && rightStart <= inEnd;
            if (hasLeft) {
                node.left = build(rootPos + 1, inStart, leftEnd, preorder, inorder);
            } else node.left = null;
            if (hasRight) {
                int rightRoot = leftEnd - inStart + 1 + 1;
                node.right = build(rootPos + rightRoot, rightStart, inEnd, preorder, inorder);
            } else node.right = null;
        }
        return node;
    }

    public static void preorder(TreeNode node) {
        if (node != null) {
            System.out.print(node.val + " ");
            preorder(node.left);
            preorder(node.right);
        }
    }
}
