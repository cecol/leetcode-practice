package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.list.ListNode;

import java.util.Arrays;
import java.util.Stack;

public class LC1019NextGreaterNodeInLinkedList extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1019NextGreaterNodeInLinkedList();
    }

    /**
     * https://github.com/wisdompeak/LeetCode/blob/master/Stack/1019.Next-Greater-Node-In-Linked-List/1019.Next-Greater-Node-In-Linked-List.cpp
     * 這跟 LC496NextGreaterElementI NextGreaterElement 沒什麼差別
     * 只是轉換成 List, 很直觀的把 List 轉成 int[] 來處理就好
     * 你總是要先走遍 list 才會知道最後要走的長度
     * 然後就是維持一個 monotonic decrease stack 就好
     *
     * 是有辦法只走過一遍的解法, Stack 裏面擺 int[]{offset, val}
     * 然後透過 List<Integer> answer = new ArrayList<>();
     * 以及 answer.set(id, head.val);
     * 可以跳著插入你要的值在你要的位置
     * 不過其實只是少走一遍 仍是 O(N)
     * */
    public int[] nextLargerNodes(ListNode head) {
        if (head.next == null) return new int[]{0};
        int n = 0;
        ListNode cur = head;
        while (cur != null) {
            n++;
            cur = cur.next;
        }
        int[] res = new int[n];
        int[] num = new int[n];
        n = 0;
        cur = head;
        while (cur != null) {
            num[n++] = cur.val;
            cur = cur.next;
        }
        Stack<Integer> sk = new Stack<>();
        for (int i = 0; i < n; i++) {
            while(sk.size()>0&& num[i] > num[sk.peek()]){
                res[sk.pop()] = num[i];
            }
            sk.push(i);
        }

        return res;
    }
}
