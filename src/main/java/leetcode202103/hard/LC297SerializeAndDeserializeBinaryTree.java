package leetcode202103.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC297SerializeAndDeserializeBinaryTree extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC297SerializeAndDeserializeBinaryTree();
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // 沒過, 有想起字串 PRE-ORDER 但想不起 DEQUE 作法
    // 關鍵: 每個 NODE 都是 ',' 分隔, null 用 X encode
    // decode 就是先SPLIT 就下去 deque 遞迴
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        buildS(root, sb);
        return sb.toString();
    }

    void buildS(TreeNode rt, StringBuilder sb) {
        if (rt == null) sb.append("X").append(",");
        else {
            sb.append(rt.val).append(",");
            buildS(rt.left, sb);
            buildS(rt.right, sb);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Deque<String> nodes = new LinkedList<>();
        nodes.addAll(Arrays.asList(data.split(",")));
        return buildT(nodes);
    }

    TreeNode buildT(Deque<String> nodes) {
        String val = nodes.remove();
        if(val.equals("X")) return null;
        else {
            TreeNode n = new TreeNode(Integer.valueOf(val));
            n.left = buildT(nodes);
            n.right = buildT(nodes);
            return n;
        }
    }
}
