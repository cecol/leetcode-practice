package leetcode202103.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.ListNode;

import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class LC21MergeTwoSortedLists extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC21MergeTwoSortedLists();
    }

    /**
     * https://leetcode.com/problems/merge-two-sorted-lists/discuss/9715/Java-1-ms-4-lines-codes-using-recursion
     * 遞迴做法, 覺得真的是太好了
     */
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists2(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists2(l1, l2.next);
            return l2;
        }
    }

    /**
     * 我是有解出來, 但code有點長, 結果看了人家遞迴做法, 覺得真的是太好了
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) return null;
        if (l1 == null && l2 != null) return l2;
        if (l1 != null && l2 == null) return l1;
        ListNode h = null, c = null;
        if (l1.val > l2.val) {
            c = h = l2;
            l2 = l2.next;
        } else {
            c = h = l1;
            l1 = l1.next;
        }
        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                c.next = l2;
                l2 = l2.next;
            } else {
                c.next = l1;
                l1 = l1.next;
            }
            c = c.next;

        }
        if (l1 != null) c.next = l1;
        if (l2 != null) c.next = l2;
        return h;
    }
}
