package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.list.ListNode;

import java.util.Stack;

public class LC369PlusOneLinkedList extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC369PlusOneLinkedList();
    }

    /**
     * 也是用 遞迴 很直觀解掉
     * 因為 加1 是作用在最後一個 ListNode
     * 剩下的 parent ListNode 都是看前面一個算完 有沒有帶進位回來 再繼續算下去
     * 所以一個 global acc 來記載前一層 遞迴 的進位結果
     * 最後 遞迴結束看 acc 還有沒有 有的話得創造新的 head
     * */
    public ListNode plusOne(ListNode h) {
        plusOneRecur(h);
        if(acc == 1) {
            ListNode nh = new ListNode(1);
            nh.next = h;
            return nh;
        }
        return h;
    }

    int acc = 0;

    void plusOneRecur(ListNode h) {
        if (h.next == null) {
            int hv = h.val;
            h.val = (hv + 1) % 10;
            acc = (hv + 1) / 10;
            return;
        }
        plusOneRecur(h.next);
        int hv = h.val;
        h.val = (hv + acc) % 10;
        acc = (hv + acc) / 10;
    }

}
