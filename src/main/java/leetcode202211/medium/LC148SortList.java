package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.list.ListNode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LC148SortList extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC148SortList();
    }

    /**
     * 沒有一開始想要就是用merge sort, 不過merge sort 有分 recursive & iterative
     * 看來 follow up 是要考 iterative
     * <p>
     * recursive version: https://leetcode.com/problems/sort-list/discuss/423304/java-mergesort
     * explain: https://leetcode.com/problems/sort-list/discuss/1795855/Java-or-2-methods-or-Explained
     */
    public ListNode sortListRecur(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode m = getMiddle(head);
        ListNode l1 = head, l2 = m.next;
        m.next = null;
        l1 = sortListRecur(l1);
        l2 = sortListRecur(l2);
        return merge(l1, l2);
    }

    ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode dummy = new ListNode(0), cur = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        if (l1 != null) cur.next = l1;
        if (l2 != null) cur.next = l2;
        return dummy.next;
    }

    ListNode getMiddle(ListNode n) {
        if (n == null) return n;
        ListNode s = n, f = n;
        while (f.next != null && f.next.next != null) {
            s = s.next;
            f = f.next.next;
        }
        return s;
    }

    /**
     * iterative version
     * https://leetcode.com/problems/sort-list/discuss/1253865/Java-Recursive-and-Iterative-Merge-Sort-Solution
     * 這個code 最容易看懂
     * 1. 先拿到 list 長度
     * 2. step 從 1, 2, 4, .. 下去直到 > len, merge sort 本來就是兩兩 merge, 所以每次結果都是 2 的指數
     *    step = 1, 從頭開始, 每1個 Node 當作獨立已sort, 下去兩兩 merge
     *    step = 2, 從頭開始, 每2個 Node 當作獨立已sort(上一圈 step=1 sort過了), 下去兩兩 merge
     *    step = 4, 從頭開始, 每4個 Node 當作獨立已sort(上一圈 step=2 sort過了), 下去兩兩 merge
     *    ...
     * 3. mergeSort() 拿到當前要切多長來做 mergeSort
     *     Ex: step 2, 當前 List 會是 [3 -> 4] -> [1 -> 6] -> [5 -> 7]
     *     所以先找出 [3 -> 4] & [1 -> 6]
     *     ListNode firstHalf = cur; -> firstHalf = [3 -> 4]
     *     ListNode secondHalf = splitByStep(firstHalf, step); 從firstHalf在往後找出step 2切出來: secondHalf = [1 -> 6]
     *     cur = splitByStep(secondHalf, step); 從secondHalf在往後找出step 2切出來: cur = [5 -> 7]
     *     cur 就是用來記載是否當前 firstHalf, secondHalf 之後還有沒有,
     *     有的話就是 while (cur != null) 繼續下一圈,繼續往後,繼續切 & merge
     * 4. splitByStep(ListNode head, step)
     *     就是很單純的head往後走多少 step(當前要切多少來merge), 找到該step 後的 node,
     *     node.next = null;
     *     回傳head step後的下一段head
     * 5. merge(l1, l2, pre)
     *     因為都是用當前一直往後 merge, 所以是從 pre(上一次merge 後的 tail) 繼續
     *     也要回傳這次merge 後的 tail, 供下一次merge
     */
    public ListNode sortListIter(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        int len = getLen(head);
        for (int step = 1; step < len; step *= 2) {
            mergeSort(dummy, step);
        }
        return dummy.next;
    }

    void mergeSort(ListNode dummy, int step) {
        ListNode pre = dummy, cur = dummy.next;
        while (cur != null) {
            ListNode firstHalf = cur;
            ListNode secondHalf = splitByStep(firstHalf, step);
            cur = splitByStep(secondHalf, step);
            pre = merge(firstHalf, secondHalf, pre);
        }
    }

    ListNode splitByStep(ListNode h, int step) {
        if (h == null) return h;
        ListNode cur = h;
        while (cur.next != null && step > 1) {
            cur = cur.next;
            step--;
        }
        ListNode next = cur.next;
        cur.next = null;
        return next;
    }

    ListNode merge(ListNode l1, ListNode l2, ListNode pre) {
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                pre.next = l1;
                l1 = l1.next;
            } else {
                pre.next = l2;
                l2 = l2.next;
            }
            pre = pre.next;
        }
        if (l1 != null) pre.next = l1;
        else pre.next = l2;
        while (pre.next != null) pre = pre.next;
        return pre;
    }


    int getLen(ListNode h) {
        int c = 0;
        while (h != null) {
            h = h.next;
            c++;
        }
        return c;
    }
}
