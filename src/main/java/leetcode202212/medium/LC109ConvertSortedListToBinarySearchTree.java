package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;
import util.list.ListNode;

import java.util.Stack;

public class LC109ConvertSortedListToBinarySearchTree extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC109ConvertSortedListToBinarySearchTree();
    }

    /**
     * 直觀的用遞迴秒解
     * 1. h == null return, 一定是空樹
     * 2. h.next == null return, 只有一個點的樹
     * 3. h.next.next return, 兩點也只有 一種可能
     * 4. 三個點以上, 找出中間點, 因為中間點當 root 一定最平衡高度
     * 找出中間點後, 切斷 左邊, 拿 head 去 遞迴結果當左樹
     * 中間點.next 去 遞迴結果當右樹
     * 回傳結果
     * */
    public TreeNode sortedListToBST(ListNode h) {
        if (h == null) return null;
        if (h.next == null) return new TreeNode(h.val);
        if (h.next.next == null) return new TreeNode(h.val, null, new TreeNode(h.next.val));

        ListNode s = h, f = h, pre = s;
        while (f.next != null && f.next.next != null) {
            pre = s;
            s = s.next;
            f = f.next.next;
        }
        pre.next = null;
        TreeNode rt = new TreeNode(s.val);
        rt.left = sortedListToBST(h);
        rt.right = sortedListToBST(s.next);
        return rt;
    }
}
