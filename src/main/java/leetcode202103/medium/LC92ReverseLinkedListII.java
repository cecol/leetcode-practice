package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.list.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC92ReverseLinkedListII extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC92ReverseLinkedListII();
    }

    /**
     * https://leetcode.com/problems/reverse-linked-list-ii/discuss/30666/Simple-Java-solution-with-clear-explanation
     * 還是沒有想到正確該怎麼解, 答案的第各回應比較簡單明瞭, 我是自己紙筆照著畫一遍才知道在幹嘛
     * 1. 先建立一個 dummy, dummy.next = head, 之後的 pointer都會變動, 只有dummy不會動 最後回傳 dummy.next
     * 2. 先給 ListNode pre = dummy; ListNode cur = dummy.next(head); int i = 1;
     * -> 因為一開始cur = head(dummy.next), 所以cur 已經往前走一步了, 所以 int i 一開始要給1
     * -> for (; i < left; i++)  這邊就是不斷往前走到left-1步, 達成 cur = left node, pre = previous left node
     * 3. 先把這兩個暫存起來, 最後要串回正確的(pre,cur 會在reverse過程中往前走)
     * ListNode leftHead = cur;
     * ListNode previousLeftHead = pre;
     * 4. for (; i <= right; i++) 因為cur 從 left開始了, 要開始往後串 一路串到 i = right
     * -> ListNode t = cur.next; 因為 cur 要往後reverse, 但下一個要先記下來才可以繼續往前, 所以先存給t
     * -> cur.next = pre; 完成往後 reverse串目的
     * 找下一個reverse後串:
     * -> pre = cur -> pre往前
     * -> cur = t(cur.next) -> cur往前
     * 最後 ->
     * leftHead.next = cur; leftHead 要指向right 之後的 -> cur走完 i == right, 時候 cur = right + 1
     * previousLeftHead.next = pre; -> 走完 i == right, 時候 pre = right
     * */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null) return null;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = dummy.next;
        int i = 1;
        for (; i < left; i++) {
            pre = cur;
            cur = cur.next;
        }
        ListNode leftHead = cur;
        ListNode previousLeftHead = pre;
        for (; i <= right; i++) {
            ListNode t = cur.next;
            cur.next = pre;
            pre = cur;
            cur = t;
        }
        leftHead.next = cur;
        previousLeftHead.next = pre;
        return dummy.next;
    }
}
