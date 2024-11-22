package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.*;

public class LC133CloneGraph extends BasicTemplate {
    public static void main(String[] args) {
    }

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

    // 初見殺, 蠻直觀的, 主要是需要特別建立一個 map 去針對建立過的 node & neighbors 記錄下來, 避免一直重複建立
    Map<Node, Node> m = new HashMap<>();
    public Node cloneGraph(Node node) {
        if (node == null) return null;
        Node res = new Node(node.val);
        m.put(node, res);
        for (Node n : node.neighbors) {
            if(m.containsKey(n)) {
                res.neighbors.add(m.get(n));
            } else res.neighbors.add(cloneGraph(n));
        }
        return res;
    }
}
