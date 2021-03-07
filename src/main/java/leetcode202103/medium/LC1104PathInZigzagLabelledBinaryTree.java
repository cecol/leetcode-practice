package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

public class LC1104PathInZigzagLabelledBinaryTree extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1104PathInZigzagLabelledBinaryTree();
    }

    /**
     * 題目花了很久才看懂 = = 基本上就是找出從 root 到 指定的 node的 path
     * https://leetcode.com/problems/path-in-zigzag-labelled-binary-tree/discuss/323312/Simple-solution-in-java-(Using-properties-of-complete-binary-tree)-(O-log-N)
     * 基本上就是從 指定的 node開始, 一直往前算parent
     * 因為是complete binary tree, 只是每個node算 label 是 zigzag
     * 1. Calculate current depth of the label
     * 2. Calculate offset (for each depth, values lie from 2^d -> 2^(d+1) -1
     * 3. Find the real parent based on offset
     * 4. Repeat until we reach 1
     * e.g. parent of 14 is 4
     * -> depth = 3, values in this depth lie from 8 to 15 (since it is a complete binary tree)
     * -> offset = 15 - 14 = 1
     * real parent of 14 = parent of ( 8 + offset ) = parent (9) = 9/2 = 4
     * */
    public List<Integer> pathInZigZagTree(int label) {
        LinkedList<Integer> res = new LinkedList<>();
        int p = label;
        res.addFirst(p);
        while (p != 1) {
            int h = (int) (Math.log(p) / Math.log(2));
            int offset = (int) Math.pow(2, h + 1) - 1 - p;
            p = ((int) Math.pow(2, h) + offset) / 2;
            res.addFirst(p);
        }
        return res;
    }
}
