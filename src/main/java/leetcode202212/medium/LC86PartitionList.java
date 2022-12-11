package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.list.ListNode;

import java.util.Stack;

public class LC86PartitionList extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC86PartitionList();
    }

    /**
     * https://leetcode.com/problems/partition-list/solutions/213571/three-java-solutions-with-recursion-dummy-node-and-in-place/
     * 我有想要用遞迴, 先拿 head.next 下去看看結果, 再把 head 加入回傳回來的結果
     * 只是 edge case 沒處理好
     * 關鍵在
     * if(h.val < x || nextHead == null || nextHead.val >= x)
     * 當前 head 是小的 or 回傳回來是空的 or 回傳回來是 只有大的
     * 那麼胞證 head 還是放在前頭回傳 h.next = nextHead;
     *
     * 只有當回傳回來 nextHead 裏面有大有小, 就得下去找 分界點把 head 加回去
     *
     * 我是在這段條件沒先定義好
     * if(h.val < x || nextHead == null || nextHead.val >= x)
     * 用成 if(h.val < x) 而已
     *
     * 導致進入 else (head.val >= x)
     * 要特別處理 nextHead == null || nextHead.val >= x case 會變很麻煩
     * pre, cur 得特別判斷！！
     ＊
     * */
    public ListNode partition(ListNode h, int x) {
        if(h == null || h.next == null) return h;
        ListNode nextHead = partition(h.next , x);
        if(h.val < x || nextHead == null || nextHead.val >= x) {
            h.next = nextHead;
            return h;
        } else {
            ListNode cur = nextHead, pre = cur;
            while(cur != null && cur.val < x) {
                pre = cur;
                cur = cur.next;
            }
            h.next = cur;
            pre.next = h;
            return nextHead;
        }
    }
}
