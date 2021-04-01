package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.list.ListNode;

import java.util.HashMap;
import java.util.Map;

public class LC2AddTwoNumbers extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC2AddTwoNumbers();
    }


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode tmp = new ListNode(0);
        ListNode cur = tmp;
        int carry = 0;
        while(l1 != null || l2 != null) {
            ListNode n = new ListNode(0);
            int s = carry;
            if(l1 != null) s+=l1.val;
            if(l2 != null) s+=l2.val;
            n.val = s%10;
            carry = s/10;
            cur.next = n;
            cur = cur.next;
            if(l1 != null) l1 = l1.next;
            if(l2 != null) l2 = l2.next;
        }
        if(carry > 0) {
            ListNode n = new ListNode(carry);
            cur.next = n;
        }
        return tmp.next;
    }
}
