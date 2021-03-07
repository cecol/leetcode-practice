package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class LC133CloneGraph extends BasicTemplate {
    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC133CloneGraph();
    }

    /**
     * 有過, 因為有注意到 node 最多就 1-100種, 所以可以用個 global 來記錄開過的node, 速度也是很快
     * 的不知道如果 node種類無限種, 那該如何複製? 可能來是要有個 global node identifier
     * */
    Node[] ns = new Node[101];
    public Node cloneGraph(Node node) {
        if (node == null) return null;
        if (ns[node.val] != null) return ns[node.val];
        Node n = new Node(node.val);
        ns[node.val] = n;
        for (Node nn : node.neighbors) {
            if(ns[nn.val] == null) cloneGraph(nn);
            n.neighbors.add(ns[nn.val]);
        }
        return ns[node.val];
    }
}
