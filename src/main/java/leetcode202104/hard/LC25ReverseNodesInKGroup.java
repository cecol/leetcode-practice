package leetcode202104.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.list.ListNode;

public class LC25ReverseNodesInKGroup extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC25ReverseNodesInKGroup();
    }

    /**
     * 很直觀的用遞迴解掉了
     * 0 ms, faster than 100.00% o
     * 38.9 MB, less than 83.56%
     * 雖然跟其他人解得有點不太依樣, 但八九不離十
     * */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null) return head;
        ListNode check = head;
        for (int i = 0; i < k - 1; i++) {
            check = check.next;
            if (check == null) return head;
        }
        ListNode pre = head;
        ListNode cur = head.next;
        for (int i = 0; i < k - 1 && cur != null; i++) {
            ListNode nt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nt;
        }
        head.next = reverseKGroup(cur, k);
        return pre;
    }
}
