package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.list.ListNode;

import java.util.Arrays;

public class LC61RotateList extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");

    }

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) return head;
        ListNode t = head;
        int len = 1;
        while (t.next != null) {
            t = t.next;
            len++;
        }
        ListNode cur = head;
        for (int steps = len - k % len; steps > 1; steps--) cur = cur.next;
        t.next = head;
        ListNode res = cur.next;
        cur.next = null;
        return res;
    }
}
