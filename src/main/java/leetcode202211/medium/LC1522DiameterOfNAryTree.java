package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC1522DiameterOfNAryTree extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");

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

    int res = 0;

    public int diameter(Node root) {
        dfs(root);
        return res;
    }

    int dfs(Node rt) {
        if (rt == null) return 0;
        int t1 = 0, t2 = 0;
        for (Node ch : rt.children) {
            int max = dfs(ch);
            if (max > t1) {
                t2 = t1;
                t1 = max;
            } else if (max > t2) t2 = max;
        }
        res = Math.max(res, t1 + t2);
        return t1 + 1;
    }
}
