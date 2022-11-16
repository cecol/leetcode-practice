package leetcode202211.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.TreeSet;

public class LC480SlidingWindowMedian extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC480SlidingWindowMedian();
    }

    /**
     * 沒想到要刪除超出的元素直接去 Heap 刪除就好
     * 可以這麼做  就等同於這題
     * Find Median from Data Stream https://leetcode.com/problems/find-median-from-data-stream/
     * <p>
     * 準備2個 PriorityQueue, max, min
     * 我完全忘記這題邏輯 一直以為都是先給 min 才給 max,
     * 其實是達成
     * max 存前半段
     * min 存後半段
     * 這樣才會 max.peek, min.peek 是中間
     * 如果先給 min, min 吐給 max, 又把小的吐給 max, 兩邊根本不會成為一個有序
     * 所以都是先給 max, max 把大的吐給 min
     *
     * https://leetcode.com/problems/sliding-window-median/discuss/2464339/Java-Optimal-Sliding-Window-using-two-PriorityQueues
     * 這解法比較直觀
     * 1, 依照 max, min heap 來放元素, 確保 max >= min, 這樣 max 就是前半段且 max.peek 剛好是 median
     * 2. 維持 i,j 當作 sliding window, 如果 j-i+1 == k, 代表要砍 nums[i],
     * 直接去 max heap 砍, 如果回傳 false, 才去 min heap
     */
    public double[] medianSlidingWindow(int[] nums, int k) {
        int n = nums.length - k + 1;
        if (n <= 0) return new double[]{};
        double[] res = new double[n];
        PriorityQueue<Integer> max = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> min = new PriorityQueue<>();
        for (int i = 0, j = 0, idx = 0; j < nums.length; j++) {
            max.add(nums[j]);
            min.add(max.poll());
            if(min.size() > max.size()) max.offer(min.poll());
            if (j - i + 1 < k) continue;
            else if (j - i + 1 == k) {
                double median = min.size() == max.size() ? (double) ((long)min.peek() + (long)max.peek()) / 2 : max.peek();
                res[idx++] = median;
                if (!max.remove(nums[i])) min.remove(nums[i]);
                i++;
            }
        }
        return res;
    }
}
