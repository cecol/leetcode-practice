package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.list.ListNode;

public class LC142LinkedListCycleII extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC142LinkedListCycleII();
    }


    /**
     * https://leetcode.com/problems/linked-list-cycle-ii/discuss/44793/O(n)-solution-by-using-two-pointers-without-change-anything
     * 不知道這種 cycle detect 有個algorithm: Floyd's cycle detection algorithm, also known as the hare-tortoise algorithm.
     * To understand this solution, you just need to ask yourself these question.
     * Assume the distance from head to the start of the loop is x1
     * the distance from the start of the loop to the point fast and slow meet is x2
     * the distance from the point fast and slow meet to the start of the loop is x3
     * What is the distance fast moved? What is the distance slow moved? And their relationship?
     *
     * x1 + x2 + x3 + x2
     * x1 + x2
     * x1 + x2 + x3 + x2 = 2 (x1 + x2)
     * Thus x1 = x3
     * 花了好長時間看, 才看懂上述的解釋,
     * 我們重點是要找出 cycle起點, 所以他是善用 list起點 -> cycle起點的步數 == fast/slow cycle meet點 回到 cycle start的步數
     * 以下是已知的情境推導
     * 1. fast走的步數是slow*2 -> 這是最基本邏輯, 畢竟fast一次走兩步, slow一次走一步
     * 2. fast/slow must meet in cycle certain point y
     * 3. 幾個點的關係
     * -> x1: list起點 -> cycle起點
     * -> fast/slow一定是 meet in cycle point y, x2: cycle start -> y
     * -> y 繼續走下去還會回到 cycle start, x3: y forward to -> cycle start
     * -> slow 走了 x1 + x2 到 y
     * -> fast 走了 x1+x2+x3+x2 到 y
     * -> 又因為 fast走的步數是slow*2 -> x1 + x2 + x3 + x2 = 2 (x1 + x2) -> 可得知 x1 == x3
     * 所以當 fast meet slow時後
     * slow繼續走, 這時候head往前走, slow走了 x3剛好到 cycle start, 而 head也走了x3到了 cycle start
     * */
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return null;
        ListNode slow = head, fast = head, start = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                while (slow != start) {
                    slow = slow.next;
                    start = start.next;
                }
                return start;
            }
        }
        return null;
    }
}
