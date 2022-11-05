package leetcode202210.easy;

import leetcode20200921to20201031.BasicTemplate;
import leetcode20200921to20201031.hard.LC23MergeKSortedLists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.list.ListNode;

public class LC876MiddleOfTheLinkedList extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC876MiddleOfTheLinkedList();
    }

    public ListNode middleNode(ListNode head) {
        ListNode n = head, m = head;
        while (m.next != null) {
            n = n.next;
            m = m.next;
            if (m.next != null) m = m.next;
        }
        return n;
    }
}
