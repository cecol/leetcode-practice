import java.util.LinkedList;

public class MinimumAbsoluteDifferenceInBST {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static int getMinimumDifference(TreeNode root) {
        if (root == null) return 0;
        LinkedList<Integer> l = new LinkedList<>();
        buildNodeList(root, l);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < l.size() - 1; i++)
            if (Math.abs(l.get(i) - l.get(i + 1)) < min) min = Math.abs(l.get(i) - l.get(i + 1));
        return min;
    }

    public static void buildNodeList(TreeNode root, LinkedList<Integer> l) {
        if (root.left != null) buildNodeList(root.left, l);
        l.add(root.val);
        if (root.right != null) buildNodeList(root.right, l);
    }
}
