package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.PriorityQueue;
import java.util.Stack;
import java.util.TreeSet;

public class LC2498FrogJumpII extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC2498FrogJumpII();
    }

    /**
     * https://leetcode.com/problems/frog-jump-ii/solutions/2897902/skip/
     * 這題果然是貪心 在競賽時候 當下看到根本看不出這題要問什麼演算法 資料結構
     * 雖然最後用很奇怪方式解過了, 但速度很差 O(NlogN吧)
     * <p>
     * 其實這題最難是觀察出貪心的策略
     * 應該要用 skip 看待這題
     * 如果這次經過踩在這石頭上, 下次就得 skip,
     * 如果這趟採連續兩個石頭, 就得 skip 這兩個
     * 所以最佳策略是每一趟每一跳只skip 一個  然後求 max(stones[i-1] to stones[i+1])
     */
    public int maxJump(int[] stones) {
        int res = stones[1] - stones[0];
        for (int i = 1; i < stones.length - 1; i++) res = Math.max(res, stones[i + 1] - stones[i - 1]);
        return res;
    }

    public int maxJumpSlow(int[] stones) {
        int n = stones.length;
        if (n <= 3) return stones[n - 1] - stones[0];
        TreeSet<Integer> forward = new TreeSet<>();
        TreeSet<Integer> backward = new TreeSet<>();
        forward.add(stones[0]);
        forward.add(stones[n - 1]);
        backward.add(stones[0]);
        backward.add(stones[n - 1]);
        int res = 0;
        PriorityQueue<int[]> fq = new PriorityQueue<>((x, y) -> y[2] - x[2]);
        PriorityQueue<int[]> bq = new PriorityQueue<>((x, y) -> y[2] - x[2]);
        fq.offer(new int[]{stones[0], stones[n - 1], stones[n - 1] - stones[0]});
        bq.offer(new int[]{stones[0], stones[n - 1], stones[n - 1] - stones[0]});
        for (int i = 1; i < n - 1; i++) {
            int s = stones[i];
            if (fq.peek()[2] > bq.peek()[2]) {
                int pre = forward.floor(s);
                int next = forward.ceiling(s);
                if (fq.peek()[0] == pre && fq.peek()[1] == next) {
                    fq.poll();
                    fq.offer(new int[]{pre, s, s - pre});
                    fq.offer(new int[]{s, next, next - s});
                }
                forward.add(s);
            } else {
                int pre = backward.floor(s);
                int next = backward.ceiling(s);
                if (bq.peek()[0] == pre && bq.peek()[1] == next) {
                    bq.poll();
                    bq.offer(new int[]{pre, s, s - pre});
                    bq.offer(new int[]{s, next, next - s});
                }
                backward.add(s);
            }
            res = Math.max(fq.peek()[2], bq.peek()[2]);
        }
        return res;
    }
}
