package leetcode202211.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LC1516MoveSubTreeOfNAryTree extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1516MoveSubTreeOfNAryTree();
    }

    class Node {
        public int val;
        public List<Node> children;


        public Node() {
            children = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            children = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    /**
     * https://leetcode.com/problems/move-sub-tree-of-n-ary-tree/discuss/751219/Java-easy-DFS
     * 這題沒什麼技巧, 主要就是三種 case 要處理好
     * 1. q 是 p 的子孫
     * 2. p 是 q 的子孫
     * 3. p, q 不是互相的子孫
     *
     * 基本上就是很直觀的 q.children.add(p);
     * 必做: 但是我們一定要找到 p 的 parent 把 p 刪掉
     * 可能要做: 找到 q parent , 因為 case 1 得把 q 接回 p 的位子, 不然 p parent 刪了 p, 就會變成兩棵樹
     * 所以必找 p, q parent ,來看情況是否要處理 case 1
     *
     */
    public Node moveSubTree(Node root, Node p, Node q) {
        if (q.children.contains(p)) return root;
        Node[] parent = new Node[2];
        findParent(root, p, q, parent);
        if (isSubTree(p, q)) {
            parent[1].children.remove(q);
            q.children.add(p);
            if (parent[0] == null) return q;
            int index = parent[0].children.indexOf(p);
            parent[0].children.set(index, q);
            return root;
        }
        q.children.add(p);
        parent[0].children.remove(p);
        return root;
    }

    void findParent(Node rt, Node p, Node q, Node[] parent) {
        if (rt == null) return;
        for (Node ch : rt.children) {
            if (ch == p) parent[0] = rt;
            if (ch == q) parent[1] = rt;
            findParent(ch, p, q, parent);
        }
    }

    boolean isSubTree(Node rt, Node p) {
        if (rt == null) return false;
        if (rt == p) return true;
        for (Node ch : rt.children) {
            if (isSubTree(ch, p)) return true;
        }
        return false;
    }
}
