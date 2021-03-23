package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.list.ListNode;

import java.util.Arrays;

public class LC24SwapNodesInPairs extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC24SwapNodesInPairs();
    }


    /**
     * 自己解出來, 但也是花了點時間 figure out, linkedlist題型都還不夠熟練就是
     * */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode n3 = head.next.next;
        ListNode n2 = head.next;
        n2.next = head;
        head.next = swapPairs(n3);
        return n2;
    }
}
