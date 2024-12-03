package leetcode202211.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

public class LC632SmallestRangeCoveringElementsFromKLists extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC632SmallestRangeCoveringElementsFromKLists();
    }

    /**
     * 完全沒想到 heap 就可以解, 也沒想到這其實跟 merging k sorted array using a heap 很類似, 模板一樣 只是考的角度不一樣
     * https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/discuss/104893/Java-Code-using-PriorityQueue.-similar-to-merge-k-array
     * 解釋
     * https://github.com/wisdompeak/LeetCode/tree/master/Heap/632.Smallest-Range-Covering-Elements-from-K-Lists
     *
     * 1. 找出 nums[1 to n][0], 中的 MIN 與 MAX, 就是一個合法區間有包含 nums[1 to n]
     * 可是往後可能有更小區間 (如果後面找到都是同樣或者比較大區間, 還是前面找到的獲勝)
     * 所以 PriorityQueue 把 poll 出來就是當前 min, 且配上事先記載的 max, 這區間就是包含大家的區間
     * PriorityQueue 記載 nums[i] 進度
     * 1. nums[i][k] value
     * 2. offset in nums: i
     * 3. offset in nums[i]: k
     *
     * 2. 所以先把 nums[1 to n][0] 塞入 PriorityQueue, 並記載 max
     * 3. 開始 poll PriorityQueue,
     * 每拿出當前 min
     * 1. 跟目前記載的 max 算 Range, 有更小就更新 range,
     * 因為 PriorityQueue 是每 nums[i] 都加入了, 所以拿到的 MIN, 配上記載的MAX 就是包含大家的Range
     * 2. 如果拿出來的 MIN 代表的 nums[i] 還有下一個元素 就加入 PriorityQueue,
     * 要更新 max, 因為可能這個新加入, max 改變
     * 3. 繼續 poll min 迴圈, 找下一個 min-max range, 直到 PriorityQueue size < nums.size(), 代表有 nums[i] 被抽乾, 沒有 range 可以找了
     *
     * */
    public int[] smallestRange(List<List<Integer>> nums) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> x[0] - y[0]);
        int max = Integer.MAX_VALUE;
        for (int i = 0; i < nums.size(); i++) {
            pq.offer(new int[]{nums.get(i).get(0), i, 0});
            max = Math.max(max, nums.get(i).get(0));
        }

        int range = Integer.MAX_VALUE;
        int start = -1;
        while (pq.size() == nums.size()) {
            int[] min = pq.poll();
            if (max - min[0] < range) {
                range = max - min[0];
                start = min[0];
            }

            if (min[2] + 1 < nums.get(min[1]).size()) {
                min[2] += 1;
                min[0] = nums.get(min[1]).get(min[2]);
                max = Math.max(max, min[0]);
                pq.offer(min);
            }
        }
        return new int[]{start, start + range};
    }
}
