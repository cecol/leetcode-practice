package leetcode202103.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.ListNode;

public class LC141LinkedListCycle extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC141LinkedListCycle();
    }

    public boolean hasCycle(ListNode head) {
        ListNode l = head;
        ListNode r = head.next;
        while (r != l && r != null && r.next != null) {
            l = l.next;
            r = r.next.next;
        }
        return l == r;
    }
}
