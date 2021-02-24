package leetcode202102.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.Node;
import util.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class LC116PopulatingNextRightPointersInEachNode extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC116PopulatingNextRightPointersInEachNode();
        var s = LC.connect(null);
    }

    /**
     * 直覺用level order來解, 是有想到應該沒這麼簡單
     * 看了別人的解法才發現我確實沒想透 complete binary tree的好處 還有利用已建立好的 Next來做事
     * https://leetcode.com/problems/populating-next-right-pointers-in-each-node/discuss/37472/A-simple-accepted-solution
     * 直接先
     * 1. dfs左子樹下去 -> 左子樹一定next右子樹
     * 2. 然後dfs右子樹 -> 透過上一層拿到的next -> 來找出 next.left 來給子右子樹
     *
     * 後來發現其實可以更直覺的直接寫
     * 1. n.left.next = n.right; -> 因為是完整的2元樹, 有left一定有right
     * 2. n.right.next = n.next==null?null:n.next.left;
     * ->   因為是完整的2元樹, 有next, 那麼right就可以直接指下去, 知道爸爸的時候, 其實爸爸(n.next)就已經建立好, 所以可以直接用
     * 然後直接dfs(n.left), dfs(n.right) 其實先dfs哪個兒子都沒差, 因為爸爸有已準備好next, 兒子的next直接用
     */
    public Node connect(Node root) {
        dfs(root, null);
        return root;
    }

    private void dfs(Node curr, Node next) {
        if (curr == null) return;
        curr.next = next;
        dfs(curr.left, curr.right);
        dfs(curr.right, curr.next == null? null: curr.next.left);
    }

    private void dfs2(Node n) {
        if(n==null) return;
        if(n.left != null) {
            n.left.next = n.right;
            n.right.next = n.next==null?null:n.next.left;
            dfs2(n.left);
            dfs2(n.right);
        }
    }
}
