package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.list.ListNode;

import java.util.PriorityQueue;

public class LC328OddEvenLinkedList extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC328OddEvenLinkedList();
    }

    /**
     * https://leetcode.com/problems/odd-even-linked-list/discuss/78079/Simple-O(N)-time-O(1)-space-Java-solution.
     * 沒有想出解法, 以為要用遞迴, 但看到答案才明白 odd, even pointer就可以解了
     * 尤其是只要檢查 even, 因為一定有 odd -> 因為給的 head就是odd -> while (even != null && even.next != null)
     * -> 有下一個even 就有odd
     * 然後往後指就很簡單明瞭, odd, even個別處理就好
     * odd.next = odd.next.next;
     * even.next = even.next.next;
     * even = even.next;
     * odd = odd.next;
     */
    public ListNode oddEvenList(ListNode head) {
        if (head != null) {
            ListNode odd = head, even = head.next, evenH = even;
            while (even != null && even.next != null) {
                odd.next = odd.next.next;
                even.next = even.next.next;
                even = even.next;
                odd = odd.next;
            }
            odd.next = evenH;
        }
        return head;
    }
}
