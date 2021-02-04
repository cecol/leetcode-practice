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
}
