package leetcode202104.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.list.ListNode;

import java.util.HashMap;
import java.util.Map;

public class LC445AddTwoNumbersII extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC445AddTwoNumbersII();
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode rev1 = rev(l1);
        ListNode rev2 = rev(l2);
        ListNode tmp = new ListNode(0);
        ListNode cur = tmp;
        int carry = 0;
        while (rev1 != null || rev2 != null) {
            int s = carry;
            if (rev1 != null) {
                s += rev1.val;
                rev1 = rev1.next;
            }
            if (rev2 != null) {
                s += rev2.val;
                rev2 = rev2.next;
            }
            cur.next = new ListNode(s%10);
            cur = cur.next;
            carry = s / 10;
        }
        if(carry > 0) cur.next = new ListNode(carry);
        rev(tmp.next);
        return tmp.next;
    }

    private ListNode rev(ListNode n) {
        if (n == null || n.next == null) return n;
        ListNode t = rev(n.next);
        n.next.next = n;
        n.next = null;
        return t;
    }
}
