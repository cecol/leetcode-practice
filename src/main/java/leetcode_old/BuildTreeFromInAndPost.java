package leetcode_old;

public class BuildTreeFromInAndPost {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        int[] in = {3, 2, 1};
        int[] post = {3, 2, 1};
        postorder(buildTree(post, in));
    }

    public static TreeNode buildTree(int[] postorder, int[] inorder) {
        return build(postorder.length - 1, 0, inorder.length - 1, postorder, inorder);
    }

    public static TreeNode build(int rootPos, int inStart, int inEnd, int[] postorder, int[] inorder) {
        if (postorder.length == 0) return null;
        TreeNode node = new TreeNode(postorder[rootPos]);

        if (inStart == inEnd) {
            node.left = null;
            node.right = null;
        } else {
            int inPos = inStart;
            for (; inPos <= inEnd && inorder[inPos] != postorder[rootPos]; inPos++) ;
            int leftEnd = inPos - 1;
            int rightStart = inPos + 1;
            boolean hasLeft = leftEnd >= 0 && leftEnd >= inStart;
            boolean hasRight = rightStart < inorder.length && rightStart <= inEnd;
            if (hasLeft) {
                int leftRoot = rootPos - (inEnd - rightStart + 1 + 1);
                node.left = build(leftRoot, inStart, leftEnd, postorder, inorder);
            } else node.left = null;
            if (hasRight) {
                node.right = build(rootPos - 1, rightStart, inEnd, postorder, inorder);
            } else node.right = null;
        }
        return node;
    }

    public static void postorder(TreeNode node) {
        if (node != null) {
            postorder(node.left);
            postorder(node.right);
            System.out.print(node.val + " ");
        }
    }
}
