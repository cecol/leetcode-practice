package leetcode202103.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.list.ListNode;

import java.util.Deque;
import java.util.LinkedList;

public class LC206ReverseLinkedList extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC206ReverseLinkedList();
    }

    /**
     * 用Stack 是蠻容易解的, 但速度超慢
     * 完全沒想到有遞迴解, 看來這方面的題目我還是不熟
     * https://leetcode.com/problems/reverse-linked-list/discuss/58156/My-Java-recursive-solution
     * 基本概念就是
     * 1. 先遞迴 head.next下去 -> 終止條件是 head == null || head.next == null 沒的遞迴了->代表就是要reverse的 head -> 回傳自己
     * 2. 上一層回來的, 就是reversed 完的head -> 善用自己.next還是原本方向找到下一個把下一個指回自己 -> reverse才會發生
     * -> 然後 assign 自己.next = null; 自己的正確的reversed next, 是由前一層遞迴自己的前一個來把自己指向前一層(因為自己不會知道前一層是誰)
     * 3. 回傳前一層回傳的真正reversed head
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}
