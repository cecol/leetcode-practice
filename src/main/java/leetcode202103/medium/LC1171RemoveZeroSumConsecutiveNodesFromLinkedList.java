package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.list.ListNode;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LC1171RemoveZeroSumConsecutiveNodesFromLinkedList extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1171RemoveZeroSumConsecutiveNodesFromLinkedList();
    }

    /**
     * 完全沒什麼想法
     * https://leetcode.com/problems/remove-zero-sum-consecutive-nodes-from-linked-list/discuss/413134/Java-O(N)-with-detail-explanation
     * 其實換成 Array 就是找出 subarray 的0 -> preSum!!
     * 只是換成 List 我不知道怎麼解
     * 其實只有兩次 iterate list
     * iterate 1 -> List 沒有 Array index好用, 所以在累加過程中 把當前 preSum 存入 Map<Integer, ListNode>
     * -> 其中 如果 preSum 出現過兩次 -> 代表中間有一段加起來是 0, 0 -> 2 preSum = 5, 0 -> 7 preSum = 5,
     * -> 因為 0 -> 7 已包含 0 -> 2的加總的5, 結果其加總還是5, 代表 3 -> 7 加一加也還是 0, 所以要改成 0 -> 2 -> 8
     * -> 所以map 最後會存的是 kv: 5 -> NodeList 7 (原本先存 5 -> 2, 後來又被 key 7 蓋掉)
     * iterate 2 -> 因為 Map<Integer, ListNode> 已經有所有 preSum與其位置, 所以在重走一次 preSum累加
     * -> 如果 m 出現過該 preSum map 代表該 NodeList p.next -> preSum.get(sum) 中間都得去除, 因為這段加總都0
     * -> 所以要直接 p.next = preSum.get(sum).next; 然後中間這段就完全去除, 下一次 p = p.next 就去找更後面的 sum 0
     * 這樣寫法真的太神了....
     */
    public ListNode removeZeroSumSublists(ListNode head) {
        ListNode dummy = new ListNode(0), cur = dummy;
        dummy.next = head;
        int prefix = 0;
        Map<Integer, ListNode> preSum = new HashMap<>();
        int sum = 0;
        for (ListNode p = dummy; p != null; p = p.next) {
            sum += p.val;
            preSum.put(sum, p);
        }

        sum = 0;
        for (ListNode p = dummy; p != null; p = p.next) {
            sum += p.val;
            p.next = preSum.get(sum).next;
        }
        return dummy.next;
    }
}
