package leetcode202104.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.list.ListNode;

public class LC160IntersectionOfTwoLinkedLists extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC160IntersectionOfTwoLinkedLists();
    }

    /**
     * 很直覺得解法faster than 97.97%
     * time O(n) space O(1)
     * a,b 先往前走 誰先到null 就結束
     * 剩下來的那個, 代表差值
     * ex b != null -> b 還沒走完 -> 所以在拿這個 b, 跟 bb = headB 繼續一起走, 直到b == null
     * 這時候 bb 跟 headA 就是可以一起並行走一步一路走到交會點
     *
     * 關鍵就在於 a,b 並行走一步, 當a 到了 null, b剩下來的步數就是 a,b, 開頭長短的差異
     * 如果上述都沒找到交會, 代表沒有
     * */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode a = headA, b = headB;
        while (a != null && b != null) {
            a = a.next;
            b = b.next;
        }
        ListNode look = null;
        ListNode f1 = null;
        ListNode f2 = null;
        if (a != null) {
            look = a;
            f1 = headA;
            f2 = headB;
        } else {
            look = b;
            f1 = headB;
            f2 = headA;
        }
        while (look != null) {
            look = look.next;
            f1 = f1.next;
        }
        while (f2 != null && f1 != null) {
            if (f2 == f1) return f1;
            f2 = f2.next;
            f1 = f1.next;
        }
        return null;
    }
}
