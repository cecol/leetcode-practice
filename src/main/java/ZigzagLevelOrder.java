import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class ZigzagLevelOrder {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
//        TreeNode root = new TreeNode(3);
//        TreeNode r1 = new TreeNode(20);
//        TreeNode l1 = new TreeNode(9);
//        root.left = l1;
//        root.right = r1;
//        TreeNode r2 = new TreeNode(7);
//        TreeNode l2 = new TreeNode(15);
//        r1.left = l2;
//        r1.right = r2;
        TreeNode root = new TreeNode(1);
        TreeNode r1 = new TreeNode(3);
        TreeNode l1 = new TreeNode(2);
        root.left = l1;
        root.right = r1;
        TreeNode r2 = new TreeNode(5);
        TreeNode l2 = new TreeNode(4);
        r1.right = r2;
        l1.left = l2;
        List<List<Integer>> result = zigzagLevelOrder(root);
        for (List<Integer> i : result)
            for (Integer j : i) System.out.print(j + " ");

    }

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> list = new LinkedList<List<Integer>>();
        if (root == null) return list;
        Stack<TreeNode> st = new Stack<TreeNode>();
        st.push(root);
        bfsWithZigzagAndLevel(list, st, true, 1);
        return list;
    }

    public static void bfsWithZigzagAndLevel(List<List<Integer>> list,
                                             Stack<TreeNode> st,
                                             boolean direction,
                                             int total) {
        if (total == 0) return;
        int count = 0;
        Queue<TreeNode> subQue = new LinkedList<TreeNode>();
        for (; total > 0; total--) subQue.add(st.pop());
        List<Integer> sub = new LinkedList<Integer>();
        while (!subQue.isEmpty()) {
            TreeNode node = subQue.remove();
            sub.add(node.val);
            if (direction) {
                count = addStack(st, node.left, count);
                count = addStack(st, node.right, count);
            } else {
                count = addStack(st, node.right, count);
                count = addStack(st, node.left, count);
            }
        }
        list.add(sub);
        if (count > 0) bfsWithZigzagAndLevel(list, st, !direction, count);
    }

    public static int addStack(Stack<TreeNode> st, TreeNode node, int count) {
        if (node != null) {
            st.push(node);
            count++;
        }
        return count;
    }
}
