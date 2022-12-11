package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.list.ListNode;

import java.util.Stack;

public class LC82RemoveDuplicatesFromSortedListII extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC82RemoveDuplicatesFromSortedListII();
    }

    /**
     * https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/solutions/28339/my-recursive-java-solution/
     * 這題一直覺得操作 cur/pre 來找 duplicated 很沒效率也容易錯
     * 後來看到 recursive 答案才覺得這才是正解
     * 1. 先看 head == null or head.next == null  是的話就不用看了
     * 2. 如果 h.next.val == h.val 就代表 head 這一串下去都要砍了, 所以 head 一路走到最後 然後回傳 deleteDuplicates(h.next);
     * 3. 如果 h.next.val != h.val 就代表 head 保住了 遞迴檢查 next; h.next = deleteDuplicates(h.next);
     * */
    public ListNode deleteDuplicates(ListNode h) {
        if (h == null || h.next == null) return h;
        if (h.val == h.next.val) {
            while (h.next != null && h.val == h.next.val) h = h.next;
            return deleteDuplicates(h.next);
        } else h.next = deleteDuplicates(h.next);
        return h;
    }
}
