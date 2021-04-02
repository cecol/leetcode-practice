package leetcode202104.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.list.ListNode;

public class LC234PalindromeLinkedList extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC234PalindromeLinkedList();
    }



    /**
     * 直觀解法有解出來  faster than 53.92%
     * https://leetcode.com/problems/palindrome-linked-list/discuss/64501/Java-easy-to-understand
     * 1. fast/slow 找出中間點
     * 2. reverse後半
     * 3. 比對
     * 有recursive 解法
     * https://leetcode.com/problems/palindrome-linked-list/discuss/181453/Java-or-5-lines-method-or-2ms-or-O(n)-or-recursive-or-easy-to-understand
     * 沒看懂 但是速度很慢?
     * 17 ms, faster than 8.12%
     * 跟標題宣稱的不一樣
     * */
    public boolean isPalindrome(ListNode head) {
        ListNode s = head, f = head;
        while (f.next != null && f.next.next != null) {
            s = s.next;
            f = f.next.next;
        }
        if (f.next != null) f = f.next;
        ListNode pre = s.next, cur = pre.next;
        pre.next = null;
        while (cur != null) {
            ListNode np = cur.next;
            cur.next = pre;
            pre = cur;
            cur = np;
        }

        while (f != null) {
            if (f.val != head.val) return false;
            f = f.next;
            head = head.next;
        }
        return true;
    }
}
