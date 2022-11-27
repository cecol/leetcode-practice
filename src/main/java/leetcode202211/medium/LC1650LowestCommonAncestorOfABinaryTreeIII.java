package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

public class LC1650LowestCommonAncestorOfABinaryTreeIII extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");

    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    }

    /**
     * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iii/discuss/2684138/Java-simple-and-short-solution
     * 原本有解出來, 但寫得太複雜了, 先往下找 不然網上找
     * 但其實都往上找就好, 用一個 Set 記載看過的 parent
     * */
    public Node lowestCommonAncestor(Node p, Node q) {
        Set<Node> seenPParent = new HashSet<>();
        while(p != null) {
            seenPParent.add(p);
            p = p.parent;
        }

        while(!seenPParent.contains(q))  {
            q = q.parent;
        }
        return q;
    }
}
