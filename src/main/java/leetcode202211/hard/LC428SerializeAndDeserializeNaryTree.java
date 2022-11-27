package leetcode202211.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC428SerializeAndDeserializeNaryTree extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC428SerializeAndDeserializeNaryTree();
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

    /**
     * https://leetcode.com/problems/serialize-and-deserialize-n-ary-tree/discuss/151421/Java-preorder-recursive-solution-using-queue
     * 這題其實跟 LC297SerializeAndDeserializeTree 很類似做法
     * 但有幾個細節
     * 1. 直接 preorder 下去 serialize, 先放 rt.val, 再放 rt.children.size, 然後根據 children.size 下去 preorder serialize
     * - deserialize 也是如此直接先解出 rt.val 跟 rt.children.size, 然後每個 child 下去 dfs
     * 2. 不用顧慮 List<Node> children = null case , children 一定有實例
     * 3. children 內也不會有 null
     * 4. null node 不必在用特殊字元儲存, 直接省略, 因為 rt.children.size 就告訴你要 寫/讀 多少了
     * 相較 binary tree, 得塞特殊字元 null 來判別 下一個是 left or right 是否是 null
     * rt.children 有就是有, 沒有所謂的 null child -> 這是最關鍵的！！
     * 不會有 rt.children: [null, ch1, null, null, ch2], 所以根本不用去處理 null case !!
     * */
    public String serialize(Node rt) {
        List<String> list = new LinkedList<>();
        serializeH(rt, list);
        return String.join(",", list);
    }

    void serializeH(Node rt, List<String> list) {
        if (rt == null) return;
        list.add(String.valueOf(rt.val));
        list.add(String.valueOf(rt.children.size()));
        for (Node ch : rt.children) serializeH(ch, list);
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if (data.isEmpty()) return null;
        String[] ss = data.split(",");
        Queue<String> q = new LinkedList<>(Arrays.asList(ss));
        return deserializeH(q);
    }

    Node deserializeH(Queue<String> q) {
        Node rt = new Node();
        rt.val = Integer.parseInt(q.poll());
        int size = Integer.parseInt(q.poll());
        rt.children = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            rt.children.add(deserializeH(q));
        }
        return rt;
    }
}
