package leetcode202103.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Deque;
import java.util.LinkedList;

public class LC84LargestRectangleInHistogram extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC84LargestRectangleInHistogram();
    }

    /**
     * https://leetcode.com/problems/largest-rectangle-in-histogram/discuss/29018/AC-clean-Java-solution-using-stack
     * 有想到要用 stack, 但還是沒好好地想出細節
     * 1. stack 儲存單調遞增(儲存的是 height的 index, 因為要用來算width), 所以不能直接存 height[i], 是要存 i
     * 2. 所以遇到當前的 height 小於 stack top -> 該兌現前面的 max area(因為當前stack 存的比較高的也在後面無望了, 後面矮的會去兌現)
     * -> 所以先q.pollLast() -> heights[q.pollLast()]
     * -> 而pop出來的這個要兌現的
     * -> 1. 如果 stack 還有(!q.isEmpty()), 也一定比當前要兌現的小
     * ->   因此能兌現的寬度就是  i - q.getLast() + 1
     * -> int realizeHWidth = i - (q.isEmpty() ? 0 : q.getLast() + 1);
     * -> 為什麼寬度是 i 減去 q.getLast() + 1?
     * -> 因為是遞增, 所以當前拿出來的 realizeH = heights[q.pollLast()];
     * -> 他只能往後看算到 i - 1, 往前都比他小(因為遞增stack中的都比他小, 自然無法往更前面來算面積)
     * -> 所以可以算得 width 是 i - (q.getLast() + 1)
     * -> 如果 stack is empty -> 等於前面沒有比他更小, 等於說整個都可以計算
     * -> 這其實就是 heights = [2,1,5,6,2,3] 中, 當i 走到n, stack 會是存 1,4,5 offset, 相對應就是height: 1,2,3
     * -> 這時候0 會強迫把 1,2,3 pop出來算面積, 最後pop到1, 1就會是貫通的, 因為他最矮, 自然可全寬度全算
     * -> 會算到 q.isEmpty() ? 0 in (int realizeHWidth = i - (q.isEmpty() ? 0 : q.getLast() + 1);)
     * -> 1. 要嘛是最矮的 這樣 width就是會是 n -> 唯一一個可以留到 i == n 並且算 height * n的
     * -> 2. 要嘛是最矮的 前面的height這樣, width就是會是當時走到的i來算(因為不是最矮, 所以只能貫通到頭, 不能到n)
     * 3. 最後一點  for (int i = 0; i <= n; i++) 其中讓 i 走到 n 是因為要配合 int v = i == n ? 0 : heights[i];
     * 等於說若一直遞增, 就會沒有發生兌現, 所以, 得帶入一個最小高度0 -> 算是最後收尾把還沒兌現的高度都兌現了
     */
    public int largestRectangleArea(int[] heights) {
        int n = heights.length, res = 0;
        Deque<Integer> q = new LinkedList<>();
        for (int i = 0; i <= n; i++) {
            int v = i == n ? 0 : heights[i];
            while (!q.isEmpty() && v < heights[q.getLast()]) {
                int realizeH = heights[q.pollLast()];
                int realizeHWidth = i - (q.isEmpty() ? 0 : q.getLast() + 1);
                res = Math.max(res, realizeH * realizeHWidth);
            }
            q.add(i);
        }
        return res;
    }
}
