package leetcode202105.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC406QueueReconstructionByHeight extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC406QueueReconstructionByHeight();
        LC.reconstructQueue(new int[][]{{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}});
    }

    /**
     * 完全沒想法, 連基本的邏輯都想不太出來, 覺得應該是某種sort邏輯可達成, 但一直歸納不出來
     * https://leetcode.com/problems/queue-reconstruction-by-height/discuss/89387/Java-solution-using-array-sort-and-greedy
     * 這是我比較理解的辦法
     * 關鍵是 先把 people排序成誰要優先考慮的,
     * 1. 從最矮的開始看
     * 2. 相同高度的, 前面需求越多的先看
     * 3. 當排序好了 people 就是從 第一個開始放到 res 中, 越前面就是越要先考慮的
     * 然後 assign list 就是 所有可以安排的位置index
     * 接著 iterate people, 根據當前 people[i][1] 裡面要求前面要有幾個, 就是去 list取出該位置的 idx
     * 取完要刪掉該 idx, 因為被占走了,  過程中 list會變短,
     * 概念就是 people[i][1] 指出前面還要有幾個, 前面要有幾個就當前list offset 所反映的狀況,
     * [[4,4], [5,2], [5,0], [6,1], [7,1], [7,0]]
     * list = [0, 1, 2, 3, 4, 5]
     * - p[4,4]: 4, list: [0, 1, 2, 3, 4, 5], idx: 4
     * - p[5,2]: 2, list: [0, 1, 2, 3, 5], idx: 2
     * - p[5,0]: 0, list: [0, 1, 3, 5], idx: 0
     * - p[6,1]: 1, list: [1, 3, 5], idx: 3 ->  the index 1 of remaining unoccupied position ->[5,0][][5,2][6,1][4,4][]
     * - p[7,1]: 1, list: [1, 5], idx: 5 -> the index 1 of remaining unoccupied position ->[5,0][][5,2][6,1][4,4][7,1]
     * - p[7,0]: 0, list: [1], idx: 1
     */
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (x, y) -> {
            if (x[0] != y[0]) return x[0] - y[0];
            else return y[1] - x[1];
        });
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < people.length; i++) list.add(i);
        int[][] res = new int[people.length][];
        for (int[] p : people) {
            int idx = list.get(p[1]);
            res[idx] = p;
            list.remove(p[1]);
        }
        return res;
    }
}
