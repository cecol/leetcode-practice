package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class LC946ValidateStackSequences extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC946ValidateStackSequences();
        LC.validateStackSequences(new int[]{1, 2, 3, 4, 5}, new int[]{4, 5, 3, 2, 1});
    }

    /**
     * https://leetcode.com/problems/validate-stack-sequences/discuss/1853250/JavaC%2B%2B-Space-Complexity-going-from-O(N)-greater-O(1)
     * 想不到這題就是很直觀的 Greedy, 照著給的 pushed/popped 來操作
     * 1. go though pushed[i] 每次直接 push,
     * 2. 檢查如果 sk.top == popped[idx] -> sk.pop, idx++, 直到不一樣
     * 3. 檢查是否 sk 是否為空
     */
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int idx = 0;
        Stack<Integer> sk = new Stack<>();
        for (int p : pushed) {
            sk.push(p);
            while(sk.size() > 0 && sk.peek() == popped[idx]) {
                idx++;
                sk.pop();
            }
        }
        return sk.isEmpty();
    }
}
