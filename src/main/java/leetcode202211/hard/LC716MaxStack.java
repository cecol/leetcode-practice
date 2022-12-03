package leetcode202211.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC716MaxStack extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC716MaxStack();
    }

    public void MaxStack() {

    }


    /**
     * 這題乍看之下很類似 LC155MinStack 但因為多了一個 popMax()
     * 邏輯完全不適用了！！
     * https://leetcode.com/problems/max-stack/solution/
     * 所以要維持兩個 資料結構 一個支持傳統 stack, 一個支持 maxStack
     * top 會增減傳統 stack, 也會更動 maxStack
     * or
     * popMax 會增減 maxStack, 也會更動傳統 stack
     * 互相影響要是 O(LogN)
     *
     * 官方 TreeSet 解法就很直觀也很好理解
     * 準備
     * 1. TreeSet<int[]> stack -> 元素是 int[]{idx, val} , idx 取勝(idx 單調遞增)
     * 2. TreeSet<int[]> max -> 元素是 int[]{val, idx}, val 取勝 不然就就 idx
     * - 這樣就好好互相動態因為 pop 更新對方
     * 3. idx 是一個 global 遞增, 每次 push 都會遞增
     * 4. pop or popMax 就是各自拿 pollLast, 然後去對方那邊刪除
     * 5. top ro peekMax 就是各自拿 last
     * */
    TreeSet<int[]> stack = new TreeSet<>((x, y) -> {
        return x[0] == y[0] ? x[1] - y[1] : x[0] - y[0];
    });
    TreeSet<int[]> max = new TreeSet<>((x, y) -> {
        return x[0] == y[0] ? x[1] - y[1] : x[0] - y[0];
    });
    int idx = 0;

    public void push(int x) {
        stack.add(new int[]{idx, x});
        max.add(new int[]{x, idx});
        idx++;
    }

    public int pop() {
        int[] pair = stack.pollLast();
        max.remove(new int[]{pair[1], pair[0]});
        return pair[1];
    }

    public int top() {
        return stack.last()[1];
    }

    public int peekMax() {
        return max.last()[0];
    }

    public int popMax() {
        int[] pair = max.pollLast();
        stack.remove(new int[]{pair[1], pair[0]});
        return pair[0];
    }
}
