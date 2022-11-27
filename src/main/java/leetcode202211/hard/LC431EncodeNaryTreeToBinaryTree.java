package leetcode202211.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LC431EncodeNaryTreeToBinaryTree extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC431EncodeNaryTreeToBinaryTree();
    }

    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    ;

    // Encodes an n-ary tree to a binary tree.

    /**
     * https://leetcode.com/problems/encode-n-ary-tree-to-binary-tree/solution/
     * 官方的解答寫得很好, 沒看過都不知道可以這樣想
     * encode N-ary to binary tree
     * 1. binary tree 的left 一定是 N-ary 的第一個兒子, 兒子的left 仍是是兒子自己的 encode
     * - Node firstCh = rtn.children.get(0);
     * - rt.left = encode(firstCh);
     * 2. binary tree 的left 是 N-ary 的第一個兒子, 兒子的right 是指向自己兄弟, 就是 N-ary 的下一個兒子
     * - TreeNode sibling = rt.left;
     * - sibling.right = encode(rtn.children.get(i));
     * - sibling = sibling.right;
     * 如此遞迴下去
     * <p>
     * 所以每個 TreeNode rt
     * 1. rt.left 是自己第一個兒子, rt.left.right.right ... 是自己的後續兒子
     * 2. 同理 rt.right.right .. right 都是父親的兒子群, 只有 rt.left 是自己的兒子群
     * 因此 decode
     * 1. 拿左兒子一直往 right 取直到 right == null, 建立 N-ary 的兒子群
     * - TreeNode sibling = rt.left;
     * - sibling.right = encode(rtn.children.get(i));
     * - sibling = sibling.right;
     */
    public TreeNode encode(Node rtn) {
        if (rtn == null) return null;
        TreeNode rt = new TreeNode(rtn.val);
        if (rtn.children.size() > 0) {
            Node firstCh = rtn.children.get(0);
            rt.left = encode(firstCh);
        }

        TreeNode sibling = rt.left;
        for (int i = 1; i < rtn.children.size(); i++) {
            sibling.right = encode(rtn.children.get(i));
            sibling = sibling.right;
        }
        return rt;
    }

    // Decodes your binary tree to an n-ary tree.
    public Node decode(TreeNode rt) {
        if (rt == null) return null;
        Node rtn = new Node(rt.val, new ArrayList<>());
        TreeNode sibling = rt.left;
        while (sibling != null) {
            rtn.children.add(decode(sibling));
            sibling = sibling.right;
        }
        return rtn;
    }
}
