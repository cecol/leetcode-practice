package leetcode202103.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class LC589N_aryTreePreorderTraversal extends BasicTemplate {
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

    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC589N_aryTreePreorderTraversal();
    }

    public List<Integer> preorder(Node root) {
        List<Integer> res = new ArrayList<>();
        pre(root, res);
        return res;
    }

    private void pre(Node n, List<Integer> res) {
        if (n == null) return;
        res.add(n.val);
        for (Node nn : n.children) pre(nn, res);
    }
}
