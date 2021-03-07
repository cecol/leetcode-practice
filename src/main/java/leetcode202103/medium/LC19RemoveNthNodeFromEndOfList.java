package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC19RemoveNthNodeFromEndOfList extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC19RemoveNthNodeFromEndOfList();
    }

    /**
     * 一開始看錯題目, 後來理解完知道無腦法, 但沒辦法做到 one pass,
     * https://leetcode.com/problems/remove-nth-node-from-end-of-list/discuss/8804/Simple-Java-solution-in-one-pass
     * 看到解答才恍然大悟 fast & slow 的關係是這樣
     * 1. fast先往前 n 格
     * 2. 接著 fast & slow 一起一格格前進 直到 fast == null, fast就像指標依樣, fast == null 時候就代表已經走到倒數 n了
     * 而且去除掉中間一個 只要 p.next = p.next.next; 簡單多了
     *
     * 我覺得比較難的是處理 corner case -> 就是說 如果要刪的剛好在 head, 或者要刪的倒數第幾格當好是長度N -> 最後刪到 head
     * 所以要做一個 0 開頭的 DummyNode, DummyNode(0).next = head , slow/fast = DummyNode 都從這開始
     * 做好以上準備後就可以統一：
     * 1. fast 先走 n 格,
     * 2 然後 slow/fast 一起一格走 直到 fast.next == null
     * 3 最後slow.next = slow.next.next;
     * 其實slow 是要刪的前一格
     * fast會走到last node, 也佔一個位置 剛好是最後面數過來的第一格
     * 不然的話沒有ＤｕｍｍｙＮｏｄｅ 得特別處理各種corner case
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode start = new ListNode(0);
        ListNode slow = start, fast = start;
        slow.next = head;
        for (int i = 1; i <= n; i++) {
            fast = fast.next;
        }
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return start.next;
    }
}
