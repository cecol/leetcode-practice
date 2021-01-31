package leetcode202101.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class LC297SerializeAndDeserializeTree extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC297SerializeAndDeserializeTree();
    }

    /**
     * https://leetcode.com/problems/serialize-and-deserialize-binary-tree/discuss/74253/Easy-to-understand-Java-Solution
     * 我一開始以為是跟BST serialize & deserialize很類似(因為那提的捷達也有提到BT, 但我沒細看)
     * 但沒想到更簡單的是, 直接用preorder, 尤其是null node用一個特殊字元下去encode就好了
     * 這樣因為serialize出來的就是完整的樹,
     * 因為一定有左右兒子 -> 只是是真的樹還是null, 是null就直接回傳, 真的樹就繼續的回下去
     * 所以就可以很容易用一個deque preorder遞迴下去
     * */
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }

    private void buildString(TreeNode n, StringBuilder sb) {
        if(n == null) sb.append("X").append(",");
        else {
            sb.append(n.val).append(",");
            buildString(n.left,sb);
            buildString(n.right,sb);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Deque<String> nodes = new LinkedList<>();
        nodes.addAll(Arrays.asList(data.split(",")));
        return buildTree(nodes);
    }

    private TreeNode buildTree(Deque<String> nodes){
        String val = nodes.remove();
        if(val.equals("X")) return null;
        else {
            TreeNode node = new TreeNode(Integer.parseInt(val));
            node.left = buildTree(nodes);
            node.right = buildTree(nodes);
            return node;
        }
    }

}
